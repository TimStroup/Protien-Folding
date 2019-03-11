package com.company;

public class Nucleotide {

	protected String type;
	protected boolean paired;
	protected int connectedIndex = -1;

	protected Nucleotide(String type){
		this.type = type;
		this.paired = false;
	}

	protected void connect(int index){
		connectedIndex = index;
	}

	public String toString(){
		return this.type;
	}
}
