package ex4;

import graph.SubPlot;
import processing.core.PApplet;
import processing.core.PVector;

public class BoidTestApp1 implements IProcessingApp {

	private double[] window = { -10, 10, -10, 10 };
	private float[] viewport = { 0f, 0f, 1f, 1f };
	private SubPlot plt;
	private Boid b;
	private float radius = 0.3f; // tamanho boid
	private float mass = 1f; // influencia velocidade
	private boolean brake = false; // variavel para parar~
	private float speed;
	private float timer;
	private PVector v;
	private boolean flag;

	@Override
	public void setup(PApplet p) {
		plt = new SubPlot(window, viewport, p.width, p.height);
		b = new Boid(new PVector(0, 0), mass, radius, p.color(50), window);
		b.setShape(p, plt); // formato

		speed = 1f;
		timer = 0;

	}

	@Override
	public void draw(PApplet p, float dt) {
		p.background(200);
		

		PVector f;
		if (timer%50 == 0) {
			f = b.wander(p);
			b.applyForce(f); // aplica força para ele se mecher
			timer = 0;
			v = f;
			flag = true;

		} else {
			if (!flag) {
				float value = p.random(-1, 1);
				v = new PVector(value, value);
				f = b.seek(v);
				b.applyForce(f);
			}
			else {
				f = b.seek(v);
				b.applyForce(f);
			}
		}

		b.move(dt * speed); // mecher
		b.display(p, plt);// mostrar

		timer++;

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

	}

	@Override
	public void keyReleased(PApplet p) {

	}

}
