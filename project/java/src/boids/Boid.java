package boids;

import java.util.ArrayList;

import graph.SubPlot;
import physics.Mover;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;

public class Boid extends Mover {

	protected int color;
	private PShape shape;
	protected double[] window;
	private float maxSpeed;
	private float cowMaxSpeed;
	private float maxForce;
	private float maxDistance;
	private float maxAngle;
	protected DNA dna;
	private ArrayList<PVector> positions;
	private ArrayList<PVector> positionsParallel;
	private int endpoint;
	private int counter;
	private int counter2;
	private int counter3;
	private int counter4;
	private int increase;

	private int timerWander;
	private int timerCowStop;
	private PVector posWander;
	private PImage img;

	private int identifier;
	private int timerFire;

	protected Boid(PVector pos, float mass, float radius, int color, double[] window) {

		super(pos, new PVector(), mass, radius);
		this.color = color;
		this.window = window;
		this.dna = new DNA();

		cowMaxSpeed = 3;
		maxSpeed = 6;
		maxForce = 10;
		maxDistance = 3;
		maxAngle = (float) Math.PI;

		endpoint = 0;
		counter = 0;
		counter2 = 0;
		counter3 = 0;
		counter4 = 0;
		increase = 0;

		positions = new ArrayList<PVector>();
		positions.add(new PVector(9.5f, 6.5f));
		positions.add(new PVector(-9.5f, 8f));

		positions.add(new PVector(-1.2f, 7f));// up
		positions.add(new PVector(8f, 6f));// curve
		positions.add(new PVector(5f, 4f));// front

		positions.add(new PVector(-2.3f, 8f));// middle
		positions.add(new PVector(-1.5f, 3f));// middle2
		positions.add(new PVector(-2.7f, -9.5f));// down

		positions.add(new PVector(-3, 6.5f));// virarBLue
		positions.add(new PVector(-3.7f, -2.5f));// virarBLue2

		positions.add(new PVector(-0.7f, 7f));// up2
		positions.add(new PVector(-9f, 5f));// turnLeft
		positions.add(new PVector(-9, 8.3f));// begin2

		positions.add(new PVector(-5f, 6f));// front
		positions.add(new PVector(-3f, 6.5f));// front
		positions.add(new PVector(1f, 7f));// front
		positions.add(new PVector(4f, 6f));// front

	}

	// construtor para os boids da estrada paralela ou que passa por baixo
	protected Boid(PVector pos, float mass, float radius, int color, double[] window, String sr) {
		super(pos, new PVector(), mass, radius);

		this.color = color;
		this.window = window;
		dna = new DNA();

		maxSpeed = 6;
		maxForce = 10;
		maxDistance = 3;
		maxAngle = (float) Math.PI;

		// estrada paralela
		positionsParallel = new ArrayList<PVector>();
		positionsParallel.add(new PVector(9.8f, -4.7f));
		positionsParallel.add(new PVector(-9.8f, -3.5f));

		timerWander = 0;
		timerCowStop = 0;
		posWander = new PVector(-8.2f, 0);

	}

	// contrutor das vacas e dos bombeiros
	protected Boid(PVector pos, float mass, float radius, double[] window, PApplet p, String type, int identifier) {
		super(pos, new PVector(), mass, radius);

		this.window = window;
		dna = new DNA();

		maxSpeed = 6;
		maxForce = 10;
		maxDistance = 3;
		maxAngle = (float) Math.PI;

		positions = new ArrayList<PVector>();
		positions.add(new PVector(9.5f, 6.5f));
		positions.add(new PVector(-9.5f, 8f));

		// estrada paralela
		positionsParallel = new ArrayList<PVector>();
		positionsParallel.add(new PVector(9.8f, -4.7f));
		positionsParallel.add(new PVector(-9.8f, -3.5f));

		if (type.equalsIgnoreCase("truck1"))
			img = p.loadImage("boids/fire-truck.png");

		else if (type.equalsIgnoreCase("truck2"))
			img = p.loadImage("boids/fire-truck2.png");


		this.identifier = identifier;
		timerFire = 0;
	}

	public void vacaPrimeiroQuadrante(float dt, PApplet p) {

		PVector r;
		float x = p.random(0.5f, 8.9f);
		float y = p.random(-1.1f, 4.1f);

		r = this.seek(new PVector(x, y));
		this.applyForce(r);
		this.move(dt);

	}

	public void vacaSegundoQuadrante(float dt, PApplet p) {

		PVector r;
		float x = p.random(-9.3f, -3.1f);
		float y = p.random(-2.8f, 4.1f);

		r = this.seek(new PVector(x, y));
		this.applyForce(r);
		this.move(dt);

	}

	public void behaviorWander(PApplet p, float dt, SubPlot plt, int quadrante) {
		PVector f;
		if (timerWander % 50 == 0) {

			if (quadrante == 1) {
				f = new PVector(p.random(1.7f, 7.5f), p.random(0.1f, 2.8f));
				applyForce(f);
				timerWander = 0;
				posWander = f;
			} else if (quadrante == 2) {
				f = new PVector(p.random(-7.9f, -5.1f), p.random(0.1f, 2.8f));
				applyForce(f);
				timerWander = 0;
				posWander = f;
			}
		}

		else {
			f = cowSeek(posWander);
			applyForce(f);

		}

		timerWander++;

	}

	public void stopCow(PApplet p, float dt, SubPlot plt, int indice) {

		if (timerCowStop < indice || timerCowStop > indice + 25) {
			move(dt);
		}

		if (timerCowStop == indice + 60) {
			timerCowStop = 0;
		}

		display(p, plt);// mostrar

		timerCowStop++;
	}

	// estrada paralela
	public void underGoRight(float dt) {
		PVector r;
		r = this.seek(positionsParallel.get(0));
		this.applyForce(r);
		this.move(dt);
	}

	public void underGoLeft(float dt) {
		PVector r;
		r = this.seek(positionsParallel.get(1));
		this.applyForce(r);
		this.move(dt);
	}

	// função para ir em linha reta da esquerda para direita
	public void leftGoRight(float dt) {
		PVector r;
		r = this.seek(positions.get(0));

		this.applyForce(r);
		this.move(dt);
	}

	// função para ir em linha reta da esquerda para direita
	public void rightGoLeft(float dt) {
		PVector r;
		r = this.seek(positions.get(1));

		this.applyForce(r);
		this.move(dt);
	}

	public void downGoRight(float dt) {
		PVector r;

		if (endpoint == 0) {
			r = this.seek(positions.get(2));
			if (this.isClose(positions.get(2), 3f)) {
				endpoint = 1;
			}
			this.applyForce(r);
			this.move(dt);

		}

		else if (endpoint == 1) {
			counter++;
			if (counter > 80) {
				r = this.seek(positions.get(4));
				if (this.isClose(positions.get(4), 4.5f)) {
					endpoint = 2;
					counter = 0;
				}
				this.applyForce(r);
				this.move(dt);
			}
		} else {
			r = this.seek(positions.get(0));
			this.applyForce(r);
			this.move(dt);

		}

	}

	public void downGoRight2(float dt, double random) {
		PVector r;

		if (endpoint == 0) {
			if (random < 0.2) {
				r = this.seek(positions.get(2));
				if (this.isClose(positions.get(2), 3f)) {
					endpoint = 1;
				}
				this.applyForce(r);
				this.move(dt);

			} else if (random < 0.4) {
				r = this.seek(positions.get(13));
				if (this.isClose(positions.get(13), 3f)) {
					endpoint = 1;
				}
				this.applyForce(r);
				this.move(dt);

			} else if (random < 0.6) {
				r = this.seek(positions.get(14));
				if (this.isClose(positions.get(14), 3f)) {
					endpoint = 1;
				}
				this.applyForce(r);
				this.move(dt);

			} else if (random < 0.8) {
				r = this.seek(positions.get(15));
				if (this.isClose(positions.get(15), 3f)) {
					endpoint = 1;
				}
				this.applyForce(r);
				this.move(dt);

			} else {
				r = this.seek(positions.get(16));
				if (this.isClose(positions.get(16), 2.5f)) {
					endpoint = 1;
				}
				this.applyForce(r);
				this.move(dt);
			}
		}

		else if (endpoint == 1) {
			counter++;
			if (counter > 80) {
				r = this.seek(positions.get(4));
				if (this.isClose(positions.get(4), 4.5f)) {
					endpoint = 2;
					counter = 0;
				}
				this.applyForce(r);
				this.move(dt);
			}
		} else {
			r = this.seek(positions.get(0));
			this.applyForce(r);
			this.move(dt);

		}

	}

	public void rightGoDown(float dt) {

		PVector v;

		if (counter2 == 0) {
			v = this.seek(positions.get(5));
			if (this.isClose(positions.get(5), 4.5f)) {
				counter2 = 1;
			}

			this.applyForce(v);
			this.move(dt);

		} else if (counter2 == 1) {
			v = this.seek(positions.get(6));
			if (this.isClose(positions.get(6), 1.4f)) {
				counter2 = 2;
			}
			this.applyForce(v);
			this.move(dt);

		} else {
			v = this.seek(positions.get(7));
			this.applyForce(v);
			this.move(dt);
		}
	}

	public void leftGoDown(float dt) {
		PVector v;

		if (counter3 == 0) {
			v = this.seek(positions.get(8));
			if (this.isClose(positions.get(8), 2.9f)) {
				counter3 = 1;
			}
			this.applyForce(v);
			this.move(dt);

		} else if (counter3 == 1) {
			v = this.seek(positions.get(9));
			if (this.isClose(positions.get(9), 3.1f)) {
				counter3 = 2;
			}
			this.applyForce(v);
			this.move(dt);

		} else if (counter3 == 2) {
			v = this.seek(positions.get(7));
			this.applyForce(v);
			this.move(dt);
		}

	}

	public void downGoLeft(float dt) {
		PVector v;

		// boid vem de baixo e vira direita
		if (counter4 == 0) {
			v = this.seek(positions.get(10));
			if (this.isClose(positions.get(10), 3f))// 7.8f
				counter4 = 1;

			this.applyForce(v);
			this.move(dt);
		}

		else if (counter4 == 1) {
			increase++;
			if (increase > 80) {
				v = this.seek(positions.get(11));
				if (this.isClose(positions.get(11), 6f)) {
					counter4 = 2;
				}
				this.applyForce(v);
				this.move(dt);
			}
		} else {
			v = this.seek(positions.get(12));
			this.applyForce(v);
			this.move(dt);

		}

	}

	public void downGoLeft2(float dt, double random) {
		PVector r;

		// boid vem de baixo e vira direita
		if (counter4 == 0) {

			if (random < 0.2) {
				r = this.seek(positions.get(10));
				if (this.isClose(positions.get(10), 3f))
					counter4 = 1;

				this.applyForce(r);
				this.move(dt);

			} else if (random < 0.4) {
				r = this.seek(positions.get(13));
				if (this.isClose(positions.get(13), 3f)) {
					counter4 = 1;
				}
				this.applyForce(r);
				this.move(dt);

			} else if (random < 0.6) {
				r = this.seek(positions.get(14));
				if (this.isClose(positions.get(14), 3f)) {
					counter4 = 1;
				}
				this.applyForce(r);
				this.move(dt);

			} else if (random < 0.8) {
				r = this.seek(positions.get(15));
				if (this.isClose(positions.get(15), 3f)) {
					counter4 = 1;
				}
				this.applyForce(r);
				this.move(dt);

			} else {
				r = this.seek(positions.get(16));
				if (this.isClose(positions.get(16), 2.5f)) {
					counter4 = 1;
				}
				this.applyForce(r);
				this.move(dt);
			}
		}

		else if (counter4 == 1) {
			increase++;
			if (increase > 80) {
				r = this.seek(positions.get(11));
				if (this.isClose(positions.get(11), 6f)) {
					counter4 = 2;
				}
				this.applyForce(r);
				this.move(dt);
			}
		} else {
			r = this.seek(positions.get(12));
			this.applyForce(r);
			this.move(dt);

		}

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

	public void setShapeImg(PApplet p, SubPlot plt) {

		float[] rr = plt.getPixelVector(radius, radius);

		p.image(img, rr[0] / 2, rr[0] / 2);

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

	public PVector cowSeek(PVector target) {

		PVector vd = PVector.sub(target, pos);
		vd.normalize().mult(cowMaxSpeed);

		return PVector.sub(vd, vel);
	}

	public PVector seek(PVector target) {

		// vector entre o boid esta e o target
		PVector vd = PVector.sub(target, pos);
		// colocar uma velocidade maxima (velocidade desejada
		vd.normalize().mult(maxSpeed);

		// Fs= vd - v
		return PVector.sub(vd, vel);
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

	public boolean isClose(PVector reference, float distance) {

		PVector r = PVector.sub(reference, pos);

		float dt = r.mag();

		if (dt < distance)
			return true;

		else
			return false;

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

	public void displayImg(PApplet p, SubPlot plt) {
		p.pushMatrix();
		float[] pp = plt.getPixelCoord(pos.x, pos.y);
		p.translate(pp[0], pp[1]);
		p.rotate(-vel.heading());
		setShapeImg(p, plt);
		p.popMatrix();

	}

	public DNA getDNA() {
		return dna;
	}

	public ArrayList<PVector> getPositions() {
		return this.positions;
	}

	public int getEndPoint() {
		return this.endpoint;
	}

	public int getCounter2() {
		return this.counter2;
	}

	public int getCounter3() {
		return this.counter3;
	}

	public int getCounter4() {
		return this.counter4;
	}

	public int getIdentifier() {
		return this.identifier;
	}

	public int getTimerFire() {
		return this.timerFire;
	}

	public void increaseTimerFire() {
		this.timerFire++;
	}

}
