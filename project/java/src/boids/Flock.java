package boids;

import java.util.ArrayList;

import graph.SubPlot;
import particles.Fires;
import processing.core.PApplet;
import processing.core.PVector;

public class Flock {

	private ArrayList<Boid> boids;
	private ArrayList<Boid> boids2;
	private ArrayList<Boid> boids3;
	private ArrayList<Boid> boids4;
	private ArrayList<Boid> boids5;

	private float pos1;
	private float pos2;
	private int blueStart;

	private ArrayList<Boid> boidsCows1;
	private ArrayList<Boid> boidsCows2;
	private ArrayList<PVector> firePos;

	private Fires fires;
	private Fires fires2;
	private ArrayList<PVector> fireCollision;

	private ArrayList<Integer> timeCows1;
	private ArrayList<Integer> timeCows2;

	private FireMan truck;
	private ArrayList<PVector> fireTruckPos;

	public Flock(int nboids, float radius, float mass, PApplet p, SubPlot plt) {

		boids = new ArrayList<Boid>();
		boids2 = new ArrayList<Boid>();
		boids3 = new ArrayList<Boid>();
		boids4 = new ArrayList<Boid>();
		boids5 = new ArrayList<Boid>();
		fires = new Fires(40);
		fires2 = new Fires("2", 40);
		fireCollision = new ArrayList<PVector>();
		firePos = new ArrayList<PVector>();
		fireTruckPos = new ArrayList<PVector>();
		truck = new FireMan(p, mass, radius, plt);

		blueStart = 0;

		for (int i = 0; i < nboids; i++) {
			int color1 = (int) p.random(0, 255);
			int color2 = (int) p.random(0, 255);
			int color3 = (int) p.random(0, 255);
			int location = (int) p.random(1, 6);
			if (location == 1) {
				float pos = p.random(0, 1);
				Boid b = new Boid(new PVector(-9.7f, 6f + pos), mass, radius, p.color(color1, color2, color3),
						plt.getWindow());

				b.setShape(p, plt);
				boids.add(b);

			} else if (location == 2) {
				float pos = p.random(0f, 0.8f);
				Boid b = new Boid(new PVector(9.7f, 7.7f + pos), mass, radius, p.color(color1, color2, color3),
						plt.getWindow());
				float vx = p.random(-1, 1);
				float vy = p.random(-1, 1);

				b.setVel(new PVector(vx, vy));
				b.setShape(p, plt);
				boids2.add(b);

			} else if (location == 3) {
				float pos = p.random(0f, 0.9f);
				Boid b = new Boid(new PVector(-1.5f + pos, -9.7f), mass, radius, p.color(color1, color2, color3),
						plt.getWindow());
				float vx = p.random(-1, 1);
				float vy = p.random(-1, 1);

				b.setVel(new PVector(vx, vy));
				b.setShape(p, plt);
				boids3.add(b);
			} else if (location == 4) {
				float pos = p.random(0f, 1.2f);

				Boid b = new Boid(new PVector(-9.8f, -5.5f + pos), mass, radius, p.color(color1, color2, color3),
						plt.getWindow(), "under");
				float vx = p.random(-1, 1);
				float vy = p.random(-1, 1);

				b.setVel(new PVector(vx, vy));
				b.setShape(p, plt);
				boids4.add(b);
			} else if (location == 5) {
				float pos = p.random(0f, 1.6f);

				Boid b = new Boid(new PVector(9.8f, -3.5f + pos), mass, radius, p.color(color1, color2, color3),
						plt.getWindow(), "under");
				float vx = p.random(-1, 1);
				float vy = p.random(-1, 1);

				b.setVel(new PVector(vx, vy));
				b.setShape(p, plt);
				boids5.add(b);
			}

		}
		if (boids.size() != 0) {
			pos1 = boids.get(0).getPositions().get(0).x + boids.get(0).getPositions().get(0).y;
			pos2 = boids.get(0).getPositions().get(1).x + boids.get(0).getPositions().get(1).y;
		} else if (boids2.size() != 0) {
			pos1 = boids2.get(0).getPositions().get(0).x + boids2.get(0).getPositions().get(0).y;
			pos2 = boids2.get(0).getPositions().get(1).x + boids2.get(0).getPositions().get(1).y;
		} else if (boids3.size() != 0) {
			pos1 = boids3.get(0).getPositions().get(0).x + boids3.get(0).getPositions().get(0).y;
			pos2 = boids3.get(0).getPositions().get(1).x + boids3.get(0).getPositions().get(1).y;
		} else {
			pos1 = boids4.get(0).getPositions().get(0).x + boids3.get(0).getPositions().get(0).y;
			pos2 = boids4.get(0).getPositions().get(1).x + boids3.get(0).getPositions().get(1).y;
		}

	}

	public Flock(int nboids1, int nboids2, float radius, float mass, PApplet p, SubPlot plt) {
		boidsCows1 = new ArrayList<Boid>();
		boidsCows2 = new ArrayList<Boid>();
		timeCows1 = new ArrayList<Integer>();
		timeCows2 = new ArrayList<Integer>();

		for (int i = 0; i < nboids1; i++) {

			float ranY = p.random(-1, 4.5f);
			float ranX = p.random(0.8f, 7.6f);

			Boid b = new Boid(new PVector(ranX, ranY), mass, radius, p.color(255, 255, 255), plt.getWindow());
			b.setShape(p, plt);
			boidsCows1.add(b);
			timeCows1.add((int) p.random(0, 150));
		}

		for (int i = 0; i < nboids2; i++) {

			float ranY = p.random(-1, 4.5f);
			float ranX = p.random(-9.3f, -4.1f);

			Boid b = new Boid(new PVector(ranX, ranY), mass, radius, p.color(255, 255, 255), plt.getWindow());
			b.setShape(p, plt);

			boidsCows2.add(b);
			timeCows2.add((int) p.random(0, 150));
		}

	}

	// aplicacao das 3 forcas necessarias (coesao, separacao e alinhamento)
	public void applyBehaviour() {

		for (Boid b : boids) {
			if (!(b.color < -8355712)) {
				if ((b.getCounter3() == 0 || b.getCounter3() == 2)) {
					ArrayList<Boid> boidsInSight = b.inCone(boids);
					// forca alinhamento
					PVector fa = b.align(boidsInSight);

					// forca de separacao
					PVector fs = b.separation(boidsInSight);
					// soma das 2 forcas em simultaneo
					b.applyForce(fa.add(fs));
				}
			} else if (b.getPos().x < -3.7f || b.getPos().x > 0f) {
				ArrayList<Boid> boidsInSight = b.inCone(boids);
				// forca alinhamento
				PVector fa = b.align(boidsInSight);

				// forca de separacao
				PVector fs = b.separation(boidsInSight);
				// soma das 2 forcas em simultaneo
				b.applyForce(fa.add(fs));
			}

		}

		for (Boid b : boids2) {
			if (!(b.color < -8355712)) {
				if (b.getCounter2() == 0 || b.getCounter2() == 2) {
					ArrayList<Boid> boidsInSight = b.inCone(boids2);
					// forca alinhamento
					PVector fa = b.align(boidsInSight);

					// forca de separacao
					PVector fs = b.separation(boidsInSight);
					// soma das 2 forcas em simultaneo
					b.applyForce(fa.add(fs));
				}
			} else if (b.getPos().x < -3.7f || b.getPos().x > 0f) {
				ArrayList<Boid> boidsInSight = b.inCone(boids2);
				// forca alinhamento
				PVector fa = b.align(boidsInSight);

				// forca de separacao
				PVector fs = b.separation(boidsInSight);
				// soma das 2 forcas em simultaneo
				b.applyForce(fa.add(fs));
			}

		}
		for (Boid b : boids3) {
			if (b.color < -8355712) {
				if (b.getEndPoint() == 0 || b.getEndPoint() == 2) {

					ArrayList<Boid> boidsInSight = b.inCone(boids3);
					// forca alinhamento
					PVector fa = b.align(boidsInSight);

					// forca de separacao
					PVector fs = b.separation(boidsInSight);
					// soma das 2 forcas em simultaneo
					b.applyForce(fa.add(fs));
				}
			} else {
				if (b.getCounter4() == 0 || b.getCounter4() == 2) {

					ArrayList<Boid> boidsInSight = b.inCone(boids3);
					// forca alinhamento
					PVector fa = b.align(boidsInSight);

					// forca de separacao
					PVector fs = b.separation(boidsInSight);
					// soma das 2 forcas em simultaneo
					b.applyForce(fa.add(fs));
				}
			}
		}

		for (Boid b : boids4) {
			ArrayList<Boid> boidsInSight = b.inCone(boids4);
			PVector fa = b.align(boidsInSight);
			PVector fs = b.separation(boidsInSight);
			b.applyForce(fa.add(fs));
		}

		for (Boid b : boids5) {
			ArrayList<Boid> boidsInSight = b.inCone(boids5);
			PVector fa = b.align(boidsInSight);
			PVector fs = b.separation(boidsInSight);
			b.applyForce(fa.add(fs));
		}

	}

	public void cowWander1(PApplet p, float dt, SubPlot plt) {

		for (int i = 0; i < boidsCows1.size(); i++) {
			Boid b = boidsCows1.get(i);
			b.behaviorWander(p, dt, plt, 1);
			b.stopCow(p, dt, plt, timeCows1.get(i));

		}

		for (int i = 0; i < boidsCows2.size(); i++) {
			Boid b = boidsCows2.get(i);
			b.behaviorWander(p, dt, plt, 2);
			b.stopCow(p, dt, plt, timeCows2.get(i));

		}
	}

	public void goThroughCourseUnderLeft(PApplet p, float dt, SubPlot plt, float mass, float radius) {
		int count = 0;
		int index = 0;
		int[] indexes = new int[30];
		for (Boid b : boids5) {
			if (b.getPos().x < -9.6f) {
				indexes[count] = index;
				count++;

			} else
				b.underGoLeft(dt);

		}

		for (int i = 0; i < count; i++) {
			int color1 = (int) p.random(0, 255);
			int color2 = (int) p.random(0, 255);
			int color3 = (int) p.random(0, 255);
			float pos = p.random(0f, 1.2f);

			Boid bo = new Boid(new PVector(9.8f, -3.5f + pos), mass, radius, p.color(color1, color2, color3),
					plt.getWindow(), "under");

			boids5.add(bo);
			bo.setShape(p, plt);

			float vel = p.random(-1.5f, 0);
			bo.setVel(new PVector(vel, 0f));

			if (i == 0)
				boids5.remove(indexes[i]);

			else if (i == 1) {
				if (indexes[i - 1] < indexes[i])
					boids5.remove(indexes[i] - 1);

				else
					boids5.remove(indexes[i]);

			}

			else if (i == 2) {
				if (indexes[i - 1] < indexes[i] && indexes[i - 2] < indexes[i])
					boids5.remove(indexes[i] - i);

				else if (indexes[i - 1] < indexes[i] && indexes[i - 2] > indexes[i]
						|| indexes[i - 1] > indexes[i] && indexes[i - 2] < indexes[i])
					boids5.remove(indexes[i] - 1);

				else
					boids5.remove(indexes[i]);

			} else {
				if (indexes[i] < boids5.size())
					boids5.remove(indexes[i]);
			}
		}
	}

	public void goThroughCourseUnderRight(PApplet p, float dt, SubPlot plt, float mass, float radius) {
		int count = 0;
		int index = 0;
		int[] indexes = new int[30];
		for (Boid b : boids4) {
			if (b.getPos().x > 9.6f) {
				indexes[count] = index;
				count++;

			} else
				b.underGoRight(dt);

		}

		for (int i = 0; i < count; i++) {
			int color1 = (int) p.random(0, 255);
			int color2 = (int) p.random(0, 255);
			int color3 = (int) p.random(0, 255);
			float pos = p.random(0f, 1.3f);

			Boid bo = new Boid(new PVector(-9.8f, -5.5f + pos), mass, radius, p.color(color1, color2, color3),
					plt.getWindow(), "under");

			boids4.add(bo);

			float vel = p.random(0, 1.5f);
			bo.setVel(new PVector(vel, 0f));
			bo.setShape(p, plt);

			if (i == 0)
				boids4.remove(indexes[i]);

			else if (i == 1) {
				if (indexes[i - 1] < indexes[i])
					boids4.remove(indexes[i] - 1);

				else
					boids4.remove(indexes[i]);

			}

			else if (i == 2) {
				if (indexes[i - 1] < indexes[i] && indexes[i - 2] < indexes[i])
					boids4.remove(indexes[i] - i);

				else if (indexes[i - 1] < indexes[i] && indexes[i - 2] > indexes[i]
						|| indexes[i - 1] > indexes[i] && indexes[i - 2] < indexes[i])
					boids4.remove(indexes[i] - 1);

				else
					boids4.remove(indexes[i]);

			} else {
				if (indexes[i] < boids4.size())
					boids4.remove(indexes[i]);
			}
		}

	}

	public void goThroughCourseRight(PApplet p, float dt, SubPlot plt, float mass, float radius) {
		int count = 0;
		int index = 0;
		int[] indexes = new int[30];

		for (Boid b : boids) {
			if (b.color < -8355712) {
				if (b.getPos().x + b.getPos().y > pos1) {
					indexes[count] = index;
					count++;

				} else
					b.leftGoRight(dt);
			} else {
				if (b.getPos().y < b.getPositions().get(7).y) {
					indexes[count] = index;
					count++;

				} else
					b.leftGoDown(dt);
			}

			index++;
		}
		for (int i = 0; i < count; i++) {
			int color1 = (int) p.random(0, 255);
			int color2 = (int) p.random(0, 255);
			int color3 = (int) p.random(0, 255);
			float pos = p.random(0, 1);
			Boid bo = new Boid(new PVector(-9.8f, 6f + pos), mass, radius, p.color(color1, color2, color3),
					plt.getWindow());

			boids.add(bo);
			bo.setShape(p, plt);

			if (i == 0)
				boids.remove(indexes[i]);

			else if (i == 1) {
				if (indexes[i - 1] < indexes[i])
					boids.remove(indexes[i] - 1);

				else
					boids.remove(indexes[i]);

			}

			else if (i == 2) {
				if (indexes[i - 1] < indexes[i] && indexes[i - 2] < indexes[i])
					boids.remove(indexes[i] - i);

				else if (indexes[i - 1] < indexes[i] && indexes[i - 2] > indexes[i]
						|| indexes[i - 1] > indexes[i] && indexes[i - 2] < indexes[i])
					boids.remove(indexes[i] - 1);

				else
					boids.remove(indexes[i]);

			} else {
				if (indexes[i] < boids.size())
					boids.remove(indexes[i]);
			}
		}

	}

	public void goThroughCourseLeft(PApplet p, float dt, SubPlot plt, float mass, float radius) {

		int count = 0;
		int index = 0;
		int[] indexes = new int[30];

		for (Boid b : boids2) {
			if (b.color < -8355712) {
				if (b.getPos().x + b.getPos().y < pos2) {
					indexes[count] = index;
					count++;

				} else
					b.rightGoLeft(dt);

			} else {
				if (b.getPos().y < b.getPositions().get(7).y) {
					indexes[count] = index;
					count++;
				} else {
					b.rightGoDown(dt);
				}
			}
			index++;
		}

		for (int i = 0; i < count; i++) {
			int color1 = (int) p.random(0, 255);
			int color2 = (int) p.random(0, 255);
			int color3 = (int) p.random(0, 255);
			float pos = p.random(0, 0.8f);
			Boid bo = new Boid(new PVector(9.8f, 7.7f + pos), mass, radius, p.color(color1, color2, color3),
					plt.getWindow());

			boids2.add(bo);
			bo.setShape(p, plt);

			if (i == 0)
				boids2.remove(indexes[i]);

			else if (i == 1) {
				if (indexes[i - 1] < indexes[i])
					boids2.remove(indexes[i] - 1);

				else
					boids2.remove(indexes[i]);

			}

			else if (i == 2) {
				if (indexes[i - 1] < indexes[i] && indexes[i - 2] < indexes[i])
					boids2.remove(indexes[i] - i);

				else if (indexes[i - 1] < indexes[i] && indexes[i - 2] > indexes[i]
						|| indexes[i - 1] > indexes[i] && indexes[i - 2] < indexes[i])
					boids2.remove(indexes[i] - 1);

				else
					boids2.remove(indexes[i]);

			} else {
				if (indexes[i] < boids2.size())
					boids2.remove(indexes[i]);
			}

		}
	}

	public void goThroughCourseUp(PApplet p, float dt, SubPlot plt, float mass, float radius) {
		int count = 0;
		int index = 0;
		int[] indexes = new int[30];

		for (Boid b : boids3) {
			if (b.color < -8355712) {
				if (b.getPos().x + b.getPos().y > pos1) {
					indexes[count] = index;
					count++;

				} else {
					double rd = Math.random();
					b.downGoRight2(dt, rd);
				}
			} else {
				if (b.getPos().x < b.getPositions().get(1).x) {
					indexes[count] = index;
					count++;

				} else {
					double rd = Math.random();
					b.downGoLeft2(dt, rd);
				}
			}
			index++;
		}

		for (int i = 0; i < count; i++) {
			int color1 = (int) p.random(0, 255);
			int color2 = (int) p.random(0, 255);
			int color3 = (int) p.random(0, 255);
			float pos = p.random(0, 0.9f);
			Boid bo = new Boid(new PVector(-1.5f + pos, -9.7f), mass, radius, p.color(color1, color2, color3),
					plt.getWindow());

			boids3.add(bo);
			bo.setShape(p, plt);

			if (i == 0)
				boids3.remove(indexes[i]);

			else if (i == 1) {
				if (indexes[i - 1] < indexes[i])
					boids3.remove(indexes[i] - 1);

				else
					boids3.remove(indexes[i]);

			}

			else if (i == 2) {
				if (indexes[i - 1] < indexes[i] && indexes[i - 2] < indexes[i])
					boids3.remove(indexes[i] - i);

				else if (indexes[i - 1] < indexes[i] && indexes[i - 2] > indexes[i]
						|| indexes[i - 1] > indexes[i] && indexes[i - 2] < indexes[i])
					boids3.remove(indexes[i] - 1);

				else
					boids3.remove(indexes[i]);

			} else {
				if (indexes[i] < boids3.size())
					boids3.remove(indexes[i]);
			}

		}
	}

	public void outOfLine(PApplet p, SubPlot plt) {
		int index = 0;
		int count = 0;
		int[] out = new int[10];

		int index2 = 0;
		int count2 = 0;
		int[] out2 = new int[10];

		int index3 = 0;
		int count3 = 0;
		int[] out3 = new int[10];

		int index4 = 0;
		int count4 = 0;
		int[] out4 = new int[10];

		int index5 = 0;
		int count5 = 0;
		int[] out5 = new int[10];
		boolean outOf = false;

		for (Boid b : boids2) {
			if (b.getPos().y > 9) {
				out[count] = index;
				count++;
				outOf = true;
			}
			index++;
		}
		for (int i = 0; i < count; i++) {
			if (i == 0) {
				firePos.add(boids2.get(out[i]).getPos());
				boids2.remove(out[i]);
			}

			else if (i == 1) {
				if (out[i - 1] < out[i]) {
					firePos.add(boids2.get(out[i] - 1).getPos());
					boids2.remove(out[i] - 1);

				} else {
					if (out[i] == boids2.size()) {
						firePos.add(boids2.get(out[i] - 1).getPos());
						boids2.remove(out[i] - 1);
					} else if (out[i] < boids2.size()) {
						firePos.add(boids2.get(out[i]).getPos());
						boids2.remove(out[i]);
					}
				}

			} else if (i == 2) {
				if (out[i - 1] < out[i] && out[i - 2] < out[i]) {
					firePos.add(boids2.get(out[i] - i).getPos());
					boids2.remove(out[i] - i);
				}

				else if (out[i - 1] < out[i] && out[i - 2] > out[i] || out[i - 1] > out[i] && out[i - 2] < out[i]) {
					firePos.add(boids2.get(out[i] - 1).getPos());
					boids2.remove(out[i] - 1);
				}

				else {
					if (out[i] == boids2.size()) {
						firePos.add(boids2.get(out[i] - 1).getPos());
						boids2.remove(out[i] - 1);
					} else if (out[i] < boids2.size()) {
						firePos.add(boids2.get(out[i]).getPos());
						boids2.remove(out[i]);
					}
				}

			} else {
				if (out[i] < boids2.size()) {
					firePos.add(boids2.get(out[i]).getPos());
					boids2.remove(out[i]);
				}
			}
		}

		for (Boid b : boids) {
			if ((b.getPos().y < 5.5f && (b.getPos().x < -6.5f && b.getPos().x > -9.5f))
					|| (b.getPos().y < 5.5f && (b.getPos().x < 9.5 && b.getPos().x > 2))) {
				out2[count2] = index2;
				count2++;
				outOf = true;
			}
			index2++;
		}
		for (int i = 0; i < count2; i++) {

			if (i == 0) {
				firePos.add(boids.get(out2[i]).getPos());
				boids.remove(out2[i]);
			}

			else if (i == 1) {
				if (out2[i - 1] < out2[i]) {
					firePos.add(boids.get(out2[i] - 1).getPos());
					boids.remove(out2[i] - 1);

				} else {
					if (out2[i] == boids.size()) {
						firePos.add(boids.get(out2[i] - 1).getPos());
						boids.remove(out2[i] - 1);
					} else if (out2[i] < boids.size()) {
						firePos.add(boids.get(out2[i]).getPos());
						boids.remove(out2[i]);
					}
				}

			} else if (i == 2) {
				if (out2[i - 1] < out2[i] && out2[i - 2] < out2[i]) {
					firePos.add(boids.get(out2[i] - i).getPos());
					boids.remove(out2[i] - i);
				}

				else if (out2[i - 1] < out2[i] && out2[i - 2] > out2[i]
						|| out2[i - 1] > out2[i] && out2[i - 2] < out2[i]) {
					firePos.add(boids.get(out2[i] - 1).getPos());
					boids.remove(out2[i] - 1);
				}

				else {
					if (out2[i] == boids.size()) {
						firePos.add(boids.get(out2[i] - 1).getPos());
						boids.remove(out2[i] - 1);
					} else if (out2[i] < boids.size()) {
						firePos.add(boids.get(out2[i]).getPos());
						boids.remove(out2[i]);
					}
				}

			} else {
				if (out2[i] < boids.size()) {
					firePos.add(boids.get(out2[i]).getPos());
					boids.remove(out2[i]);
				}
			}
		}
		for (Boid b : boids3) {
			if (b.getEndPoint() == 0) {
				if (b.getPos().x > -0.2) {
					out3[count3] = index3;
					count3++;
					outOf = true;
				}
				index3++;
			}
		}
		for (int i = 0; i < count3; i++) {

			if (i == 0) {
				firePos.add(boids3.get(out3[i]).getPos());
				boids3.remove(out3[i]);
			}

			else if (i == 1) {
				if (out3[i - 1] < out3[i]) {
					firePos.add(boids3.get(out3[i] - 1).getPos());
					boids3.remove(out3[i] - 1);

				} else {
					if (out3[i] == boids3.size()) {
						firePos.add(boids3.get(out3[i] - 1).getPos());
						boids3.remove(out3[i] - 1);
					} else if (out3[i] < boids3.size()) {
						firePos.add(boids3.get(out3[i]).getPos());
						boids3.remove(out3[i]);
					}
				}

			} else if (i == 2) {
				if (out3[i - 1] < out3[i] && out3[i - 2] < out3[i]) {
					firePos.add(boids3.get(out3[i] - i).getPos());
					boids3.remove(out3[i] - i);
				}

				else if (out3[i - 1] < out3[i] && out3[i - 2] > out3[i]
						|| out3[i - 1] > out3[i] && out3[i - 2] < out3[i]) {
					firePos.add(boids3.get(out3[i] - 1).getPos());
					boids3.remove(out3[i] - 1);
				}

				else {
					if (out3[i] == boids3.size()) {
						firePos.add(boids3.get(out3[i] - 1).getPos());
						boids3.remove(out3[i] - 1);
					} else if (out3[i] < boids3.size()) {
						firePos.add(boids3.get(out3[i]).getPos());
						boids3.remove(out3[i]);
					}
				}

			} else {
				if (out3[i] < boids3.size()) {
					firePos.add(boids3.get(out3[i]).getPos());
					boids3.remove(out3[i]);
				}
			}
		}

		for (Boid b : boids4) {
			if (b.getPos().y < -5.7f || b.getPos().y > -2.2f) {
				out4[count4] = index4;
				count4++;
				outOf = true;
			}
			index4++;
		}
		for (int i = 0; i < count4; i++) {

			if (i == 0) {
				firePos.add(boids4.get(out4[i]).getPos());
				boids4.remove(out4[i]);

			}

			else if (i == 1) {
				if (out4[i - 1] < out4[i]) {
					firePos.add(boids4.get(out4[i] - 1).getPos());
					boids4.remove(out4[i] - 1);

				} else {
					if (out4[i] == boids4.size()) {
						firePos.add(boids4.get(out4[i] - 1).getPos());
						boids4.remove(out4[i] - 1);
					} else if (out4[i] < boids4.size()) {
						firePos.add(boids4.get(out4[i]).getPos());
						boids4.remove(out4[i]);
					}

				}

			} else if (i == 2) {
				if (out4[i - 1] < out4[i] && out4[i - 2] < out4[i]) {
					firePos.add(boids4.get(out4[i] - i).getPos());
					boids4.remove(out4[i] - i);
				}

				else if (out4[i - 1] < out4[i] && out4[i - 2] > out4[i]
						|| out4[i - 1] > out4[i] && out4[i - 2] < out4[i]) {
					firePos.add(boids4.get(out4[i] - 1).getPos());
					boids4.remove(out4[i] - 1);
				} else {
					if (out4[i] == boids4.size()) {
						firePos.add(boids4.get(out4[i] - 1).getPos());
						boids4.remove(out4[i] - 1);
					} else if (out4[i] < boids4.size()) {
						firePos.add(boids4.get(out4[i]).getPos());
						boids4.remove(out4[i]);
					}
				}

			} else {
				if (out4[i] < boids4.size()) {
					firePos.add(boids4.get(out4[i]).getPos());
					boids4.remove(out4[i]);

				}
			}
		}

		for (Boid b : boids5) {
			if (b.getPos().y > -2.2f || b.getPos().y < -5.7f) {
				out5[count5] = index5;
				count5++;
				outOf = true;
			}
			index5++;
		}
		for (int i = 0; i < count5; i++) {

			if (i == 0) {
				firePos.add(boids5.get(out5[i]).getPos());
				boids5.remove(out5[i]);
			}

			else if (i == 1) {
				if (out5[i - 1] < out5[i]) {
					firePos.add(boids5.get(out5[i] - 1).getPos());
					boids5.remove(out5[i] - 1);

				} else {
					if (out5[i] == boids5.size()) {
						firePos.add(boids5.get(out5[i] - 1).getPos());
						boids5.remove(out5[i] - 1);
					} else if (out5[i] < boids5.size()) {
						firePos.add(boids5.get(out5[i]).getPos());
						boids5.remove(out5[i]);
					}
				}

			} else if (i == 2) {
				if (out5[i - 1] < out5[i] && out5[i - 2] < out5[i]) {
					firePos.add(boids5.get(out5[i] - i).getPos());
					boids5.remove(out5[i] - i);
				}

				else if (out5[i - 1] < out5[i] && out5[i - 2] > out5[i]
						|| out5[i - 1] > out5[i] && out5[i - 2] < out5[i]) {
					firePos.add(boids5.get(out5[i] - 1).getPos());
					boids5.remove(out5[i] - 1);

				}

				else {
					if (out5[i] == boids5.size()) {
						firePos.add(boids5.get(out5[i] - 1).getPos());
						boids5.remove(out5[i] - 1);
					} else if (out5[i] < boids5.size()) {
						firePos.add(boids5.get(out5[i]).getPos());
						boids5.remove(out5[i]);
					}
				}

			} else {
				if (out5[i] < boids5.size()) {
					firePos.add(boids5.get(out5[i]).getPos());
					boids5.remove(out5[i]);
				}
			}
		}
		if (outOf)
			outOfLineFire(p, plt);
	}

	public void outOfLineFire(PApplet p, SubPlot plt) {
		for (PVector pos : firePos) {
			fires.createFire(p, pos);

		}
		for (int i = 0; i < firePos.size(); i++) {
			firePos.remove(0);
		}

	}

	public void displayOutOfLineFire(PApplet p, SubPlot plt) {
		fires.displayFire(p, plt);
	}

	public void accident(PApplet p, SubPlot plt) {

		int count = 0;
		int count2 = 0;
		int count3 = 0;
		int index = 0;
		int index2 = 0;
		int index3 = 0;
		int index4 = 0;
		int index5 = 0;
		int[] indexes = new int[40];
		int[] indexes2 = new int[40];
		int[] indexes3 = new int[40];

		boolean crash = false;

		// guardar posições no arraylist que chocaram
		for (Boid a : boids) {
			for (Boid b : boids2) {
				float difX = a.getPos().x - b.getPos().x;
				float difY = a.getPos().y - b.getPos().y;

				if ((difX < 0.3 && difX > -0.3) && (difY < 0.3 && difY > -0.3)) {
					indexes[count] = index;
					indexes[count + 1] = index2;
					count = count + 2;
					// System.out.println("ACIDENTE RED AND GREEN");
				}
				index2++;
				if (index2 == boids2.size())
					index2 = 0;

			}
			for (Boid b : boids3) {
				float difX = a.getPos().x - b.getPos().x;
				float difY = a.getPos().y - b.getPos().y;

				if ((difX < 0.3 && difX > -0.3) && (difY < 0.3 && difY > -0.3)) {
					indexes2[count2] = index;
					indexes2[count2 + 1] = index3;
					count2 = count2 + 2;
					// System.out.println("ACIDENTE RED AND BLUE");
				}
				index3++;
				if (index3 == boids3.size())
					index3 = 0;
			}
			index++;

		}
		// Para remover os boids
		for (int in = 0; in < count / 2; in++) {

			if (in == 0) {
				fireCollision.add(boids.get(indexes[in * 2]).getPos());
				fireTruckPos.add(boids.get(indexes[in * 2]).getPos());
				boids.remove(indexes[in * 2]);
				boids2.remove(indexes[in * 2 + 1]);
				crash = true;
			}

			else if (in == 1) {
				if (indexes[0] < indexes[in * 2] && indexes[1] < indexes[in * 2 + 1]) {
					fireCollision.add(boids.get(indexes[in * 2] - 1).getPos());
					fireTruckPos.add(boids.get(indexes[in * 2] - 1).getPos());
					boids.remove(indexes[in * 2] - 2);
					boids2.remove(indexes[in * 2 + 1] - 2);
					crash = true;

				} else if (indexes[0] > indexes[in * 2] && indexes[1] < indexes[in * 2 + 1]) {
					fireCollision.add(boids.get(indexes[in * 2]).getPos());
					fireTruckPos.add(boids.get(indexes[in * 2]).getPos());
					boids.remove(indexes[in * 2] - 1);
					boids2.remove(indexes[in * 2 + 1] - 2);
					crash = true;
				} else if (indexes[0] < indexes[in * 2] && indexes[1] > indexes[in * 2 + 1]) {
					fireCollision.add(boids.get(indexes[in * 2] - 1).getPos());
					fireTruckPos.add(boids.get(indexes[in * 2] - 1).getPos());
					boids.remove(indexes[in * 2] - 1);
					boids2.remove(indexes[in * 2 + 1]);
					crash = true;
				} else {
					if (indexes[in * 2] == boids.size() && indexes[in * 2 + 1] == boids2.size()) {
						fireCollision.add(boids.get(indexes[in * 2] - 1).getPos());
						fireTruckPos.add(boids.get(indexes[in * 2] - 1).getPos());
						boids.remove(indexes[in * 2] - 1);
						boids2.remove(indexes[in * 2 + 1] - 1);
						crash = true;
					} else if (indexes[in * 2] == boids.size()) {
						fireCollision.add(boids.get(indexes[in * 2] - 1).getPos());
						fireTruckPos.add(boids.get(indexes[in * 2] - 1).getPos());
						boids.remove(indexes[in * 2] - 1);
						boids2.remove(indexes[in * 2 + 1]);
						crash = true;
					} else if (indexes[in * 2 + 1] == boids2.size()) {
						fireCollision.add(boids.get(indexes[in * 2]).getPos());
						fireTruckPos.add(boids.get(indexes[in * 2]).getPos());
						boids.remove(indexes[in * 2]);
						boids2.remove(indexes[in * 2 + 1] - 1);
						crash = true;
					} else if (indexes[in * 2] < boids.size() && indexes[in * 2 + 1] < boids2.size()) {
						fireCollision.add(boids.get(indexes[in * 2]).getPos());
						fireTruckPos.add(boids.get(indexes[in * 2]).getPos());
						boids.remove(indexes[in * 2]);
						boids2.remove(indexes[in * 2 + 1]);
						crash = true;
					}
				}

			} else if (in == 2) {
				// 2,2
				if (indexes[0] < indexes[in * 2] && indexes[2] < indexes[in * 2] && indexes[1] < indexes[in * 2 + 1]
						&& indexes[3] < indexes[in * 2 + 1]) {
					fireCollision.add(boids.get(indexes[in * 2] - 2).getPos());
					fireTruckPos.add(boids.get(indexes[in * 2] - 2).getPos());
					boids.remove(indexes[in * 2] - 2);
					boids2.remove(indexes[in * 2 + 1] - 2);
					crash = true;
				}
				// 2,1
				else if (indexes[0] < indexes[in * 2] && indexes[2] < indexes[in * 2]
						&& ((indexes[1] < indexes[in * 2 + 1] && indexes[3] > indexes[in * 2 + 1])
								|| (indexes[1] > indexes[in * 2 + 1] && indexes[3] < indexes[in * 2 + 1]))) {
					fireCollision.add(boids.get(indexes[in * 2] - 2).getPos());
					fireTruckPos.add(boids.get(indexes[in * 2] - 2).getPos());
					boids.remove(indexes[in * 2] - 2);
					boids2.remove(indexes[in * 2 + 1] - 1);
					crash = true;
				}
				// 2,0
				else if (indexes[0] < indexes[in * 2] && indexes[2] < indexes[in * 2]
						&& indexes[1] > indexes[in * 2 + 1] && indexes[3] > indexes[in * 2 + 1]) {
					fireCollision.add(boids.get(indexes[in * 2] - 2).getPos());
					fireTruckPos.add(boids.get(indexes[in * 2] - 2).getPos());
					boids.remove(indexes[in * 2] - 2);
					boids2.remove(indexes[in * 2 + 1]);
					crash = true;
				}
				// 1,2
				else if (((indexes[0] > indexes[in * 2] && indexes[2] < indexes[in * 2])
						|| (indexes[0] < indexes[in * 2] && indexes[2] > indexes[in * 2]))
						&& indexes[1] < indexes[in * 2 + 1] && indexes[3] < indexes[in * 2 + 1]) {
					fireCollision.add(boids.get(indexes[in * 2] - 1).getPos());
					fireTruckPos.add(boids.get(indexes[in * 2] - 1).getPos());
					boids.remove(indexes[in * 2] - 1);
					boids2.remove(indexes[in * 2 + 1] - 2);
					crash = true;
				}
				// 1,1
				else if (((indexes[0] > indexes[in * 2] && indexes[2] < indexes[in * 2])
						|| (indexes[0] < indexes[in * 2] && indexes[2] > indexes[in * 2]))
						&& ((indexes[1] > indexes[in * 2 + 1] && indexes[3] < indexes[in * 2 + 1])
								|| indexes[1] < indexes[in * 2 + 1] && indexes[3] > indexes[in * 2 + 1])) {
					fireCollision.add(boids.get(indexes[in * 2] - 1).getPos());
					fireTruckPos.add(boids.get(indexes[in * 2] - 1).getPos());
					boids.remove(indexes[in * 2] - 1);
					boids2.remove(indexes[in * 2 + 1] - 1);
					crash = true;
				}
				// 1,0
				else if (((indexes[0] > indexes[in * 2] && indexes[2] < indexes[in * 2])
						|| (indexes[0] < indexes[in * 2] && indexes[2] > indexes[in * 2]))
						&& indexes[1] > indexes[in * 2 + 1] && indexes[3] > indexes[in * 2 + 1]) {
					fireCollision.add(boids.get(indexes[in * 2] - 1).getPos());
					fireTruckPos.add(boids.get(indexes[in * 2] - 1).getPos());
					boids.remove(indexes[in * 2] - 1);
					boids2.remove(indexes[in * 2 + 1]);
					crash = true;
				}
				// 0,2
				else if (indexes[0] > indexes[in * 2] && indexes[2] > indexes[in * 2]
						&& indexes[1] < indexes[in * 2 + 1] && indexes[3] < indexes[in * 2 + 1]) {
					fireCollision.add(boids.get(indexes[in * 2]).getPos());
					fireTruckPos.add(boids.get(indexes[in * 2]).getPos());
					boids.remove(indexes[in * 2]);
					boids2.remove(indexes[in * 2 + 1] - 2);
					crash = true;
				}
				// 0,1
				else if (indexes[0] > indexes[in * 2] && indexes[2] > indexes[in * 2]
						&& ((indexes[1] > indexes[in * 2 + 1] && indexes[3] < indexes[in * 2 + 1])
								|| indexes[1] < indexes[in * 2 + 1] && indexes[3] > indexes[in * 2 + 1])) {
					fireCollision.add(boids.get(indexes[in * 2]).getPos());
					fireTruckPos.add(boids.get(indexes[in * 2]).getPos());
					boids.remove(indexes[in * 2]);
					boids2.remove(indexes[in * 2 + 1] - 1);
					crash = true;
				}
				// 0,0
				else {
					if (indexes[in * 2] == boids.size() && indexes[in * 2 + 1] == boids2.size()) {
						fireCollision.add(boids.get(indexes[in * 2] - 1).getPos());
						fireTruckPos.add(boids.get(indexes[in * 2] - 1).getPos());
						boids.remove(indexes[in * 2] - 1);
						boids2.remove(indexes[in * 2 + 1] - 1);
						crash = true;
					} else if (indexes[in * 2] == boids.size()) {
						fireCollision.add(boids.get(indexes[in * 2] - 1).getPos());
						fireTruckPos.add(boids.get(indexes[in * 2] - 1).getPos());
						boids.remove(indexes[in * 2] - 1);
						boids2.remove(indexes[in * 2 + 1]);
						crash = true;
					} else if (indexes[in * 2 + 1] == boids2.size()) {
						fireCollision.add(boids.get(indexes[in * 2] - 1).getPos());
						fireTruckPos.add(boids.get(indexes[in * 2] - 1).getPos());
						boids.remove(indexes[in * 2] - 1);
						boids2.remove(indexes[in * 2 + 1]);
						crash = true;
					} else if (indexes[in * 2 + 1] < boids2.size() || indexes[in * 2] < boids.size()) {
						fireCollision.add(boids.get(indexes[in * 2]).getPos());
						fireTruckPos.add(boids.get(indexes[in * 2]).getPos());
						boids.remove(indexes[in * 2]);
						boids2.remove(indexes[in * 2 + 1]);
						crash = true;
					}
				}

			} else {
				if (indexes[in * 2] < boids.size() && indexes[in * 2 + 1] < boids2.size()) {
					boids.remove(indexes[in * 2]);
					boids2.remove(indexes[in * 2 + 1]);
				}
			}
		}

		for (int in = 0; in < count2 / 2; in++) {

			if (in == 0) {
				fireCollision.add(boids.get(indexes2[in * 2]).getPos());
				fireTruckPos.add(boids.get(indexes2[in * 2]).getPos());
				boids.remove(indexes2[in * 2]);
				boids3.remove(indexes2[in * 2 + 1]);
				crash = true;
			}

			else if (in == 1) {
				if (indexes2[0] < indexes2[in * 2] && indexes2[1] < indexes2[in * 2 + 1]) {
					fireCollision.add(boids.get(indexes2[in * 2] - 1).getPos());
					fireTruckPos.add(boids.get(indexes2[in * 2] - 1).getPos());
					boids.remove(indexes2[in * 2] - 1);
					boids3.remove(indexes2[in * 2 + 1] - 1);
					crash = true;

				} else if (indexes2[0] > indexes2[in * 2] && indexes2[1] < indexes2[in * 2 + 1]) {
					fireCollision.add(boids.get(indexes2[in * 2]).getPos());
					fireTruckPos.add(boids.get(indexes2[in * 2]).getPos());
					boids.remove(indexes2[in * 2]);
					boids3.remove(indexes2[in * 2 + 1] - 1);
					crash = true;
				} else if (indexes2[0] < indexes2[in * 2] && indexes2[1] > indexes2[in * 2 + 1]) {
					fireCollision.add(boids.get(indexes2[in * 2] - 1).getPos());
					fireTruckPos.add(boids.get(indexes2[in * 2] - 1).getPos());
					boids.remove(indexes2[in * 2] - 1);
					boids3.remove(indexes2[in * 2 + 1]);
					crash = true;
				} else {
					if (indexes2[in * 2] == boids.size() && indexes2[in * 2 + 1] == boids3.size()) {
						fireCollision.add(boids.get(indexes2[in * 2] - 1).getPos());
						fireTruckPos.add(boids.get(indexes2[in * 2] - 1).getPos());
						boids.remove(indexes2[in * 2] - 1);
						boids3.remove(indexes2[in * 2 + 1] - 1);
						crash = true;
					} else if (indexes2[in * 2] == boids.size()) {
						fireCollision.add(boids.get(indexes2[in * 2] - 1).getPos());
						fireTruckPos.add(boids.get(indexes2[in * 2] - 1).getPos());
						boids.remove(indexes2[in * 2] - 1);
						boids3.remove(indexes2[in * 2 + 1]);
						crash = true;
					} else if (indexes2[in * 2 + 1] == boids3.size()) {
						fireCollision.add(boids.get(indexes2[in * 2]).getPos());
						fireTruckPos.add(boids.get(indexes2[in * 2]).getPos());
						boids.remove(indexes2[in * 2]);
						boids3.remove(indexes2[in * 2 + 1] - 1);
						crash = true;
					} else if (indexes2[in * 2 + 1] < boids3.size() && indexes2[in * 2] < boids.size()) {
						fireCollision.add(boids.get(indexes2[in * 2]).getPos());
						fireTruckPos.add(boids.get(indexes2[in * 2]).getPos());
						boids.remove(indexes2[in * 2]);
						boids3.remove(indexes2[in * 2 + 1]);
						crash = true;
					}

				}

			} else if (in == 2) {
				// 2,2
				if (indexes2[0] < indexes2[in * 2] && indexes2[2] < indexes2[in * 2]
						&& indexes2[1] < indexes2[in * 2 + 1] && indexes2[3] < indexes2[in * 2 + 1]) {
					fireCollision.add(boids.get(indexes2[in * 2] - 2).getPos());
					fireTruckPos.add(boids.get(indexes2[in * 2] - 2).getPos());
					boids.remove(indexes2[in * 2] - 2);
					boids3.remove(indexes2[in * 2 + 1] - 2);
					crash = true;
				}
				// 2,1
				else if (indexes2[0] < indexes2[in * 2] && indexes2[2] < indexes2[in * 2]
						&& ((indexes2[1] < indexes2[in * 2 + 1] && indexes2[3] > indexes2[in * 2 + 1])
								|| (indexes2[1] > indexes2[in * 2 + 1] && indexes2[3] < indexes2[in * 2 + 1]))) {
					fireCollision.add(boids.get(indexes2[in * 2] - 2).getPos());
					fireTruckPos.add(boids.get(indexes2[in * 2] - 2).getPos());
					boids.remove(indexes2[in * 2] - 2);
					boids3.remove(indexes2[in * 2 + 1] - 1);
					crash = true;
				}
				// 2,0
				else if (indexes2[0] < indexes2[in * 2] && indexes2[2] < indexes2[in * 2]
						&& indexes2[1] > indexes2[in * 2 + 1] && indexes2[3] > indexes2[in * 2 + 1]) {
					fireCollision.add(boids.get(indexes2[in * 2] - 2).getPos());
					fireTruckPos.add(boids.get(indexes2[in * 2] - 2).getPos());
					boids.remove(indexes2[in * 2] - 2);
					boids3.remove(indexes2[in * 2 + 1]);
					crash = true;
				}
				// 1,2
				else if (((indexes2[0] > indexes2[in * 2] && indexes2[2] < indexes2[in * 2])
						|| (indexes2[0] < indexes2[in * 2] && indexes2[2] > indexes2[in * 2]))
						&& indexes2[1] < indexes2[in * 2 + 1] && indexes2[3] < indexes2[in * 2 + 1]) {
					fireCollision.add(boids.get(indexes2[in * 2] - 1).getPos());
					fireTruckPos.add(boids.get(indexes2[in * 2] - 1).getPos());
					boids.remove(indexes2[in * 2] - 1);
					boids3.remove(indexes2[in * 2 + 1] - 2);
					crash = true;
				}
				// 1,1
				else if (((indexes2[0] > indexes2[in * 2] && indexes2[2] < indexes2[in * 2])
						|| (indexes2[0] < indexes2[in * 2] && indexes2[2] > indexes2[in * 2]))
						&& ((indexes2[1] > indexes2[in * 2 + 1] && indexes2[3] < indexes2[in * 2 + 1])
								|| indexes2[1] < indexes2[in * 2 + 1] && indexes2[3] > indexes2[in * 2 + 1])) {
					fireCollision.add(boids.get(indexes2[in * 2] - 1).getPos());
					fireTruckPos.add(boids.get(indexes2[in * 2] - 1).getPos());
					boids.remove(indexes2[in * 2] - 1);
					boids3.remove(indexes2[in * 2 + 1] - 1);
					crash = true;
				}
				// 1,0
				else if (((indexes2[0] > indexes2[in * 2] && indexes2[2] < indexes2[in * 2])
						|| (indexes2[0] < indexes2[in * 2] && indexes2[2] > indexes2[in * 2]))
						&& indexes2[1] > indexes2[in * 2 + 1] && indexes2[3] > indexes2[in * 2 + 1]) {
					fireCollision.add(boids.get(indexes2[in * 2] - 1).getPos());
					fireTruckPos.add(boids.get(indexes2[in * 2] - 1).getPos());
					boids.remove(indexes2[in * 2] - 1);
					boids3.remove(indexes2[in * 2 + 1]);
					crash = true;
				}
				// 0,2
				else if (indexes2[0] > indexes2[in * 2] && indexes2[2] > indexes2[in * 2]
						&& indexes2[1] < indexes2[in * 2 + 1] && indexes2[3] < indexes2[in * 2 + 1]) {
					fireCollision.add(boids.get(indexes2[in * 2]).getPos());
					fireTruckPos.add(boids.get(indexes2[in * 2]).getPos());
					boids.remove(indexes2[in * 2]);
					boids3.remove(indexes2[in * 2 + 1] - 2);
					crash = true;
				}
				// 0,1
				else if (indexes2[0] > indexes2[in * 2] && indexes2[2] > indexes2[in * 2]
						&& ((indexes2[1] > indexes2[in * 2 + 1] && indexes2[3] < indexes2[in * 2 + 1])
								|| indexes2[1] < indexes2[in * 2 + 1] && indexes2[3] > indexes2[in * 2 + 1])) {
					fireCollision.add(boids.get(indexes2[in * 2]).getPos());
					fireTruckPos.add(boids.get(indexes2[in * 2]).getPos());
					boids.remove(indexes2[in * 2]);
					boids3.remove(indexes2[in * 2 + 1] - 1);
					crash = true;
				} else {
					if (indexes2[in * 2] == boids.size() && indexes[in * 2 + 1] == boids3.size()) {
						fireCollision.add(boids.get(indexes2[in * 2] - 1).getPos());
						fireTruckPos.add(boids.get(indexes2[in * 2] - 1).getPos());
						boids.remove(indexes2[in * 2] - 1);
						boids3.remove(indexes2[in * 2 + 1] - 1);
						crash = true;
					} else if (indexes2[in * 2] == boids.size()) {
						fireCollision.add(boids.get(indexes2[in * 2] - 1).getPos());
						fireTruckPos.add(boids.get(indexes2[in * 2] - 1).getPos());
						boids.remove(indexes2[in * 2] - 1);
						boids3.remove(indexes2[in * 2 + 1]);
						crash = true;
					} else if (indexes[in * 2 + 1] == boids3.size()) {
						fireCollision.add(boids.get(indexes2[in * 2]).getPos());
						fireTruckPos.add(boids.get(indexes2[in * 2]).getPos());
						boids.remove(indexes2[in * 2]);
						boids3.remove(indexes2[in * 2 + 1] - 1);
						crash = true;
					} else if (indexes2[in * 2] < boids.size() && indexes[in * 2 + 1] < boids3.size()) {
						fireCollision.add(boids.get(indexes2[in * 2]).getPos());
						fireTruckPos.add(boids.get(indexes2[in * 2]).getPos());
						boids.remove(indexes2[in * 2]);
						boids3.remove(indexes2[in * 2 + 1]);
						crash = true;
					}
				}

			} else {
				if (indexes2[in * 2] < boids.size() && indexes2[in * 2 + 1] < boids3.size()) {
					boids.remove(indexes2[in * 2]);
					boids3.remove(indexes2[in * 2 + 1]);
				}
			}
		}

		// Para verde e azul
		for (Boid a : boids2) {
			for (Boid b : boids3) {
				float difX = a.getPos().x - b.getPos().x;
				float difY = a.getPos().y - b.getPos().y;

				if ((difX < 0.3 && difX > -0.3) && (difY < 0.3 && difY > -0.3)) {
					indexes3[count3] = index4;
					indexes3[count3 + 1] = index5;
					count3 = count3 + 2;
					// System.out.println("ACIDENTE GREEN AND BLUE");
				}
				index5++;
				if (index5 == boids3.size())
					index5 = 0;

			}
			index4++;
		}

		for (int in = 0; in < count3 / 2; in++) {

			if (in == 0) {
				fireCollision.add(boids2.get(indexes3[in * 2]).getPos());
				fireTruckPos.add(boids2.get(indexes3[in * 2]).getPos());
				boids2.remove(indexes3[in * 2]);
				boids3.remove(indexes3[in * 2 + 1]);
				crash = true;
			}

			else if (in == 1) {
				if (indexes3[0] < indexes3[in * 2] && indexes3[1] < indexes3[in * 2 + 1]) {
					fireCollision.add(boids2.get(indexes3[in * 2] - 1).getPos());
					fireTruckPos.add(boids2.get(indexes3[in * 2] - 1).getPos());
					boids2.remove(indexes3[in * 2] - 1);
					boids3.remove(indexes3[in * 2 + 1] - 1);
					crash = true;

				} else if (indexes3[0] > indexes3[in * 2] && indexes3[1] < indexes3[in * 2 + 1]) {
					fireCollision.add(boids2.get(indexes3[in * 2]).getPos());
					fireTruckPos.add(boids2.get(indexes3[in * 2]).getPos());
					boids2.remove(indexes3[in * 2]);
					boids3.remove(indexes3[in * 2 + 1] - 1);
					crash = true;
				} else if (indexes3[0] < indexes3[in * 2] && indexes3[1] > indexes3[in * 2 + 1]) {
					fireCollision.add(boids2.get(indexes3[in * 2] - 1).getPos());
					fireTruckPos.add(boids2.get(indexes3[in * 2] - 1).getPos());
					boids2.remove(indexes3[in * 2] - 1);
					boids3.remove(indexes3[in * 2 + 1]);
					crash = true;
				} else {
					if (indexes3[in * 2] == boids2.size() && indexes3[in * 2 + 1] == boids3.size()) {
						fireCollision.add(boids2.get(indexes3[in * 2] - 1).getPos());
						fireTruckPos.add(boids2.get(indexes3[in * 2] - 1).getPos());
						boids2.remove(indexes3[in * 2] - 1);
						boids3.remove(indexes3[in * 2 + 1] - 1);
						crash = true;
					} else if (indexes3[in * 2] == boids2.size()) {
						fireCollision.add(boids2.get(indexes3[in * 2] - 1).getPos());
						fireTruckPos.add(boids2.get(indexes3[in * 2] - 1).getPos());
						boids2.remove(indexes3[in * 2] - 1);
						boids3.remove(indexes3[in * 2 + 1]);
						crash = true;
					} else if (indexes3[in * 2 + 1] == boids3.size()) {
						fireCollision.add(boids2.get(indexes3[in * 2]).getPos());
						fireTruckPos.add(boids2.get(indexes3[in * 2]).getPos());
						boids2.remove(indexes3[in * 2]);
						boids3.remove(indexes3[in * 2 + 1] - 1);
						crash = true;
					} else if (indexes3[in * 2 + 1] < boids3.size() && indexes3[in * 2] < boids2.size()) {
						fireCollision.add(boids2.get(indexes3[in * 2]).getPos());
						fireTruckPos.add(boids2.get(indexes3[in * 2]).getPos());
						boids2.remove(indexes3[in * 2]);
						boids3.remove(indexes3[in * 2 + 1]);
						crash = true;
					}

				}

			} else if (in == 2) {
				// 2,2
				if (indexes3[0] < indexes3[in * 2] && indexes3[2] < indexes3[in * 2]
						&& indexes3[1] < indexes3[in * 2 + 1] && indexes3[3] < indexes3[in * 2 + 1]) {
					fireCollision.add(boids2.get(indexes3[in * 2] - 2).getPos());
					fireTruckPos.add(boids2.get(indexes3[in * 2] - 2).getPos());
					boids2.remove(indexes3[in * 2] - 2);
					boids3.remove(indexes3[in * 2 + 1] - 2);
					crash = true;
				}
				// 2,1
				else if (indexes3[0] < indexes3[in * 2] && indexes3[2] < indexes3[in * 2]
						&& ((indexes3[1] < indexes3[in * 2 + 1] && indexes3[3] > indexes3[in * 2 + 1])
								|| (indexes3[1] > indexes3[in * 2 + 1] && indexes3[3] < indexes3[in * 2 + 1]))) {
					fireCollision.add(boids2.get(indexes3[in * 2] - 2).getPos());
					fireTruckPos.add(boids2.get(indexes3[in * 2] - 2).getPos());
					boids2.remove(indexes3[in * 2] - 2);
					boids3.remove(indexes3[in * 2 + 1] - 1);
					crash = true;
				}
				// 2,0
				else if (indexes3[0] < indexes3[in * 2] && indexes3[2] < indexes3[in * 2]
						&& indexes3[1] > indexes3[in * 2 + 1] && indexes3[3] > indexes3[in * 2 + 1]) {
					fireCollision.add(boids2.get(indexes3[in * 2] - 2).getPos());
					fireTruckPos.add(boids2.get(indexes3[in * 2] - 2).getPos());
					boids2.remove(indexes3[in * 2] - 2);
					boids3.remove(indexes3[in * 2 + 1]);
					crash = true;
				}
				// 1,2
				else if (((indexes3[0] > indexes3[in * 2] && indexes3[2] < indexes3[in * 2])
						|| (indexes3[0] < indexes3[in * 2] && indexes3[2] > indexes3[in * 2]))
						&& indexes3[1] < indexes3[in * 2 + 1] && indexes3[3] < indexes3[in * 2 + 1]) {
					fireCollision.add(boids2.get(indexes3[in * 2] - 1).getPos());
					fireTruckPos.add(boids2.get(indexes3[in * 2] - 1).getPos());
					boids2.remove(indexes3[in * 2] - 1);
					boids3.remove(indexes3[in * 2 + 1] - 2);
					crash = true;
				}
				// 1,1
				else if (((indexes3[0] > indexes3[in * 2] && indexes3[2] < indexes3[in * 2])
						|| (indexes3[0] < indexes3[in * 2] && indexes3[2] > indexes3[in * 2]))
						&& ((indexes3[1] > indexes3[in * 2 + 1] && indexes3[3] < indexes3[in * 2 + 1])
								|| indexes3[1] < indexes3[in * 2 + 1] && indexes3[3] > indexes3[in * 2 + 1])) {
					fireCollision.add(boids2.get(indexes3[in * 2] - 1).getPos());
					fireTruckPos.add(boids2.get(indexes3[in * 2] - 1).getPos());
					boids2.remove(indexes3[in * 2] - 1);
					boids3.remove(indexes3[in * 2 + 1] - 1);
					crash = true;
				}
				// 1,0
				else if (((indexes3[0] > indexes3[in * 2] && indexes3[2] < indexes3[in * 2])
						|| (indexes3[0] < indexes3[in * 2] && indexes3[2] > indexes3[in * 2]))
						&& indexes3[1] > indexes3[in * 2 + 1] && indexes3[3] > indexes3[in * 2 + 1]) {
					fireCollision.add(boids2.get(indexes3[in * 2] - 1).getPos());
					fireTruckPos.add(boids2.get(indexes3[in * 2] - 1).getPos());
					boids2.remove(indexes3[in * 2] - 1);
					boids3.remove(indexes3[in * 2 + 1]);
					crash = true;
				}
				// 0,2
				else if (indexes3[0] > indexes3[in * 2] && indexes3[2] > indexes3[in * 2]
						&& indexes3[1] < indexes3[in * 2 + 1] && indexes3[3] < indexes3[in * 2 + 1]) {
					fireCollision.add(boids2.get(indexes3[in * 2]).getPos());
					fireTruckPos.add(boids2.get(indexes3[in * 2]).getPos());
					boids2.remove(indexes3[in * 2]);
					boids3.remove(indexes3[in * 2 + 1] - 2);
					crash = true;
				}
				// 0,1
				else if (indexes3[0] > indexes3[in * 2] && indexes3[2] > indexes3[in * 2]
						&& ((indexes3[1] > indexes3[in * 2 + 1] && indexes3[3] < indexes3[in * 2 + 1])
								|| indexes3[1] < indexes3[in * 2 + 1] && indexes3[3] > indexes3[in * 2 + 1])) {
					fireCollision.add(boids2.get(indexes3[in * 2]).getPos());
					fireTruckPos.add(boids2.get(indexes3[in * 2]).getPos());
					boids2.remove(indexes3[in * 2]);
					boids3.remove(indexes3[in * 2 + 1] - 1);
					crash = true;
				} else {
					if (boids2.size() == indexes3[in * 2] && boids3.size() == indexes3[in * 2 + 1]) {
						fireCollision.add(boids2.get(indexes3[in * 2] - 1).getPos());
						fireTruckPos.add(boids2.get(indexes3[in * 2] - 1).getPos());
						boids2.remove(indexes3[in * 2] - 1);
						boids3.remove(indexes3[in * 2 + 1] - 1);
						crash = true;
					} else if (boids2.size() == indexes3[in * 2]) {
						fireCollision.add(boids2.get(indexes3[in * 2] - 1).getPos());
						fireTruckPos.add(boids2.get(indexes3[in * 2] - 1).getPos());
						boids2.remove(indexes3[in * 2] - 1);
						boids3.remove(indexes3[in * 2 + 1]);
						crash = true;
					} else if (boids3.size() == indexes3[in * 2 + 1]) {
						fireCollision.add(boids2.get(indexes3[in * 2]).getPos());
						fireTruckPos.add(boids2.get(indexes3[in * 2]).getPos());
						boids2.remove(indexes3[in * 2]);
						boids3.remove(indexes3[in * 2 + 1] + 1);
						crash = true;
					} else if (boids2.size() < indexes3[in * 2] && boids3.size() < indexes3[in * 2 + 1]) {
						fireCollision.add(boids2.get(indexes3[in * 2]).getPos());
						fireTruckPos.add(boids2.get(indexes3[in * 2]).getPos());
						boids2.remove(indexes3[in * 2]);
						boids3.remove(indexes3[in * 2 + 1]);
						crash = true;
					}

				}

			} else {
				if (indexes3[in * 2] < boids2.size() && indexes3[in * 2 + 1] < boids3.size()) {
					boids2.remove(indexes3[in * 2]);
					boids3.remove(indexes3[in * 2 + 1]);
				}
			}
		}
		blueStart++;
		if (blueStart > 150) {
			accidentBlue();
		}

		accidentParalela(p, plt);

		if (crash)
			accidentTruckCall(p, plt);

		if (crash)
			createFire(p, plt);
	}

	public void createFire(PApplet p, SubPlot plt) {
		for (PVector pos : fireCollision) {
			fires2.createFire2(p, pos);
		}
		for (int i = 0; i < fireCollision.size(); i++) {
			fireCollision.remove(0);
		}

	}

	public void displayFireCollision(PApplet p, SubPlot plt) {
		fires2.displayFire2(p, plt);
	}

	public void accidentTruckCall(PApplet p, SubPlot plt) {
		for (PVector pos : fireTruckPos) {
			if (truck.getSize() == 1) {
				break;
			}
			truck.pickFire(pos, p, plt);

		}
		for (int i = 0; i < fireTruckPos.size(); i++) {
			fireTruckPos.remove(0);
		}

	}

	public void displayFireTruck(PApplet p, SubPlot plt, float dt) {
		truck.chaseFire(p, plt, dt);
	}

	public void accidentBlue() {

		int index = 0;
		int index2 = 0;
		int count = 0;
		int[] indexes = new int[40];

		for (Boid a : boids3) {
			for (Boid b : boids3) {
				if (index != index2) {
					float difX = a.getPos().x - b.getPos().x;
					float difY = a.getPos().y - b.getPos().y;

					if ((difX < 0.15 && difX > -0.15) && (difY < 0.15 && difY > -0.15)) {
						indexes[count] = index;
						count++;
					}
				}
				index2++;

			} // end for boids3
			index++;

		}

		for (int in = 0; in < count; in++) {

			if (in == 0) {
				fireCollision.add(boids3.get(indexes[in]).getPos());
				boids3.remove(indexes[in]);
			}

			else {
				if (indexes[in] < boids5.size()) {
					firePos.add(boids5.get(indexes[in]).getPos());
					boids5.remove(indexes[in]);
				}
			}

		}
	}

	public void accidentParalela(PApplet p, SubPlot plt) {

		int count = 0;

		int index0 = 0;
		int index1 = 0;

		int[] indexes = new int[40];

		boolean crash = false;

		for (Boid a : boids4) {
			for (Boid b : boids5) {
				float difX = a.getPos().x - b.getPos().x;
				float difY = a.getPos().y - b.getPos().y;

				if ((difX < 0.3 && difX > -0.3) && (difY < 0.3 && difY > -0.3)) {
					indexes[count] = index0;
					indexes[count + 1] = index1;
					count = count + 2;
					// System.out.println("ACIDENTE GREEN AND BLUE");
				}
				index1++;
				if (index1 == boids5.size())
					index1 = 0;

			}
			index0++;
		}
		// destruir
		for (int in = 0; in < count / 2; in++) {
			if (indexes[in * 2] < boids4.size() && indexes[in * 2 + 1] < boids5.size()) {

				if (in == 0) {
					fireCollision.add(boids4.get(indexes[in * 2]).getPos());
					fireTruckPos.add(boids4.get(indexes[in * 2]).getPos());
					boids4.remove(indexes[in * 2]);
					boids5.remove(indexes[in * 2 + 1]);
					crash = true;
				}

				else if (in == 1) {
					if (indexes[0] < indexes[in * 2] && indexes[1] < indexes[in * 2 + 1]) {
						fireCollision.add(boids4.get(indexes[in * 2] - 1).getPos());
						fireTruckPos.add(boids4.get(indexes[in * 2] - 1).getPos());
						boids4.remove(indexes[in * 2] - 2);
						boids5.remove(indexes[in * 2 + 1] - 2);
						crash = true;

					} else if (indexes[0] > indexes[in * 2] && indexes[1] < indexes[in * 2 + 1]) {
						fireCollision.add(boids4.get(indexes[in * 2]).getPos());
						fireTruckPos.add(boids4.get(indexes[in * 2]).getPos());
						boids4.remove(indexes[in * 2] - 1);
						boids5.remove(indexes[in * 2 + 1] - 2);
						crash = true;
					} else if (indexes[0] < indexes[in * 2] && indexes[1] > indexes[in * 2 + 1]) {
						fireCollision.add(boids4.get(indexes[in * 2] - 1).getPos());
						fireTruckPos.add(boids4.get(indexes[in * 2] - 1).getPos());
						boids4.remove(indexes[in * 2] - 1);
						boids5.remove(indexes[in * 2 + 1]);
						crash = true;
					} else {
						if (indexes[in * 2] == boids4.size() && indexes[in * 2 + 1] == boids5.size()) {
							fireCollision.add(boids4.get(indexes[in * 2] - 1).getPos());
							fireTruckPos.add(boids4.get(indexes[in * 2] - 1).getPos());
							boids4.remove(indexes[in * 2] - 1);
							boids5.remove(indexes[in * 2 + 1] - 1);
							crash = true;
						} else if (indexes[in * 2] == boids4.size()) {
							fireCollision.add(boids4.get(indexes[in * 2] - 1).getPos());
							fireTruckPos.add(boids4.get(indexes[in * 2] - 1).getPos());
							boids4.remove(indexes[in * 2] - 1);
							boids5.remove(indexes[in * 2 + 1]);
							crash = true;
						} else if (indexes[in * 2 + 1] == boids5.size()) {
							fireCollision.add(boids4.get(indexes[in * 2]).getPos());
							fireTruckPos.add(boids4.get(indexes[in * 2]).getPos());
							boids4.remove(indexes[in * 2]);
							boids5.remove(indexes[in * 2 + 1] - 1);
							crash = true;
						} else if (indexes[in * 2] < boids4.size() && indexes[in * 2 + 1] < boids5.size()) {
							fireCollision.add(boids4.get(indexes[in * 2]).getPos());
							fireTruckPos.add(boids4.get(indexes[in * 2]).getPos());
							boids4.remove(indexes[in * 2]);
							boids5.remove(indexes[in * 2 + 1]);
							crash = true;
						}
					}
				} else {
					if (indexes[in * 2] < boids4.size() && indexes[in * 2 + 1] < boids5.size()) {
						boids4.remove(indexes[in * 2]);
						boids5.remove(indexes[in * 2 + 1]);
					}
				}
			}
		}
		if (crash)
			accidentTruckCall(p, plt);

		if (crash)
			createFire(p, plt);
	}

	public void newRed(PApplet p, float mass, float radius, SubPlot plt) {
		int color1 = (int) p.random(0, 255);
		int color2 = (int) p.random(0, 255);
		int color3 = (int) p.random(0, 255);
		float pos = p.random(0, 1);
		Boid bo = new Boid(new PVector(-9.8f, 6f + pos), mass, radius, p.color(color1, color2, color3),
				plt.getWindow());
		addBoids(bo);
		bo.setShape(p, plt);
	}

	public void newGreen(PApplet p, float mass, float radius, SubPlot plt) {
		int color1 = (int) p.random(0, 255);
		int color2 = (int) p.random(0, 255);
		int color3 = (int) p.random(0, 255);
		float pos = p.random(0, 1);
		Boid bo = new Boid(new PVector(9.8f, 7.7f + pos), mass, radius, p.color(color1, color2, color3),
				plt.getWindow());
		addBoids2(bo);
		bo.setShape(p, plt);
	}

	public void newBlue(PApplet p, float mass, float radius, SubPlot plt) {
		int color1 = (int) p.random(0, 255);
		int color2 = (int) p.random(0, 255);
		int color3 = (int) p.random(0, 255);
		float pos = p.random(0, 1);
		Boid bo = new Boid(new PVector(-1.5f + pos, -9.5f), mass, radius, p.color(color1, color2, color3),
				plt.getWindow());
		addBoids3(bo);
		bo.setShape(p, plt);
	}

	public void newUnderRight(PApplet p, float mass, float radius, SubPlot plt) {
		int color1 = (int) p.random(0, 255);
		int color2 = (int) p.random(0, 255);
		int color3 = (int) p.random(0, 255);
		float pos = p.random(0f, 1.3f);

		Boid bo = new Boid(new PVector(-9.8f, -5.5f + pos), mass, radius, p.color(color1, color2, color3),
				plt.getWindow(), "under");

		float vel = p.random(0, 1f);
		bo.setVel(new PVector(vel, 0f));

		boids4.add(bo);
		bo.setShape(p, plt);
	}

	public void newUnderLeft(PApplet p, float mass, float radius, SubPlot plt) {
		int color1 = (int) p.random(0, 255);
		int color2 = (int) p.random(0, 255);
		int color3 = (int) p.random(0, 255);
		float pos = p.random(0f, 1.2f);

		Boid bo = new Boid(new PVector(9.8f, -3.5f + pos), mass, radius, p.color(color1, color2, color3),
				plt.getWindow(), "under");

		float vel = p.random(-1f, 0);
		bo.setVel(new PVector(vel, 0.f));

		boids5.add(bo);
		bo.setShape(p, plt);
	}

	public void newCow(float radius, float mass, PApplet p, SubPlot plt) {

		double[] ww = plt.getWorldCoord(p.mouseX, p.mouseY);
		float x = (float) ww[0];
		float y = (float) ww[1];

		if (y > -2f && y < 5.2f) {

			if (x > 0.2f && x < 9.7f) {
				float rdColor = p.random(0, 1);
				int color;
				if (rdColor > 0.5) {
					color = p.color(25);
				} else {
					color = p.color(220);
				}

				Boid b = new Boid(new PVector(x, y), mass, radius, color, plt.getWindow());
				b.setShape(p, plt);

				boidsCows1.add(b);

				timeCows1.add((int) p.random(0, 150));
				b.setShape(p, plt);
			}

			// segundo quadrante
			else if (x > -9.7f && x < -3.9f) {

				float rdColor = p.random(0, 1);
				int color;
				if (rdColor > 0.5) {
					color = p.color(25);
				} else {
					color = p.color(220);
				}

				Boid b = new Boid(new PVector(x, y), mass, radius, color, plt.getWindow());
				b.setShape(p, plt);

				boidsCows2.add(b);

				timeCows2.add((int) p.random(0, 150));
				b.setShape(p, plt);
			}
		}

	}

	public void display(PApplet p, SubPlot plt) {

		for (Boid b : boids) {
			b.display(p, plt);
		}

		for (Boid b2 : boids2) {
			b2.display(p, plt);
		}

		for (Boid b3 : boids3) {
			b3.display(p, plt);
		}

		for (Boid b4 : boids4) {
			float x = b4.getPos().x;
			if (x < -3.7f || x > 0f) {
				b4.display(p, plt);
			}
		}

		for (Boid b5 : boids5) {
			float x = b5.getPos().x;
			if (x < -3.7f || x > 0f) {
				b5.display(p, plt);
			}
		}

	}

	public void displayCow(PApplet p, SubPlot plt) {
		for (Boid cow : boidsCows1)
			cow.display(p, plt);

		for (Boid cow : boidsCows2)
			cow.display(p, plt);

	}

	public ArrayList<Boid> getBoids() {
		return boids;
	}

	public void addBoids(Boid boid) {
		boids.add(boid);
	}

	public ArrayList<Boid> getBoids2() {
		return boids2;
	}

	public void addBoids2(Boid boid) {
		boids2.add(boid);
	}

	public ArrayList<Boid> getBoids3() {
		return boids3;
	}

	public void addBoids3(Boid boid) {
		boids3.add(boid);
	}

}
