package ChaosGame;

import java.util.Random;

import processing.core.PApplet;

public class Fern implements IProcessingApp2 {

	private float x, y, random;

	@Override
	public void setup(PApplet p) {
		x = 0;
		y = 0;
		random = 0;
		p.background(0);
		

	}
	public void generatePoinsts() {
		float nextx;
		float nexty;
		
		Random r = new Random();

		random = r.nextFloat();
		
		//regras do fern e as probabilidades na net
		if (random < 0.01) {
			nextx = 0;
			nexty = (float) (0.16 * y);
		}
		else if (random < 0.86) {
			nextx = (float) (0.85 * x + 0.04 * y);
			nexty = (float) (-0.04 * x + 0.85 * y + 1.6);
		}
		else if (random < 0.93) {
			nextx = (float) (0.2 * x + -0.26 * y);
			nexty = (float) (0.23 * x + 0.22 * y + 1.6);

		}
		else {
			nextx = (float) (-0.15 * x + 0.28 * y);
			nexty = (float) (0.26 * x + 0.24 * y + 0.44);
		}
		

		x = nextx;
		y = nexty;
	}

	@Override
	public void draw(PApplet p, float dt) {
		for(int i = 0;i< 1000;i++) {
			p.stroke(26,148,49);
			p.strokeWeight(1);

			float px = PApplet.map(x, -5, 5, 0, p.width);
			float py = PApplet.map(y, 0, 10, p.height, 0);

			p.point(px, py);

			generatePoinsts();
		}
		

	}

	@Override
	public void keyPressed(PApplet p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(PApplet p) {
		// TODO Auto-generated method stub

	}

}
