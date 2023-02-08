package boids;

import java.util.ArrayList;

import graph.SubPlot;
import processing.core.PApplet;
import processing.core.PVector;

public class FireMan {

	private ArrayList<Boid> car;
	private PVector pos;

	public FireMan(PApplet p, float mass, float radius, SubPlot plt) {
		car = new ArrayList<Boid>();

	}

	public void createTruck(int place, PApplet p, SubPlot plt) {
		if (place == 1) {
			float pos = p.random(0, 1);
			Boid b = new Boid(new PVector(-9.5f, 6f + pos), 1, 0.3f, plt.getWindow(), p, "truck1", 1);

			b.setShapeImg(p, plt);

			car.add(b);

		} else if (place == 2) {
			float pos = p.random(0f, 0.8f);
			Boid b = new Boid(new PVector(9.5f, 7.7f + pos), 1, 0.3f, plt.getWindow(), p, "truck2", 2);

			b.setShapeImg(p, plt);

			car.add(b);

		} else if (place == 4) {
			float pos = p.random(0f, 1.2f);
			Boid b = new Boid(new PVector(-9.5f, -5.5f + pos), 1, 0.3f, plt.getWindow(), p, "truck1", 4);

			b.setShapeImg(p, plt);

			car.add(b);

		} else if (place == 5) {
			float pos = p.random(0f, 1.2f);
			Boid b = new Boid(new PVector(9.5f, -3.5f + pos), 1, 0.3f, plt.getWindow(), p, "truck2", 5);

			b.setShapeImg(p, plt);

			car.add(b);

		} else {
			System.out.println("erro");
		}
	}

	public void pickFire(PVector pos, PApplet p, SubPlot plt) {
		if (car.size() == 0) {
			if (pos.y > 6f && pos.y < 7.3f) {
				createTruck(1, p, plt);
				this.pos = pos;

			} else if (pos.y >= 7.3f && pos.y < 8.5f) {
				createTruck(2, p, plt);
				this.pos = pos;
				
			} else if (pos.y >= -5.5f && pos.y < -3.9f) {
				createTruck(4, p, plt);
				this.pos = pos;
				
			} else if (pos.y >= -3.9f && pos.y < -1.9f) {
				createTruck(5, p, plt);
				this.pos = pos;
			}
		}
	}

	public void chaseFire(PApplet p, SubPlot plt, float dt) {
		boolean delete = false;
		if (car.size() > 0) {
			if (car.get(0).getIdentifier() == 1) {
				if (car.get(0).getPos().x + 0.55f > pos.x) {
					car.get(0).increaseTimerFire();
					if (car.get(0).getTimerFire() >= 45) {
						car.get(0).leftGoRight(dt);
						if (car.get(0).getPos().x >= 9.5f) {
							delete = true;
						}
					}
				} else
					car.get(0).leftGoRight(dt);

			} else if (car.get(0).getIdentifier() == 2) {
				if (car.get(0).getPos().x + 0.45f < pos.x) {
					car.get(0).increaseTimerFire();
					if (car.get(0).getTimerFire() >= 45) {
						car.get(0).rightGoLeft(dt);
						if (car.get(0).getPos().x <= -9.5f) {
							delete = true;
						}
					}
				} else
					car.get(0).rightGoLeft(dt);
			} else if (car.get(0).getIdentifier() == 4) {
				if (car.get(0).getPos().x + 0.45f > pos.x) {
					car.get(0).increaseTimerFire();
					if (car.get(0).getTimerFire() >= 45) {
						car.get(0).underGoRight(dt);
						if (car.get(0).getPos().x >= 9.5f) {
							delete = true;
						}
					}
				} else
					car.get(0).underGoRight(dt);
			}

			else if (car.get(0).getIdentifier() == 5) {
				if (car.get(0).getPos().x + 0.45f < pos.x) {
					car.get(0).increaseTimerFire();
					if (car.get(0).getTimerFire() >= 45) {
						car.get(0).underGoLeft(dt);
						if (car.get(0).getPos().x <= -9.5f) {
							delete = true;
						}
					}
				} else
					car.get(0).underGoLeft(dt);
			}

			if (delete) {
				car.remove(0);
			}

			showCar(p, plt);
		}
	}

	public void showCar(PApplet p, SubPlot plt) {
		for (Boid b : car) {
			if(b.getIdentifier() == 4) {
				if(b.getPos().x < -4.4f || b.getPos().x > -0.5f) {
					b.displayImg(p, plt);
				}
			}else if(b.getIdentifier() == 5) {
				if(b.getPos().x < -3.3f || b.getPos().x > 0.7f) {
					b.displayImg(p, plt);
				}
			}
			else {
				b.displayImg(p, plt);
			}
			
		}
	}
	
	public int getSize() {
		return this.car.size();
	}

}
