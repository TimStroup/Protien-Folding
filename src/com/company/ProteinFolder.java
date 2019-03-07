package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ProteinFolder {
    private static ArrayList<String> sequence = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        readRNASequence("input.txt");
        System.out.println(Arrays.toString(sequence.toArray()));
    }

    private static void readRNASequence(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        String[] letters = scan.next().split("");
        sequence.addAll(Arrays.asList(letters));
    }

}
