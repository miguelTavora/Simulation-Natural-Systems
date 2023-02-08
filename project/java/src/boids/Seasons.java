package boids;

import java.util.ArrayList;

import graph.SubPlot;
import particles.ParticleSystem;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Seasons {

	private PImage img;

	private int state;
	private int timer;

	private int faderOut;
	private int faderIn;
	private boolean startFade;
	private boolean changeFade;
	
	private int durationSpring; //600
	private int durationSummer; //1200
	private int durationOutunn; //1800
	private int durationWinter; //2400
	private int durationFade; //40
	

	private ParticleSystem ps;
	private ArrayList<ParticleSystem> pss;

	public Seasons(PApplet p) {
		img = p.loadImage("boids/background2_spring.png");

		timer = 0;
		faderOut = 20;
		faderIn = 240;
		startFade = false;
		changeFade = false;
		p.background(img);

		pss = new ArrayList<ParticleSystem>();
		ps = new ParticleSystem(new PVector(11, 11), new PVector(0, 0), 1, 0.10f, p.color(230), 8f,
				new PVector(-4, 6));
		
		
		durationSpring = 600;
		durationSummer = 1200;
		durationOutunn = 1800;
		durationWinter = 2400;
		durationFade = 40;
	}


	public void estacoes(PApplet p,float dt, SubPlot plt) {

		if (timer > durationOutunn) {
			setWinter(p);

			if (timer == durationWinter - durationFade)
				startFade = true;

			if (timer > durationWinter)
				timer = 0;

		}

		else if (timer > durationSummer) {
			if (timer == durationOutunn - durationFade)
				startFade = true;

			setOutunn(p);
		}

		else if (timer > durationSpring) {
			if (timer == durationSummer - durationFade)
				startFade = true;

			setSummer(p);

		}

		else if (timer > 0) {
			if (timer == durationSpring-durationFade)
				startFade = true;

			setSpring(p);
		}

		
		p.background(img);
		fadeOut(p);
		if (durationOutunn+1 < timer && timer < durationWinter-40) 
			displayParticles(p,dt,plt);
		timer++;
	}

	public void fadeOut(PApplet p) {
		if (startFade) {
			p.pushStyle();
			p.noStroke();
			if (!changeFade) {
				p.fill(0, faderOut);
				p.rect(0, 0, p.width, p.height);
				faderOut = faderOut + 5;

				if (faderOut >= 240) {
					faderOut = 20;
					changeFade = true;
				}
			} else {
				p.fill(0, faderIn);
				p.rect(0, 0, p.width, p.height);
				faderIn = faderIn - 4;

				if (faderIn <= 20) {
					faderIn = 240;
					startFade = false;
					changeFade = false;
				}
			}
			p.popStyle();
		}

	}

	public void displayParticles(PApplet p, float dt, SubPlot plt) {
		for (ParticleSystem ps : pss) {

			PVector f = new PVector(0, -ps.getMass() * 9.8f);
			ps.applyForce(f);
			ps.move(dt);
			ps.display(p, plt);

		}
		ps.move(dt);
		ps.display(p, plt);
	}

	public void setSpring(PApplet p) {
		this.img = p.loadImage("boids/background2_spring.png");
	}

	public void setWinter(PApplet p) {
		this.img = p.loadImage("boids/background2_snow.png");

	}

	public void setSummer(PApplet p) {
		this.img = p.loadImage("boids/background2_summer.png");
	}

	public void setOutunn(PApplet p) {
		this.img = p.loadImage("boids/background2_automn.png");
	}

	public int getEstado() {
		return state;
	}

}
