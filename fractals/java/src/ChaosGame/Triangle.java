package ChaosGame;

import java.util.Arrays;

import processing.core.PApplet;
import processing.core.PConstants;

public class Triangle implements IProcessingApp2 {

	private float[] pNow = new float[2];
	private int[] pixelCoor;
	private int flag;
	private int change;
	private int verify;

	@Override
	public void setup(PApplet p) {

		pixelCoor = new int[8];
		System.out.println(Arrays.toString(pixelCoor));
		flag = 0;
		change = 0;
		verify = 0;
	}

	@Override
	public void draw(PApplet p, float dt) {

		if (pixelCoor[3] != 0 && change == 0) {
			p.fill(0);
			p.line(pixelCoor[0], pixelCoor[1], pixelCoor[2], pixelCoor[3]);
			change = 1;
		}
		
		if (pixelCoor[5] != 0 && change == 1) {
			if(verify == 0) {
				System.out.println("entra");
				p.triangle(pixelCoor[0], pixelCoor[1], pixelCoor[2], pixelCoor[3], pixelCoor[4], pixelCoor[5]);
				verify = 1;
			}
			for (int i = 0; i < 50; i++) {
				int rnd = (int) p.random(0, 3);
				if (rnd == 0) {
					
					pNow[0] = PApplet.lerp(pNow[0], pixelCoor[0], 0.5f);
					pNow[1] = PApplet.lerp(pNow[1], pixelCoor[1], 0.5f);
					p.stroke(255, 0, 0);
					
				} else if (rnd == 1) {
					
					pNow[0] = PApplet.lerp(pNow[0], pixelCoor[2], 0.5f);
					pNow[1] = PApplet.lerp(pNow[1], pixelCoor[3], 0.5f);
					p.stroke(0, 255, 0);
					
				} else if (rnd == 2) {
					
					pNow[0] = PApplet.lerp(pNow[0], pixelCoor[4], 0.5f);
					pNow[1] = PApplet.lerp(pNow[1], pixelCoor[5], 0.5f);
					p.stroke(0, 0, 255);
					
				}

				p.point(pNow[0], pNow[1]);
			}
		}
		System.out.println(change);
		if(pixelCoor[7] != 0) {
			p.stroke(0);
			for (int i = 0; i < 1000; i++) {
				float oldRnd = -1;
				int rnd = (int) p.random(4);
				if (rnd != oldRnd) {
					switch (rnd) {
					case 0:
						pNow[0] = PApplet.lerp(pNow[0], pixelCoor[0], 0.5f);
						pNow[1] = PApplet.lerp(pNow[1], pixelCoor[1], 0.5f);
						break;
					case 1:
						pNow[0] = PApplet.lerp(pNow[0], pixelCoor[2], 0.5f);
						pNow[1] = PApplet.lerp(pNow[1], pixelCoor[3], 0.5f);
						break;
					case 2:
						pNow[0] = PApplet.lerp(pNow[0], pixelCoor[4], 0.5f);
						pNow[1] = PApplet.lerp(pNow[1], pixelCoor[5], 0.5f);
						
						break;
					case 3:
						pNow[0] = PApplet.lerp(pNow[0], pixelCoor[6], 0.5f);
						pNow[1] = PApplet.lerp(pNow[1], pixelCoor[7], 0.5f);
						break;
					}
					p.point(pNow[0], pNow[1]);
					oldRnd = rnd;
				}
			}
		}
		
		//System.out.println(Arrays.toString(pixelCoor));

	}

	@Override
	public void keyPressed(PApplet p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(PApplet p) {
		if (p.mouseButton == PConstants.LEFT && flag == 0) {
			pixelCoor[0] = p.mouseX;
			pixelCoor[1] = p.mouseY;
			flag = 1;
		}

		if (p.mouseButton == PConstants.RIGHT && flag == 1) {
			pixelCoor[2] = p.mouseX;
			pixelCoor[3] = p.mouseY;
			flag = 2;
		}
		
		if (p.mouseButton == PConstants.LEFT && flag == 2) {
			pixelCoor[4] = p.mouseX;
			pixelCoor[5] = p.mouseY;
			flag = 3;
		}
		
		if (p.mouseButton == PConstants.RIGHT && flag == 3) {
			pixelCoor[6] = p.mouseX;
			pixelCoor[7] = p.mouseY;
			flag = 4;
			change = 2;
			p.background(200);
		}

	}

}