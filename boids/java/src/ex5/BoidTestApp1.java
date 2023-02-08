package ex5;

import graph.SubPlot;
import processing.core.PApplet;
import processing.core.PVector;

public class BoidTestApp1 implements IProcessingApp {

	private double[] window = { -10, 10, -10, 10 };
	private float[] viewport = { 0f, 0f, 1f, 1f };
	private SubPlot plt;
	private Boid b;
	private Boid b2;
	private float radius = 0.3f; // tamanho boid
	private float mass = 1f; // influencia velocidad
	private float speed;
	private float timer;
	private PVector v;
	private PVector v2;
	private boolean flag;

	@Override
	public void setup(PApplet p) {
		plt = new SubPlot(window, viewport, p.width, p.height);
		b = new Boid(new PVector(0, 0), mass, radius, p.color(50,50,200), window);
		b2 = new Boid(new PVector(5, 0), mass, radius, p.color(100,200,15), window);
		b.setShape(p, plt); // formato
		b2.setShape(p, plt);

		speed = 1f;
		timer = 0;

	}

	@Override
	public void draw(PApplet p, float dt) {
		p.background(200);
		

		PVector f ;
		PVector f2;
		if(b.inSight(b2.getPos(), 4, (float)Math.PI)){
			System.out.println("evade");
			b.evade(b2);
		}
		
		
		else if (timer%50 == 0) {
			f = b.wander(p);
			f2 = b2.wander(p);
			b.applyForce(f); // aplica força para ele se mecher
			b2.applyForce(f2);
			timer = 0;
			v = f;
			v2 = f2;
			flag = true;

		} else {
			if (!flag) {
				float value = p.random(-1, 1);
				float value2 = p.random(-1, 1);
				v = new PVector(value, value);
				v2 = new PVector(value2, value2);
				f = b.seek(v);
				b.applyForce(f);
				f2 = b.seek(v2);
				b2.applyForce(f2);
				
			}
			else {
				f = b.seek(v);
				b.applyForce(f);
				f2 =  b.seek(v2);
				b2.applyForce(f2);
			}
		}

		b.move(dt * speed); // mecher
		b.display(p, plt);// mostrar
		
		
		b2.move(dt * speed); // mecher
		b2.display(p, plt);
		
		timer++;

	}

	@Override
	public void keyPressed(PApplet p) {

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
