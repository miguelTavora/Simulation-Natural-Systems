package fractals;

import graph.SubPlot;
import processing.core.PApplet;

public class LSystemFlorest2 implements IProcessingApp {
	
	private LSystem lsys;
	private Turtle turtle;
	private double[] startPos = { -1.6,19 };

	private double[] window = { -1, 1, 0, 15 };
	private float[] viewport = { 0f, 0f, 1f, 1f };
	private SubPlot plot;
	private int[] color;
	private int count;

	@Override
	public void setup(PApplet p) {
		plot = new SubPlot(window, viewport, p.width, p.height);
		Rule[] ruleset = new Rule[1];
		ruleset[0] = new Rule('F', "F[+FF][-FF]F[-F][+F]F");
		lsys = new LSystem("F", ruleset);
		turtle = new Turtle(p, plot, 1f, PApplet.radians(36), false);
		color = new int[3];
		color[0] = 176;
		color[1] = 192;
		color[2] = 220;
		count = 0;
	}

	@Override
	public void draw(PApplet p) {
		turtle.setPoseCenter(startPos,0);
		turtle.renderColor(lsys,color);
	}

	@Override
	public void keyPressed(PApplet p) {
	}

	@Override
	public void mousePressed(PApplet p) {
		if (count < 4) {
			System.out.println(lsys.getSequence());
			lsys.nextGeneration();
			turtle.scaling(0.5f);
			count++;
		}
	}

}
