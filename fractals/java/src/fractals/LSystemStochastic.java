package fractals;

import java.util.Random;

public class LSystemStochastic {

	private String sequence;
	private Rule[] ruleset;
	private int generation;
	private float random;

	public LSystemStochastic(String axiom, Rule[] ruleset) {
		Random r = new Random();
		random = r.nextFloat();
		if (random < 0.5) {
			sequence = axiom;
			this.ruleset = ruleset;
			generation = 0;
		} else {
			sequence = "F";
			this.ruleset = ruleset;
			generation = 0;
		}

	}

	public String getSequence() {
		return sequence;
	}

	public int getGeneration() {
		return generation;
	}

	public void nextGeneration() {
		generation++;

		String nextgen = "";
		if (random < 0.5) {
			for (int i = 0; i < sequence.length(); i++) {
				char c = sequence.charAt(i);
				String replace = "" + c;
				for (int j = 0; j < ruleset.length; j++) {
					if (c == ruleset[j].getSymbol()) {
						replace = ruleset[j].getString();
						break;
					}
				}
				nextgen += replace;
			}
			this.sequence = nextgen;
		} else {
			Random r = new Random();
			String str;
			int a = r.nextInt(4);
			if(a == 0) 
				str = "F";
			
			else if(a ==1) 
				str = "G";
			
			else if(a ==2) 
				str = "+";
			
			else 
				str = "-";
			
			for (int i = 0; i < sequence.length(); i++) {
				char c = sequence.charAt(i);
				String replace = str + c;
				for (int j = 0; j < ruleset.length; j++) {
					if (c == ruleset[j].getSymbol()) {
						replace = replace + ruleset[j].getString();
						break;
					}
				}
				nextgen += replace;
			}
		}
		this.sequence = nextgen;
	}

}
