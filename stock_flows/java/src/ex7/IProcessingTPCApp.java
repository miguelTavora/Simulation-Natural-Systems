package ex7;

import processing.core.PApplet;

public interface IProcessingTPCApp {
	
	public void setup(PApplet p); 	
	public void draw(PApplet p, float dt);
	public void keyPressed(PApplet p);
	public void mousePressed(PApplet p);

}