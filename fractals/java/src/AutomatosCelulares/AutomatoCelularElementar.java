package AutomatosCelulares;

import graph.SubPlot;

import processing.core.PApplet;

public class AutomatoCelularElementar {
	private int cells[];
	private int rule[];
	private int t;
	
	public AutomatoCelularElementar(int ncells,int[] rule){
		cells=new int[ncells];
		this.rule=rule;
	}
	
	public void init() {
		t=0;
		for(int i=0;i<cells.length;i++) {
			cells[i]=0;
		}
		cells[((int)cells.length-1)/2]=1;
	}
	
	public void initRandom() {
		for(int i=0;i<cells.length;i++) {
			cells[i]=(Math.random()<0.5)?0:1;
		}
	}
    @Override
    public String toString() {
    	String s="";
    	for(int i=0;i<cells.length;i++) {
    		s+=cells[i];
    	}
    	return s;
    }
    public void nextState() {
    	int[]aux=new int[cells.length];
    	for(int i=0;i<cells.length;i++) {
    		int previous=(i==0)?cells.length-1:i-1;
    		int next=(i==cells.length-1)?0:i+1;
    		int ix=4*cells[previous]+2*cells[i]+cells[next];
    		aux[i]=rule[ix];
    	}
    	cells=aux;
    	t++;
    }
    public void display(PApplet p,SubPlot plt) {
    	for(int i=0;i<cells.length;i++) {
    		float []c1=plt.getPixelCoord(i,t);
    		float []c2=plt.getPixelCoord(i+1,t+1);
    		p.fill(255*(1-cells[i]));
    		p.rect(c1[0], c1[1], c2[0], c2[1]);
    	}
    }
	
}

