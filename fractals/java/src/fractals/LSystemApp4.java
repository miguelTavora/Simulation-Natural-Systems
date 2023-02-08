package fractals;

import graph.SubPlot;
import processing.core.PApplet;
import processing.core.PConstants;

public class LSystemApp4 implements IProcessingApp{
	
	private LSystemStochastic lsys;
	private Turtle turtle;
	private double[] startPos = { -0.23, 4.4 };//coord(400,300)

	private double[] window = { -1, 1, 0, 8 };//-1, 1, -6, 8 
	private float[] viewport = { 0f, 0f, 1f, 1f };
	private SubPlot plot;
	private int count;

	@Override
	public void setup(PApplet p) {
		plot = new SubPlot(window, viewport, p.width, p.height);
		Rule[] ruleset = new Rule[1];
		ruleset[0] = new Rule('F', "F++F++F+++++F-F++F");
		lsys = new LSystemStochastic("F++F++F++F++F", ruleset);
		turtle = new Turtle(p, plot, 1.5f, PApplet.radians(36), false);
		
		
		count = 0;
	}

	@Override
	public void draw(PApplet p) {
		turtle.setPose(startPos, (float) Math.PI / 2f);
		p.rotate(PConstants.PI/3);
		turtle.render(lsys);
	}

	@Override
	public void keyPressed(PApplet p) {
	}

	@Override
	public void mousePressed(PApplet p) {
		if (count < 4) {
			if(count == 0) {
				startPos[0] = -0.3;
				startPos[1] = 4.6;
			}
			else if(count == 1){
				startPos[0] =-0.385;
				startPos[1] = 4.75;
			}
			else if(count == 2){
				startPos[0] = -0.51;
				startPos[1] = 4.95;
			}
			else {
				startPos[0] = -0.66;
				startPos[1] = 5.3;
			}
			p.background(200);
			System.out.println(lsys.getSequence());
			lsys.nextGeneration();
			turtle.scaling(0.5f);
			count++;
		}
	}

}
