package fractalsRunFromTime;

import ChaosGame.IProcessingApp2;
import processing.core.PApplet;
import processing.core.PConstants;

public class MandelbrotApp implements IProcessingApp2 {

	final int maxIterations = 100;
	private float w;
	private float h;
	private float xmin;
	private float ymin;
	private float xmax;
	private float ymax;
	private float dx;
	private float dy;

	@Override
	public void setup(PApplet p) {
		// TODO Auto-generated method stub
		p.colorMode(PConstants.RGB, 1);
		w = 5;
		h = (w * p.height) / p.width;
		xmin = -w / 2;
		ymin = -h / 2;
		xmax = xmin + w;
		ymax = ymin + h;
		dx = (xmax - xmin) / (p.width);
		dy = (ymax - ymin) / (p.height);
		
	}

	@Override
	public void draw(PApplet p, float dt) {

		p.loadPixels();

		float y = ymin;
		for (int i = 0; i < p.height; i++) {
			// Start x
			float x = xmin;
			for (int j = 0; j < p.width; j++) {

				float a = x;
				float b = y;
				int number = 0;
				while (number < maxIterations) {
					float aa = a * a;//valores de mandelbrot que parte real: a^2-b^2 + (imaginaria) 2*a*b
					float bb = b * b;
					float twoab = 2 * a * b;
					a = aa - bb + x;
					b = twoab + y;

					if (a * a + b * b > 16) {
						break;
					}
					number++;
				}

				if (number == maxIterations) {
					p.pixels[j + i * p.width] = p.color(0);
				} else {

					p.pixels[j + i * p.width] = p.color(0,0, PApplet.sqrt((float) (number) / 100));//100 é max de iterações
				}
				x += dx;
			}
			y += dy;
		}
		p.updatePixels();

	}

	@Override
	public void keyPressed(PApplet p) {

	}

	@Override
	public void mousePressed(PApplet p) {

	}

}
