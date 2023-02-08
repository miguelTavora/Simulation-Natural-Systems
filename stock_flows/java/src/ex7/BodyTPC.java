package ex7;


import processing.core.PApplet;
import processing.core.PVector;

public class BodyTPC extends MoverTPC 
{
	private int color;
	
	public BodyTPC(PVector pos, PVector vel, float mass, 
			float radius, int color)
	{
		super(pos, vel, mass, radius);
		this.color = color;
	}
	
	public void display(PApplet p, SubPlotTPC plt)
	{
		p.pushStyle();
		p.fill(color);
		p.noStroke();
		float pp[] = plt.getPixelCoord(pos.x, pos.y);
		float r[] = plt.getPixelVector(radius, radius);
		p.ellipse(pp[0], pp[1], 2*r[0], 2*r[0]);
		p.popStyle();
	}
}

