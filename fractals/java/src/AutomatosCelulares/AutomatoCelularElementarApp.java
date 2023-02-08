package AutomatosCelulares;

import fractals.IProcessingApp;
import graph.SubPlot;

import processing.core.PApplet;
import processing.core.PConstants;

public class AutomatoCelularElementarApp implements IProcessingApp {
	
	private AutomatoCelularElementar eac;
	
	//regra 60
	private int[] rule = {0,0,1,1,1,1,0,0};
	//regra 126
	//private int[] rule = {0,1,1,1,1,1,1,0};
	
	private int ncells=250;
	private int nitter=70;
	private double window[]= {0,ncells,0,nitter};
	private float[]viewport= {0f,0f,1f,1f};
	private SubPlot plt;
	
	@Override
	public void setup(PApplet p) {
		plt=new SubPlot(window, viewport, p.width, p.height);
		eac=new AutomatoCelularElementar(ncells,rule);
		eac.init();
		p.rectMode(PConstants.CORNERS);
		p.noStroke();
		
	}

	@Override
	public void draw(PApplet p) {

		eac.display(p, plt);
		eac.nextState();
		p.frameRate(5);
	}

	@Override
	public void keyPressed(PApplet p) {
		
		
	}

	@Override
	public void mousePressed(PApplet p) {
		
		
	}

}