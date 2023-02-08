package fractals;

import processing.core.PApplet;
import processing.core.PImage;


public class LSystemFlorest implements IProcessingApp {

	private PImage bg;
	private LSystemFlorest1 lsysf1;
	private LSystemFlorest2 lsysf2;
	private LSystemFlorest3 lsysf3;
	

	@Override
	public void setup(PApplet p) {
		
		bg = p.loadImage("fractals\\back.jpg");
		
		lsysf1 = new LSystemFlorest1();
		lsysf1.setup(p);

		lsysf2 = new LSystemFlorest2();
		lsysf2.setup(p);
		
		lsysf3 = new LSystemFlorest3();
		lsysf3.setup(p);
		

	}

	@Override
	public void draw(PApplet p) {
		p.background(bg);
		lsysf1.draw(p);
		lsysf2.draw(p);
		lsysf3.draw(p);

	}

	@Override
	public void keyPressed(PApplet p) {
	}

	@Override
	public void mousePressed(PApplet p) {

		lsysf1.mousePressed(p);
		lsysf2.mousePressed(p);
		lsysf3.mousePressed(p);
	}

}
