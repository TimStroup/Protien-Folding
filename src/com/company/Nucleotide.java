package com.company;

public class Nucleotide {

	protected String type;
	protected boolean paired;

	public Nucleotide(String type){
		this.type = type;
		this.paired = false;
	}

	public String toString(){
		return this.type;
	}
}
