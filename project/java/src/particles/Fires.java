package particles;

import java.util.ArrayList;

import graph.SubPlot;
import processing.core.PApplet;
import processing.core.PVector;

public class Fires {
	
	private ArrayList<Fire> fires;
	private ArrayList<Fire2> fires2;
	private int timeBurning;
	
	
	public Fires(int timeBurning) {
		fires = new  ArrayList<Fire>();
		this.timeBurning = timeBurning;
		
	}
	
	public Fires(String fire, int timeBurning) {
		if(fire.equalsIgnoreCase("2")) {
			fires2 = new  ArrayList<Fire2>();
			this.timeBurning = timeBurning;
		}
		
	}
	
	public void createFire(PApplet p,PVector pos) {
		Fire fire = new Fire(p,pos);
		
		fires.add(fire);
	}
	
	public void createFire2(PApplet p,PVector pos) {
		Fire2 fire = new Fire2(p,pos);
		
		fires2.add(fire);
	}
	
	public void displayFire(PApplet p, SubPlot plt) {
		for(Fire fire: fires) {
			fire.startFire(p,plt);
		}
		endFire();
		
		
	}
	
	public void displayFire2(PApplet p, SubPlot plt) {
		for(Fire2 fire: fires2) {
			fire.startFire(p,plt);
		}
		endFire2();
		
		
	}
	
	public void endFire() {
		int index = 0;
		int indexes[] = new int[20];
		int count = 0;
		for(Fire fire: fires) {
			if(fire.getTimer() >= timeBurning) {
				indexes[count] = index;
				count++;
				
			}
			index++;
		}
		for(int i = 0; i< count; i++) {
			if(indexes[i] < fires.size()) {
				fires.remove(indexes[i]);
			}
		}
	}
	
	public void endFire2() {
		int index = 0;
		int indexes[] = new int[70];
		int count = 0;
		for(Fire2 fire: fires2) {
			if(fire.getTimer() >= timeBurning) {
				indexes[count] = index;
				count++;
				
			}
			index++;
		}
		for(int i = 0; i< count; i++) {
			if(indexes[i] < fires2.size()) {
				fires2.remove(indexes[i]);
			}
		}
	}

}
