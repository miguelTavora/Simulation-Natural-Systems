package fractals;

import graph.SubPlot;
import processing.core.PApplet;

public class LSystemApp2 implements IProcessingApp {

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
		Rule[] ruleset = new Rule[1];
		ruleset[0] = new Rule('F', "F[+FF][-FF]F[-F][+F]F");
		lsys = new LSystem("F", ruleset);
		turtle = new Turtle(p, plot, 3f, PApplet.radians(36), false);
		count = 0;
	}

	@Override
	public void draw(PApplet p) {
		turtle.setPose(startPos,(float) Math.PI / 2f);
		turtle.render(lsys);
	}

	@Override
	public void keyPressed(PApplet p) {
	}

	@Override
	public void mousePressed(PApplet p) {
		if (count < 5) {
			System.out.println(lsys.getSequence());
			lsys.nextGeneration();
			turtle.scaling(0.5f);
			count++;
		}
	}

}
