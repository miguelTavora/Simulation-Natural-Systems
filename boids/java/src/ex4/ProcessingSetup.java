package ex4;

import processing.core.PApplet;

public class ProcessingSetup extends PApplet {

	private static IProcessingApp app;

	// VARIAVEIS
	private int lastUpDate;

	@Override
	public void settings() {
		size(800, 600);
	}

	@Override
	public void setup() {
		app.setup(this);
		lastUpDate = millis();
	}

	@Override
	public void draw() {
		int now = millis();
		float dt = (now - lastUpDate) / 1000f;
		lastUpDate = now;
		app.draw(this, dt);
	}

	@Override
	public void keyPressed() {
		app.keyPressed(this);
	}

	@Override
	public void mousePressed() {
		app.mousePressed(this);
	}
	
	@Override
	public void keyReleased() {
		app.keyReleased(this);
		
	}

	public static void main(String[] args) {
		app = new BoidTestApp1();
		PApplet.main(ProcessingSetup.class);
	}
}