package fractalsRunFromTime;

import ChaosGame.IProcessingApp2;
import processing.core.PApplet;
import processing.core.PConstants;

public class JsetsApp2 implements IProcessingApp2 {

	private final int maxIterations = 10;
	private float ca;
	private float cb;
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
		p.background(255);
		ca = -0.875f;
		cb = -0.2321f;
		w = 5;
		h = (w * p.height) / p.width;
		xmin = -w / 2;
		ymin = -h / 2;
		// x vai de xmin at� xmin
		xmax = xmin + w;
		ymax = ymin + h;
		dx = (xmax - xmin) / (p.width);
		dy = (ymax - ymin) / (p.height);

		p.colorMode(PConstants.RGB, 1);
	}

	@Override
	public void draw(PApplet p, float dt) {

		p.loadPixels();

		// Calculate amount we increment x,y for each pixel
		float y = ymin;
		for (int i = 0; i < p.height; i++) {
			float x = xmin;
			for (int j = 0; j < p.width; j++) {
				float a = x;
				float b = y;
				int n = 0;
				while (n < maxIterations) {
					float aa = a * a;
					float bb = b * b;

					if (aa + bb > 16.0) 
						break;
					

					float twoab = 2.0f * a * b;
					a = aa - bb + ca;
					b = twoab + cb;
					n++;
					
					if (n == maxIterations) 
						p.pixels[j + i * p.width] = p.color(0);
					 
					else 
						p.pixels[j + i * p.width] = p.color(PApplet.sqrt((float) (n) / 10),1,1);// 10 � max itera��es
					
				}

				x += dx;
			}
			y += dy;
		}
		p.updatePixels();
		p.noLoop();
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
