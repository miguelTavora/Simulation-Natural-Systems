package fractals;

import graph.SubPlot;
import processing.core.PApplet;

public class LSystemApp7 implements IProcessingApp{
	
	private LSystem lsys;
	private Turtle turtle;
	private double[] startPos = { 0, 0 };

	private double[] window = { -1, 1, 0, 15 };
	private float[] viewport = { 0f, 0f, 1f, 1f };
	private SubPlot plot;
	private int count;

	@Override
	public void setup(PApplet p) {
		plot = new SubPlot(window, viewport, p.width, p.height);
		Rule[] ruleset = new Rule[2];
		ruleset[0] = new Rule('F', "G[+F]-F");
		ruleset[1] = new Rule('G',"GG");
		lsys = new LSystem("F", ruleset);
		turtle = new Turtle(p, plot, 8f, PApplet.radians(23), false);
		count = 0;
	}

	@Override
	public void draw(PApplet p) {
		turtle.setPose(startPos, (float) Math.PI / 2f);
		turtle.render(lsys);
	}

	@Override
	public void keyPressed(PApplet p) {
	}

	@Override
	public void mousePressed(PApplet p) {
		if (count < 7) {
			System.out.println(lsys.getSequence());
			lsys.nextGeneration();
			turtle.scaling(0.5f);
			
		}
		count++;
	}

}
