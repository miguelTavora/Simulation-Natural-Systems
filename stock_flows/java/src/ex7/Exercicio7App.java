package ex7;

import processing.core.PApplet;
import processing.core.PVector;
import ex7.CelestialBodyTPC;
import ex7.IProcessingTPCApp;
import ex7.SubPlotTPC;

public class Exercicio7App implements IProcessingTPCApp {

	private float sunMass = 1.989e30f;
	private float planetBlueMass = 5.97e24f / 2;
	private float earthMass = 5.97e24f;
	private float planetRedMass = 3.97e24f;
	private float planetYellowMass = (3 * earthMass) / 4;
	private float planetPurpleMass = 15.97e24f;
	private float planetBrownMass = 10.97e24f;

	private float distEarthSun = 1.496e11f;
	private float distplanetBlueSun = distEarthSun / 3;
	private float distplanetYellowSun = distplanetBlueSun * 2;
	private float distplanetRedSun = 1.096e11f;
	private float distplanetPurpleSun = 1.396e11f;
	private float distplanetBrownSun = 1.100e11f;

	private float earthSpeed = 3e4f;
	private float planetBlueSpeed = earthSpeed * 2;
	private float planetYellowSpeed = (3 * planetBlueSpeed) / 4;
	private float planetPurpleSpeed = earthSpeed / 2;
	private float planetBrownSpeed = earthSpeed * 0.7f;

	private double[] window = { -1.2 * distEarthSun, 1.2 * distEarthSun, -1.2 * distEarthSun, 1.2 * distEarthSun };
	private float[] viewport = { 0f, 0f, 1f, 1f };
	private SubPlotTPC plt;
	private CelestialBodyTPC sun, planetBlue, planetYellow, earth, planetRed, planetPurple, planetBrown;
	private float speedUp = 60 * 60 * 24 * 30;

	@Override
	public void setup(PApplet p) {
		plt = new SubPlotTPC(window, viewport, p.width, p.height);
		sun = new CelestialBodyTPC(new PVector(), new PVector(), sunMass, distEarthSun / 10, p.color(255, 128, 0));

		planetBlue = new CelestialBodyTPC(new PVector(0, distplanetBlueSun), new PVector(planetBlueSpeed, 0),
				planetBlueMass, distplanetBlueSun / 10, p.color(102, 153, 255));

		planetYellow = new CelestialBodyTPC(new PVector(0, distplanetYellowSun / 2), new PVector(planetYellowSpeed, 0),
				planetYellowMass, distplanetYellowSun / 20, p.color(255, 204, 0));

		earth = new CelestialBodyTPC(new PVector(0, distEarthSun), new PVector(earthSpeed, 0), earthMass,
				distEarthSun / 20, p.color(0, 204, 0));

		planetRed = new CelestialBodyTPC(new PVector(0, distplanetRedSun), new PVector(earthSpeed, 0), planetRedMass,
				distplanetRedSun / 20, p.color(255, 51, 0));

		planetPurple = new CelestialBodyTPC(new PVector(0, distplanetPurpleSun), new PVector(planetPurpleSpeed, 0),
				planetPurpleMass, distEarthSun / 20, p.color(204, 0, 153));

		planetBrown = new CelestialBodyTPC(new PVector(0, distplanetBrownSun), new PVector(planetBrownSpeed, 0),
				planetBrownMass, distplanetBrownSun / 20, p.color(255, 80, 80));

	}

	@Override
	public void draw(PApplet p, float dt) {
		// TODO mudar speed
		p.fill(255, 16);
		float[] box = plt.getBoundingBox();
		p.rect(box[0], box[1], box[2], box[3]);

		sun.display(p, plt);

		PVector merc = sun.attraction(planetBlue);
		planetBlue.applyForce(merc);
		planetBlue.move(speedUp * dt);
		planetBlue.display(p, plt);

		PVector ven = sun.attraction(planetYellow);
		planetYellow.applyForce(ven);
		planetYellow.move(speedUp * dt);
		planetYellow.display(p, plt);

		PVector f = sun.attraction(earth);
		earth.applyForce(f);
		earth.move(speedUp * dt);
		earth.display(p, plt);

		PVector mar = sun.attraction(planetRed);
		planetRed.applyForce(mar);
		planetRed.move(speedUp * dt);
		planetRed.display(p, plt);

		PVector jup = sun.attraction(planetPurple);
		planetPurple.applyForce(jup);
		planetPurple.move(speedUp * dt);
		planetPurple.display(p, plt);

		PVector bro = sun.attraction(planetBrown);
		planetBrown.applyForce(bro);
		planetBrown.move(speedUp * dt);
		planetBrown.display(p, plt);

	}

	@Override
	public void keyPressed(PApplet p) {
	}

	@Override
	public void mousePressed(PApplet p) {

	}

	public void mouseReleased(PApplet p) {
	}

}
