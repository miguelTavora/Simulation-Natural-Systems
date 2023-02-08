package ex3;

import java.util.ArrayList;

import graph.SubPlot;
import processing.core.PApplet;
import processing.core.PVector;

public class BoidTestApp1 implements IProcessingApp {
	
	private double[] window = {-10,10,-10,10};
	private float[] viewport = {0f,0f,1f,1f};
	private SubPlot plt;
	private Boid b;
	private float radius =0.3f; //tamanho boid
	private float mass = 1f; //influencia velocidade
	private boolean brake=false; //variavel para parar~
	private ArrayList<PVector> positions;
	private int path;

	
	
			

	@Override
	public void setup(PApplet p) {
		plt=new SubPlot(window,viewport, p.width,p.height);
		b=new Boid(new PVector(0,0),mass,radius,p.color(50),window);
		positions = b.definePath();
		b.setShape(p, plt); //formato
		
		path = 0;
		
	}

	@Override
	public void draw(PApplet p, float dt) {
		p.background(200);
		
		PVector f;
		if(path == 0) {
			f=b.seek(positions.get(0));
			
			p.circle(400, 150, 10);
			p.fill(200,0,0);
			if(b.inSight(positions.get(0), 0.5f, (float)Math.PI)) 
				path = 1;
			
		}
		else if(path == 1) {
			f=b.seek(positions.get(1));

			p.circle(120, 420, 10);
			p.fill(14,160,180);
			if(b.inSight(positions.get(1), 0.5f, (float)Math.PI)) 
				path = 2;
		}
		else {
			f=b.seek(positions.get(2));
			
			p.circle(720, 540, 10);
			p.fill(180,15,140);
			if(b.inSight(positions.get(2), 0.5f, (float)Math.PI)) 
				path = 0;	
		}

		
		b.applyForce(f); //aplica força para ele se mecher
		b.move(dt); //mecher
		b.display(p, plt);//mostrar
			
	}

	@Override
	public void keyPressed(PApplet p) {
		brake=!brake;
		
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
