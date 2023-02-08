package fractals;

import graph.SubPlot;
import processing.core.PApplet;
import processing.core.PConstants;

public class LSystemKochTriangle implements IProcessingApp {

	private LSystem lsys;
	private Turtle turtle;
	private double[] startPos = {-0.1, 7};

	private double[] window = { -1, 1, 0, 15 };
	private float[] viewport = { 0f, 0f, 1f, 1f };
	private SubPlot plot;
	private int count;
	private float random;

	@Override
	public void setup(PApplet p) {
		plot = new SubPlot(window, viewport, p.width, p.height);
		Rule[] ruleset = new Rule[1];
		ruleset[0] = new Rule('F', "F+F-F-F+F");
		lsys = new LSystem("F", ruleset);
		turtle = new Turtle(p, plot, 2f, PApplet.radians(60), false);
		count = 0;
		random = p.random(1);
	}

	@Override
	public void draw(PApplet p) {
		if (random < 0.5) {
			turtle.setPose(startPos, (float) Math.PI / 2f);
			p.rotate(-(PConstants.PI / 2));
			turtle.render(lsys);
		} else {
			turtle.setPose(startPos, (float) Math.PI / 2f);
			p.rotate((PConstants.PI / 2));
			turtle.render(lsys);
		}
	}

	@Override
	public void keyPressed(PApplet p) {
	}

	@Override
	public void mousePressed(PApplet p) {
		if (count < 4) {
			if (random < 0.5) {
				if(count == 0) {
					startPos[0] = 0.2;
					startPos[1] = 7;
				}
				else if(count == 1){
					startPos[0] = 0.4;
					startPos[1] = 7;
				}
				else if(count == 2){
					startPos[0] = 0.8;
					startPos[1] = 7;
				}
				else{
					startPos[0] = 1;
					startPos[1] = 7;
				}
				p.background(200);
				System.out.println(lsys.getSequence());
				lsys.nextGeneration();
				turtle.scaling(0.5f);
				count++;
			}
			else {
				if(count == 0) {
					startPos[0] = -0.2;
					startPos[1] = 7;
				}
				else if(count == 1){
					startPos[0] = -0.4;
					startPos[1] = 7;
				}
				else if(count == 2){
					startPos[0] =-0.8;
					startPos[1] = 7;
				}
				else{
					startPos[0] = -1;
					startPos[1] = 7;
				}
				p.background(200);
				System.out.println(lsys.getSequence());
				lsys.nextGeneration();
				turtle.scaling(0.5f);
				count++;
			}
		}
	}

}
