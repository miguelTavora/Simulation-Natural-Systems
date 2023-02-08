package particles;

import processing.core.PApplet;
import processing.core.PVector;
import graph.SubPlot;
import physics.Mover;

public class Particle extends Mover{
	
	private int color;
	private float lifetime;
	private float timer;

	protected Particle(PVector po, PVector vel, float mass, float radius, int color, float lifetime) {
		super(po, vel, mass, radius);
		this.color = color;
		this.lifetime = lifetime;
		timer = 0; 
		
	}
	
	protected Particle(PVector po, PVector vel,  float radius, int color, float lifetime) {
		super(po, vel,0, radius);
		this.color = color;
		this.lifetime = lifetime;
		timer = 0; 
		
	}
	
	@Override
	public void move(float dt) {
		super.move(dt);
		timer+= dt;
	}
	
	public boolean isDead() {
		return (timer > lifetime);
		
	}
	
	public void display(PApplet p, SubPlot plt) {
		p.pushStyle();
		p.noStroke();
		float alpha = 255 - 255/lifetime*timer;
		p.fill(color, alpha);
		float[] pp = plt.getPixelCoord(pos.x, pos.y);
		float[] r = plt.getPixelVector(radius, radius);
		p.circle(pp[0], pp[1], r[0]);
		p.popStyle();
	}

}
