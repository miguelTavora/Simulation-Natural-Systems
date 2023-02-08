package ex6;

import graph.SubPlot;
import processing.core.PApplet;
import processing.core.PVector;

public class FlockTestApp implements IProcessingApp {

	private double[] window = { -10, 10, -10, 10 };
	private float[] viewport = { 0f, 0f, 1f, 1f };
	private SubPlot plt;
	private Flock flock;
	private float radius = 0.3f;
	private float mass = 1f;
	private Boid b;
	private float speed;

	@Override
	public void setup(PApplet p) {
		// TODO Auto-generated method stub
		plt = new SubPlot(window, viewport, p.width, p.height);
		flock =new Flock(50,radius,mass,p.color(0),p,plt);
	
		
		b = new Boid(new PVector(0, 0), mass, radius, p.color(200, 100, 100), window);
		b.setShape(p, plt);
		speed = 1f;

	}

	@Override
	public void draw(PApplet p, float dt) {
		// TODO Auto-generated method stub
		p.background(255);
		flock.applyBehaviour();
		flock.move(dt);
		flock.display(p, plt);
		flock.getBoids().get(0).setColor(p.color(100,200,100));

		PVector f;
		if (b.inSight(flock.getBoids().get(0).getPos(), 4, (float) Math.PI)) {
			f = b.pursuit(flock.getBoids().get(0));
			b.applyForce(f); // aplica força para ele se mecher

		} else if (b.inSight(flock.getBoids().get(1).getPos(), 4, (float) Math.PI)) {
			f = b.pursuit(flock.getBoids().get(1));
			b.applyForce(f);
			
		} else if (b.inSight(flock.getBoids().get(2).getPos(), 4, (float) Math.PI)) {
			f = b.pursuit(flock.getBoids().get(2));
			b.applyForce(f);
			
		} else if (b.inSight(flock.getBoids().get(3).getPos(), 4, (float) Math.PI)) {
			f = b.pursuit(flock.getBoids().get(3));
			b.applyForce(f);
			
		} else {
			f = b.wander(p);
			b.applyForce(f);
		}

		b.move(dt * speed); // mecher
		b.display(p, plt);// mostrar

	}

	@Override
	public void keyPressed(PApplet p) {

	}

	@Override
	public void mousePressed(PApplet p) {

	}

	@Override
	public void mouseReleased(PApplet p) {
		// TODO Auto-generated method stub

	}

}
