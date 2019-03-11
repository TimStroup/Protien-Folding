package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ProteinFolder {

    public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Nucleotide> sequence = new ArrayList<>();
        readRNASequence("input2.txt",sequence);
        System.out.println(Arrays.toString(sequence.toArray()));
        System.out.println(sequence.size());

        if(sequence.size() < 5){
        	System.out.println(0);
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
		double temp = Double.MAX_VALUE;

		while(true){
			temp = schedule(time);
			if(temp == 0){
				return maxPairs;
			}
			boolean notDone = true;
			int randomIndex;
			while(notDone){
				randomIndex = getRandomIndex(sequence.size());
				//if it is paired we will try to break it up based on temp
				if(sequence.get(randomIndex).paired){

				}
				//otherwise we will try to match the nucleotide with another one
				//also update the number of pairs and if it is greater than the max update that as well
				else{
					if( attemptPair(randomIndex,sequence)){
						numPairs++;
						if(numPairs > maxPairs){
							maxPairs = numPairs;
						}
						notDone = false;
					}
				}
			}
		}
	}

	private static boolean attemptPair(int randomIndex, ArrayList<Nucleotide> sequence) {


		return false;
	}

	private static double schedule(int time) {
    	//TODO create a function that maps time to a temperature value
		return 0;
	}

	private static int getRandomIndex(int max) {
		Random r = new Random();
		return r.nextInt(max);
	}

}
