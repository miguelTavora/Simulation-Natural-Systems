package ex5;

import java.util.ArrayList;

import graph.SubPlot;
import physics.Mover;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
import processing.core.PVector;

public class Boid extends Mover {

	protected int color;
	private PShape shape;
	protected double[] window;
	private float maxSpeed = 6;
	private float maxForce = 10;
	private float maxDistance = 3;
	private float maxAngle = (float) Math.PI;
	protected DNA dna;
	private float phiWander;

	protected Boid(PVector pos, float mass, float radius, int color, double[] window) {

		super(pos, new PVector(), mass, radius);
		this.color = color;
		this.window = window;
		this.dna = new DNA();
		phiWander = (float) Math.PI;

	}

	@Override
	public void move(float dt) {
		super.move(dt);
		if (pos.x < window[0]) {
			pos.x += (window[1] - window[0]);
		}
		if (pos.y < window[2]) {
			pos.y += (window[3] - window[2]);
		}

		if (pos.x > window[1]) {
			pos.x -= (window[1] - window[0]);
		}
		if (pos.y > window[3]) {
			pos.y -= (window[3] - window[2]);
		}

	}

	public void setShape(PApplet p, SubPlot plt) {

		float[] rr = plt.getPixelVector(radius, radius);

		PShape s = p.createShape();
		s.beginShape();
		s.noStroke();
		s.fill(color);
		s.vertex(-rr[0], rr[0] / 2);
		s.vertex(rr[0], 0);
		s.vertex(-rr[0], -rr[0] / 2);
		s.vertex(-rr[0] / 2, 0);
		s.endShape(PConstants.CLOSE);

		this.shape = s;

	}

	@Override
	public void applyForce(PVector f) {
		super.applyForce(f.limit(maxForce));
	}

	public PVector seek(PVector target) {

		// vector entre o boid esta e o target
		PVector vd = PVector.sub(target, pos);
		// colocar uma velocidade maxima (velocidade desejada
		vd.normalize().mult(maxSpeed);

		// Fs= vd - v
		return PVector.sub(vd, vel);
	}

	public PVector wander(PApplet p) {

		PVector center = pos.copy();
		center.add(PVector.mult(vel, dna.deltaTWander));
		PVector target = new PVector(dna.radiusWander * PApplet.cos(phiWander),
				dna.radiusWander * PApplet.sin(phiWander));
		target.add(center);
		phiWander += p.random(-dna.deltaPhiWander, dna.deltaPhiWander);

		return seek(target);
	}

	public PVector cohension(ArrayList<Boid> boids) {

		PVector t = pos.copy();
		for (Boid b : boids) {
			t.add(b.pos);
		}
		// centro de massa
		t.div(boids.size() + 1);

		return seek(t);

	}

	public PVector pursuit(Boid b) {
		PVector d = PVector.mult(b.vel, dna.deltaTPursuit);
		PVector target = PVector.add(b.pos, d);
		return seek(target);
	}

	public PVector evade(Boid b) {
		return pursuit(b).mult(-1);
	}

	public PVector align(ArrayList<Boid> boids) {

		// media de velocidades
		PVector vd = this.vel.copy();

		for (Boid b : boids) {
			vd.add(b.vel);
		}
		vd.normalize().mult(maxSpeed);
		// Fs = vd-v
		return PVector.sub(vd, vel);

	}

	public PVector separation(ArrayList<Boid> boids) {

		PVector vd = vel.copy();
		float dSafe = maxDistance / 4;

		for (Boid b : boids) {
			if (inSight(b.pos, dSafe, (float) Math.PI)) {
				PVector r = PVector.sub(pos, b.pos);
				dSafe = r.mag();
				vd = PVector.div(r, dSafe * dSafe);
			}
		}

		return PVector.sub(vd, vel);
	}

	public PVector flee(PVector target) {

		return seek(target).mult(-1);

	}

	public PVector brake() {
		PVector vd = new PVector();

		return PVector.sub(vd, vel);

	}

	public ArrayList<Boid> inCone(ArrayList<Boid> allBoids) {

		ArrayList<Boid> boidsInSight = new ArrayList<Boid>();

		for (Boid b : allBoids) {
			if (inSight(b.pos, maxDistance, maxAngle)) {
				boidsInSight.add(b);
			}
		}

		return boidsInSight;

	}

	public boolean inSight(PVector t, float maxDistance, float maxAngle) {

		PVector r = PVector.sub(t, pos);
		float d = r.mag();
		float angle = PVector.angleBetween(vel, r);

		return (((d > 0) && d < maxDistance) && (angle < maxAngle));

	}

	public boolean inSight(PVector t) {
		return (this.inSight(t, 4, (float) (Math.PI)));
	}

	public void display(PApplet p, SubPlot plt) {
		p.pushMatrix();
		float[] pp = plt.getPixelCoord(pos.x, pos.y);
		p.translate(pp[0], pp[1]);
		p.rotate(-vel.heading());
		p.shape(shape, 0, 0);
		p.popMatrix();

	}

	public DNA getDNA() {
		return dna;
	}
	
	public PVector getPos() {
		return this.pos;
	}

}
