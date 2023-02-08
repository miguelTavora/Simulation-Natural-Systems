package fractals;

import graph.SubPlot;
import processing.core.PApplet;
import processing.core.PConstants;

public class LSystemAntiSnow implements IProcessingApp{
	
	private LSystem lsys;
	private Turtle turtle;
	private double[] startPos = { 0, 8.4 };

	private double[] window = { -1, 1, 0, 15 };
	private float[] viewport = { 0f, 0f, 1f, 1f };
	private SubPlot plot;
	private int count;

	@Override
	public void setup(PApplet p) {
		plot = new SubPlot(window, viewport, p.width, p.height);
		Rule[] ruleset = new Rule[1];
		ruleset[0] = new Rule('F', "F+F--F+F");
		lsys = new LSystem("F++F++F", ruleset);
		turtle = new Turtle(p, plot, 2f, PApplet.radians(60), false);
		count = 0;
	}

	@Override
	public void draw(PApplet p) {
		turtle.setPose(startPos, (float) Math.PI / 2f);
		p.rotate((float)((2*PConstants.PI/3)+0.52));
		turtle.render(lsys);
		
	}

	@Override
	public void keyPressed(PApplet p) {
	}

	@Override
	public void mousePressed(PApplet p) {
		if (count < 4) {
			if(count == 0) {
				startPos[0] = 0;
				startPos[1] = 8.8;
			}
			else if(count == 1){
				startPos[0] = 0;
				startPos[1] = 9.5;
			}
			else if(count == 2){
				startPos[0] = 0;
				startPos[1] = 10.4;
			}
			else{
				startPos[0] = 0;
				startPos[1] = 11.9;
			}
			p.background(200);
			System.out.println(lsys.getSequence());
			lsys.nextGeneration();
			turtle.scaling(0.5f);
			count++;
		}
	}

}
