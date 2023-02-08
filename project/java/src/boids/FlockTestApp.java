package boids;

import menu.Menu;

import graph.SubPlot;

import processing.core.PApplet;


public class FlockTestApp implements IProcessingApp {

	private double[] window = { -10, 10, -10, 10 };
	private float[] viewport = { 0f, 0f, 1f, 1f };
	private SubPlot plt;
	private Flock flock;
	private Flock cow;
	private float radius = 0.25f;
	private float mass = 1f;
	private Seasons ssn;
	private Menu menu;

	@Override
	public void setup(PApplet p) {

		plt = new SubPlot(window, viewport, p.width, p.height);

		// TODO numero de boids e vacas
		flock = new Flock(50, radius, mass, p, plt);
		cow = new Flock(5, 5, radius, mass, p, plt);

		ssn = new Seasons(p);

		menu = new Menu(p);

	}

	@Override
	public void draw(PApplet p, float dt) {

		manageDraw(p, dt);

	}

	@Override
	public void keyPressed(PApplet p) {
		if (menu.getState() > 1) {
			// adicionar carros
			if (p.key == 'r' || p.key == 'R') {
				flock.newRed(p, mass, radius, plt);
			}

			else if (p.key == 'g' || p.key == 'G') {
				flock.newGreen(p, mass, radius, plt);
			}

			else if (p.key == 'b' || p.key == 'B') {
				flock.newBlue(p, mass, radius, plt);
			} else if (p.key == 'm' || p.key == 'M') {
				flock.newUnderRight(p, mass, radius, plt);
			} else if (p.key == 'n' || p.key == 'N') {
				flock.newUnderLeft(p, mass, radius, plt);
			}

			else if (p.key == 'a' || p.key == 'A') {
				flock.newRed(p, mass, radius, plt);
				flock.newGreen(p, mass, radius, plt);
				flock.newBlue(p, mass, radius, plt);
				flock.newUnderLeft(p, mass, radius, plt);
				flock.newUnderRight(p, mass, radius, plt);
			}
		}

	}

	@Override
	public void mousePressed(PApplet p) {
		manageMousePressed(p);
	}

	@Override
	public void mouseReleased(PApplet p) {

	}

	public void manageDraw(PApplet p, float dt) {
		if (menu.getState() < 2) {
			menu.manageWindow(p);
		} else {
			ssn.estacoes(p, dt, plt);

			// carros
			flock.goThroughCourseRight(p, dt, plt, mass, radius);
			flock.goThroughCourseLeft(p, dt, plt, mass, radius);
			flock.goThroughCourseUp(p, dt, plt, mass, radius);
			flock.goThroughCourseUnderLeft(p, dt, plt, mass, radius);
			flock.goThroughCourseUnderRight(p, dt, plt, mass, radius);
			flock.displayFireTruck(p, plt, dt);
			flock.applyBehaviour();
			flock.accident(p, plt);
			flock.outOfLine(p, plt);
			flock.displayOutOfLineFire(p, plt);
			flock.displayFireCollision(p, plt);
			flock.display(p, plt);

			// vacas
			cow.cowWander1(p, dt, plt);
			cow.displayCow(p, plt);
		}
	}
	
	public void manageMousePressed(PApplet p) {
		if(menu.getState() < 2) 
			menu.managePressed(p);
		
		else 
			cow.newCow(radius, mass, p, plt);
	}

}
