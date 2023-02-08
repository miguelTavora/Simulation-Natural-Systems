package boids;

import graph.SubPlot;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class BoidTestApp implements IProcessingApp {

	private double[] window = { -10, 10, -10, 10 };
	private float[] viewport = { 0f, 0f, 1f, 1f };
	private SubPlot plt;
	private Boid b;
	private Boid b2;
	private Boid b3;
	private Boid b4;
	private Boid b5;
	private Boid b6;

	private float radius = 0.3f;
	private float mass = 1f;
	private boolean brake = false;
	private PImage img;
	

	@Override
	public void setup(PApplet p) {

		img = p.loadImage("boids/baack.png");

		plt = new SubPlot(window, viewport, p.width, p.height);

		b = new Boid(new PVector(-9.5f, 6f), mass, radius, p.color(0), window);
		b2 = new Boid(new PVector(9, 7.7f), mass, radius, p.color(0), window);
		b3 = new Boid(new PVector(-0.6f, -9f), mass, radius, p.color(128, 0, 128), window);
		//b4 vem de cima e vira a esquerda
		b4 = new Boid(new PVector(9, 8f), mass, radius, p.color(122, 17, 11), window);
		b5 = new Boid(new PVector(-0.7f, -9f), mass, radius, p.color(250, 218, 94), window);
		b6 = new Boid(new PVector(-9, 6.5f), mass, radius, p.color(0, 0, 255), window);

		b.setShape(p, plt);
		b2.setShape(p, plt);
		b3.setShape(p, plt);
		b4.setShape(p, plt);
		b5.setShape(p, plt);
		b6.setShape(p, plt);

		

	}

	@Override
	public void draw(PApplet p, float dt) {

		p.background(img);


		//esquerda para a direita
		b.leftGoRight(dt);
		
		
		//direita para a esquerda
		b2.rightGoLeft(dt);

		// boid vem de baixo e vira direita
		b3.downGoRight(dt);

		// boid vem da esquerda vai para baixo
		b4.rightGoDown(dt);
		
		//baixo para a esquerda
		b5.downGoLeft(dt);
		
		//esquerda para baixo
		b6.leftGoDown(dt);
		
		
		b.display(p, plt);
		b2.display(p, plt);
		b3.display(p, plt);
		b4.display(p, plt);
		b5.display(p, plt);
		b6.display(p, plt);

	}

	@Override
	public void keyPressed(PApplet p) {
		brake = !brake;

	}

	@Override
	public void mousePressed(PApplet p) {

	}

	@Override
	public void mouseReleased(PApplet p) {
		// TODO Auto-generated method stub

	}

}
