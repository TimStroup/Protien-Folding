package com.company;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ProteinFolder {

    public static void main(String[] args) throws FileNotFoundException {
    	if(args.length <1){
    		System.out.println("You must provide the file name as an argument");
		}
		else{
			ArrayList<Nucleotide> sequence = new ArrayList<>();
			readRNASequence(args[0],sequence);

			if(sequence.size() < 5){
				System.out.println(0);
			}
			else{
				System.out.println(simulatedAnnealing(sequence));
			}
		}


    }

    private static void readRNASequence(String fileName,ArrayList<Nucleotide> sequence) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        String[] letters = scan.next().split("");
        for(String letter: letters){
        	sequence.add(new Nucleotide(letter));
		}
    }

    private static int simulatedAnnealing(ArrayList<Nucleotide> sequence){
		//use the number of paired =0 for the first state
		//keep track of the max pairs so far
		//keep a double to represent temperature
		//generate a random next state by picking a random index in the sequence, if it is not paired, try to find a pair for it in the sequence according to the constraints, if it is break that pair

		int numPairs = 0;
		int maxPairs = 0;
		int time = 1;
		double temp = 100.0;

		while(true){
			temp = schedule(time,sequence.size());
			if(temp <= 0){
				int numPaired = 0;
				for(Nucleotide nucleotide: sequence){
					if(nucleotide.paired){
						numPaired++;
					}
				}
				numPairs = numPaired/2;
				if(numPairs > maxPairs){
					maxPairs= numPairs;
				}
				return maxPairs;
			}
			boolean notDone = true;
			int randomIndex;
			while(notDone){
				randomIndex = getRandomIndex(sequence.size());
				//if it is paired we will try to break it up based on temp
				if(sequence.get(randomIndex).paired){
					//compute the probability to choose the
					double randProb = Math.random();
					double probOfChange = Math.exp(-1.0/temp);
					if(probOfChange > randProb){
						int connectedIndex = sequence.get(randomIndex).disconnect();
						sequence.get(connectedIndex).disconnect();
						numPairs--;
						notDone =false;
					}
					notDone = false;
				}
				//otherwise we will try to match the nucleotide with another one
				//also update the number of pairs and if it is greater than the max update that as well
				else{
					if(attemptPair(randomIndex,sequence)){

						notDone = false;
					}
				}
			}
			time++;
		}
	}

	private static boolean attemptPair(int randomIndex, ArrayList<Nucleotide> sequence) {
		String type = sequence.get(randomIndex).type;
		boolean result = false;
		if(randomIndex + 4 > sequence.size()){ }
		else {
			for(int i = randomIndex+4; i < sequence.size();i++){
				result = pairCheck(type,sequence,i,randomIndex);
				if(result){
					break;
				}
			}
		}

		if(!result){
			if(randomIndex -4 < 0){ }
			else {
				for(int i = randomIndex -4; i > 0; i--){
					result = pairCheck(type,sequence,i,randomIndex);
					if(result){
						break;
					}
				}
			}
		}
		return result;
	}

	private static boolean pairCheck(String type, ArrayList<Nucleotide> sequence,int i, int randomIndex){
		if(type.equals("A")){
			if(sequence.get(i).type.equals("U") && !sequence.get(i).paired){
				sequence.get(randomIndex).connect(i);
				sequence.get(i).connect(randomIndex);
				return true;
			}
		}
		else if(type.equals("C")){
			if(sequence.get(i).type.equals("G") && !sequence.get(i).paired ){
				sequence.get(randomIndex).connect(i);
				sequence.get(i).connect(randomIndex);
				return true;
			}
		}
		else if(type.equals("G")){
			if((sequence.get(i).type.equals("U") || sequence.get(i).type.equals("C")) && !sequence.get(i).paired){
				sequence.get(randomIndex).connect(i);
				sequence.get(i).connect(randomIndex);
				return true;
			}
		}
		else {
			if((sequence.get(i).type.equals("A") || sequence.get(i).type.equals("G")) && !sequence.get(i).paired){
				sequence.get(randomIndex).connect(i);
				sequence.get(i).connect(randomIndex);
				return true;
			}
		}
		return false;
	}

	private static double schedule(int time,int length) {
    	//TODO create a function that maps time to a temperature value
		if(length > 100){
			return(100.0 - (.000002 * time));
		}
		else{
			return (100.0 - (.0002 * time));
		}

	}

	private static int getRandomIndex(int max) {
		Random r = new Random();
		return r.nextInt(max);
	}

}
