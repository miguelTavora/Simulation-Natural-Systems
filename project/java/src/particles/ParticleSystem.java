package particles;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import graph.SubPlot;
import physics.Mover;

public class ParticleSystem extends Mover {
	
	private ArrayList<Particle> particles;
	private int color;
	private float lifetime;
	private PVector particleSpeed;
	private int increaser;

	public ParticleSystem(PVector pos, PVector vel, float mass, float radius,int color,float lifetime, PVector particleSpeed) {
		super(pos, vel, mass, radius);
		this.color = color;
		this.lifetime = lifetime;
		this.particleSpeed = particleSpeed;
		
		particles = new ArrayList<Particle>();
		
		increaser = 0;
	}
	private void addParticle() {
		float vx = (float) (particleSpeed.x*Math.random());
		float vy = (float) (particleSpeed.x*Math.random());
		Particle p = new Particle(new PVector(pos.x,pos.y), new PVector(vx,vy), radius, color, lifetime);
		particles.add(p);
	}
	
	
	@Override
	public void move(float dt) {
		super.move(dt);
		if(increaser%3 == 0) {
			addParticle();
		}
		
		for(int i = 0;i<particles.size();i++) {//salta indices este ciclo
			Particle p = particles.get(i);
			p.move(dt);
			if(p.isDead()) particles.remove(i);
					
		}
		increaser++;
	}
	
	public void display(PApplet p, SubPlot plt) {
		for ( Particle particle : particles) {
			particle.display(p, plt);
		}
			
	}
	
	
	

}
