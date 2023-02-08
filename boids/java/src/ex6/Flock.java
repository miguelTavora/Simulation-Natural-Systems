package ex6;

import java.util.ArrayList;

import graph.SubPlot;
import processing.core.PApplet;
import processing.core.PVector;

public class Flock {

	private ArrayList<Boid> boids;
	private Boid b;

	public Flock(int nboids, float radius, float mass, int color, PApplet p, SubPlot plt) {

		boids = new ArrayList<Boid>();
		double[] w = plt.getWindow();
		for (int i = 0; i < nboids; i++) {
			float x = p.random((float) w[0], (float) w[1]);
			float y = p.random((float) w[2], (float) w[3]);
			b = new Boid(new PVector(x, y), mass, radius, color, plt.getWindow());

			float vx = p.random(-1, 1);
			float vy = p.random(-1, 1);

			b.setVel(new PVector(vx, vy));
			b.setShape(p, plt);
			boids.add(b);
		}

	}

	// aplicacao das 3 forcas necessarias (coesao, separacao e alinhamento)
	public void applyBehaviour() {

		for (Boid b : boids) {

			ArrayList<Boid> boidsInSight = b.inCone(boids);
			// forca alinhamento
			PVector fa = b.align(boidsInSight);
			// forca de coesao
			PVector fc = b.cohension(boidsInSight);
			fc.mult(0.1f);
			// forca de separacao
			PVector fs = b.separation(boidsInSight);
			// soma das 3 forcas em simultaneo
			b.applyForce(fa.add(fc).add(fs));

		}
	}

	public void move(float dt) {
		for (Boid b : boids) {
			b.move(dt);
		}
	}

	public void display(PApplet p, SubPlot plt) {

		for (Boid b : boids)
			b.display(p, plt);

	}

	public ArrayList<Boid> getBoids() {
		return this.boids;
	}

	public Boid getBoid() {
		return this.b;
	}

}
