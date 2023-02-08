package ex8;

import java.util.ArrayList;

import ex8.ParticleSystem;
import ex8.SubPlot;
import processing.core.PApplet;
import processing.core.PVector;

public class artificialFire implements IProcessingApp {

	private ArrayList<ParticleSystem> pss;
	private double[] window = { -10, 10, -10, 10 };
	private float[] viewport = { 0f, 0f, 1f, 1f };
	private SubPlot plt;
	private Particle particle;
	private int[] mousePosition;
	private int timer;
	private int timeStamp;
	private int timeDisappear;
	private boolean pressed;

	@Override
	public void setup(PApplet p) {
		mousePosition = new int[2];
		mousePosition[0] = p.mouseX;
		mousePosition[1] = p.mouseY;
		plt = new SubPlot(window, viewport, p.width, p.height);
		pss = new ArrayList<ParticleSystem>();
		timer = 0;
		timeStamp = 0;
		timeDisappear = 0;
		pressed = false;
		p.noStroke();
	}

	@Override
	public void draw(PApplet p, float dt) {

		if (pressed) {
			p.background(10);
			
			particle.move(dt);
			particle.display(p, plt);
			timeStamp = p.millis()+timer;
			
			
				
			if (timeStamp  > 0) {
				for (ParticleSystem ps : pss) {
					PVector f = new PVector(0, 0);
					ps.applyForce(f);
					ps.move(dt);
					ps.display(p, plt);

				}
			}
			
			if(p.millis() - timeDisappear > 0 ) {
				p.background(10);
				pss.remove(0);
				pressed = false;
			}
			
		}
	}

	@Override
	public void keyPressed(PApplet p) {
	}

	@Override
	public void mousePressed(PApplet p) {
		double[] ww = plt.getWorldCoord(p.mouseX, p.mouseY);
		ParticleSystem ps = new ParticleSystem(new PVector((float) ww[0], (float) ww[1]), new PVector(0, 0), 1,0.2f,
				p.color( p.random(255), p.random(255), p.random(255)), 0.5f, new PVector(p.random(-80, 80),p.random(-80, 80)));
		pss.add(ps);

		particle = new Particle(new PVector((float) ww[0], -10), new PVector(0, 6), 1, 0.2f,
				p.color(255, p.random(150), p.random(150)), 2f);
		
		timer = -(200*((int)(ww[1]+10))+p.millis());
		
		timeDisappear = p.millis()+6000;
		
		if(pss.size() > 1) {
			pss.remove(0);
		}
		pressed = true;
		
	}

}
