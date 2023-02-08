package ex1;

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
	private PVector target, targetInPixels;
	private boolean brake=false; //variavel para parar~
	private float speed;
	private int[] colorCircle;
	
	
	
			

	@Override
	public void setup(PApplet p) {
		plt=new SubPlot(window,viewport, p.width,p.height);
		b=new Boid(new PVector(0,0),mass,radius,p.color(50),window);
		b.setShape(p, plt); //formato
		target= new PVector(0,0);
		targetInPixels = new PVector(p.width/2,p.height/2);
		
		speed = 1f;
		colorCircle = new int[3];
		colorCircle[0] = (int) p.random(0,255);
		colorCircle[1] = (int) p.random(0,255);
		colorCircle[2] = (int) p.random(0,255);
		
	}

	@Override
	public void draw(PApplet p, float dt) {
		p.background(200);
		
		
		PVector f;
		if(b.inSight(target, 4, (float)Math.PI)) {//se tiver um ponto ele persegue senão para
			f=b.seek(target);
		}
		
		else {
			f=b.brake();
		}
		
		b.applyForce(f); //aplica força para ele se mecher
		b.move(dt*speed); //mecher
		b.display(p, plt);//mostrar
		
		
		p.circle(targetInPixels.x, targetInPixels.y, 10);//circulo onde se clica no ecrâ
		p.fill(colorCircle[0],colorCircle[1],colorCircle[2]);
		
		
		//if(p.keyReleased()) {
			
		//}
			
	}

	@Override
	public void keyPressed(PApplet p) {
		brake=!brake;
		
		if(p.key == 'w') 
			speed = 4;
		
	}

	@Override
	public void mousePressed(PApplet p) {
		
		double[] ww= plt.getWorldCoord(p.mouseX,p.mouseY);
		target=new PVector((float)ww[0],(float)ww[1]);
		targetInPixels= new PVector(p.mouseX,p.mouseY);
		colorCircle[0] = (int) p.random(0,255);
		colorCircle[1] = (int) p.random(0,255);
		colorCircle[2] = (int) p.random(0,255);
		
	}

	@Override
	public void mouseReleased(PApplet p) {
		
	}

	@Override
	public void keyReleased(PApplet p) {
		speed = 1;
		
	}

}
