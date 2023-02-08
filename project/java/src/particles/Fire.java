package particles;

import graph.SubPlot;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Fire{

	private float[][] fire;
	private int currentflame, nextflame;
	private PVector pos;
	private int countTime;
	private boolean createFire;

	public Fire(PApplet p,PVector pos) {
		p.pushStyle();
		p.rectMode(PConstants.CENTER);
		p.smooth();
		p.noStroke();
		p.popStyle();
		
		
		fire = new float[100000][15];
		currentflame = 0;
		this.pos = pos;
		countTime = 0;
		createFire = false;
	}

	public void startFire(PApplet p, SubPlot plt) {
		if(!createFire) {
			create_fire(p, plt);
			createFire = true;
		}
		update_fire(p);
		draw_fire(p);
		countTime++;
		
	}

	public void update_fire(PApplet p) {
		for (int flame = 0; flame < 100000; flame++) {
			if (fire[flame][0] == 1) {

				if (p.get((int) fire[flame][1], (int) (fire[flame][2])) > 200) {
					fire[flame][0] = 0;
				}
				fire[flame][1] = fire[flame][1] + fire[flame][5] * PApplet.cos(fire[flame][3]);
				fire[flame][2] = fire[flame][2] + fire[flame][5] * PApplet.sin(fire[flame][3]);
			}
			fire[flame][7] += 1;
			if (fire[flame][7] > fire[flame][6]) {
				fire[flame][0] = 0;
			}
		}
	}

	public void draw_fire(PApplet p) {
		for (int flame = 0; flame < currentflame; flame++) {
			if (fire[flame][0] == 1) {
				p.pushStyle();
				p.fill(fire[flame][9], fire[flame][10], 0, 40);
				p.pushMatrix();
				p.translate(fire[flame][1], fire[flame][2]);
				p.rotate(fire[flame][8]);
				p.rect(0, 0, fire[flame][4], fire[flame][4]);
				p.popMatrix();
				p.popStyle();
			}
		}
	}

	public void create_fire(PApplet p, SubPlot plt) {
		nextflame = currentflame + 10;
		float[] pp = plt.getPixelCoord(pos.x, pos.y);
		for (int flame = currentflame; flame < nextflame; flame++) {
			fire[flame][0] = 1;
			fire[flame][1] = pp[0];//p.mouseX;
			fire[flame][2] = pp[1];//p.mouseY;
			fire[flame][3] = p.random(0, PConstants.PI * 2);// angle
			fire[flame][4] = p.random(4, 8);// size
			fire[flame][5] = p.random(0.5f, 1);// speed
			fire[flame][6] = p.random(10, 20) / fire[flame][5];// maxlife
			fire[flame][7] = 0;// currentlife
			fire[flame][8] = p.random(0, PConstants.TWO_PI);
			fire[flame][9] = p.random(200, 255);// red
			fire[flame][10] = p.random(50, 150);// green
		}
		currentflame = nextflame;
	}
	
	public int getTimer() {
		return this.countTime;
	}

}
