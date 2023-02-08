package ex8;

import java.util.ArrayList;

import ex8.SubPlot;
import processing.core.PApplet;
import processing.core.PVector;

public class ParticleSystem extends Mover {
	
	private ArrayList<Particle> particles;
	private int color;
	private float lifetime;
	private PVector particleSpeed;

	protected ParticleSystem(PVector pos, PVector vel, float mass, float radius,int color,float lifetime, PVector particleSpeed) {
		super(pos, vel, mass, radius);
		this.color = color;
		this.lifetime = lifetime;
		this.particleSpeed = particleSpeed;
		
		particles = new ArrayList<Particle>();
	}
	private void addParticle() {
		float vx = (float) (particleSpeed.x*(Math.random()-0.5));
		float vy = (float) (particleSpeed.x*(Math.random()-0.5));
		Particle p = new Particle(new PVector(pos.x,pos.y), new PVector(vx,vy), radius, color, lifetime);
		
		particles.add(p);
	}
	
	
	@Override
	public void move(float dt) {
		super.move(dt);
		addParticle();
		for(int i = 0;i<particles.size();i++) {//salta indices este ciclo
			Particle p = particles.get(i);
			p.move(dt);
			if(p.isDead()) particles.remove(i);
					
		}
	}
	
	public void display(PApplet p, SubPlot plt) {
		for ( Particle particle : particles) {
			particle.display(p, plt);
		}
			
	}
	
	
	

}
