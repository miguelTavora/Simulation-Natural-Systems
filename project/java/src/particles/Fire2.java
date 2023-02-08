package particles;

import graph.SubPlot;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Fire2 {

	int num;
	float[][] fire;
	private PVector pos;
	private int countTime;
	private boolean createFire;

	public Fire2(PApplet p, PVector pos) {
		p.pushStyle();
		p.rectMode(PConstants.CENTER);
		p.smooth();
		p.noStroke();
		p.popStyle();
		
		num = 500;
		fire = new float[num][12];
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

	void update_fire(PApplet p) {
		for (int i = num - 1; i > 0; i--) {
			for (int fireprop = 0; fireprop < 11; fireprop++) {
				fire[i][fireprop] = fire[i - 1][fireprop];
			}
		}
		for (int flame = 0; flame < num; flame++) {
			if (fire[flame][0] == 1) {
				fire[flame][1] = fire[flame][1] + fire[flame][5] * PApplet.cos(fire[flame][3]);
				fire[flame][2] = fire[flame][2] + fire[flame][5] * PApplet.sin(fire[flame][3]);
			}
			fire[flame][7] += 1;
			if (fire[flame][7] > fire[flame][6]) {
				fire[flame][0] = 0;
			}
		}
	}

	void draw_fire(PApplet p) {
		for (int flame = 0; flame < num; flame++) {
			if (fire[flame][0] == 1) {
				p.fill(fire[flame][9], fire[flame][10], 0, 40);
				p.pushMatrix();
				p.translate(fire[flame][1], fire[flame][2]);
				p.rotate(fire[flame][8]);
				p.rect(0, 0, fire[flame][4], fire[flame][4]);
				p.popMatrix();
			}
		}
	}

	void create_fire(PApplet p, SubPlot plt) {
		float[] pp = plt.getPixelCoord(pos.x, pos.y);
		for (int flame = 0; flame < 200; flame++) {
			fire[flame][0] = 1;
			fire[flame][1] = pp[0];
			fire[flame][2] = pp[1];
			fire[flame][3] = p.random(0, PConstants.PI * 2);// angle
			fire[flame][4] = p.random(4, 8);// size
			fire[flame][5] = p.random(1, 2);// speed
			fire[flame][6] = p.random(10, 15);// maxlife
			fire[flame][7] = 0;// currentlife
			fire[flame][8] = p.random(0, PConstants.TWO_PI);
			fire[flame][9] = p.random(200, 255);// red
			fire[flame][10] = p.random(50, 150);// green
		}
	}
	
	public int getTimer() {
		return this.countTime;
	}

}
