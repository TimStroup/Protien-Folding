package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ProteinFolder {

    public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Nucleotide> sequence = new ArrayList<>();
        readRNASequence("input5.txt",sequence);
        System.out.println(Arrays.toString(sequence.toArray()));
        System.out.println(sequence.size());
    }

    private static void readRNASequence(String fileName,ArrayList<Nucleotide> sequence) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        String[] letters = scan.next().split("");
        for(String letter: letters){
        	sequence.add(new Nucleotide(letter));
		}
    }

    private static int simulatedAnnealing(ArrayList<Nucleotide> sequence){


    	return 0;
	}

}
