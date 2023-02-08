package menu;

import processing.core.PApplet;
import processing.core.PImage;

public class Menu {
	
	private PImage img;
	private PImage bt1;
	private PImage bt2;
	private PImage bt3;
	private PImage settings;
	private PImage settings2;
	private int state;
	private int music;
	private PlaySong song;
	private boolean painted;
	
	public Menu(PApplet p) {
		img = p.loadImage("menu/menu.png");
		bt1 = p.loadImage("menu/bt1.png");
		bt2 = p.loadImage("menu/bt2.png");
		bt3 = p.loadImage("menu/bt3.png");
		settings = p.loadImage("menu/back_settings.png");
		settings2 = p.loadImage("menu/back_settings2.png");
		
		state = 0;
		music = 0;
		song = new PlaySong();
		painted = false;
	}
	
	/*public void draw(PApplet p, float dt) {
		
		manageWindow(p);
	}*/

	/*public void setup(PApplet p) {
		img = p.loadImage("test/menu.png");
		bt1 = p.loadImage("test/bt1.png");
		bt2 = p.loadImage("test/bt2.png");
		bt3 = p.loadImage("test/bt3.png");
		settings = p.loadImage("test/back_settings.png");
		settings2 = p.loadImage("test/back_settings2.png");
		
		state = 0;
		music = 0;
		song = new PlaySong();
		painted = false;
	}*/


	/*@Override
	public void mousePressed(PApplet p) {
		 managePressed(p);
		
	}*/
	
	public void managePressed(PApplet p) {
		if(state == 0) {
			pressedInteraction(p);
		}
		else if(state == 1) {
			pressedSettings(p);
		}
	}
	
	public void pressedSettings(PApplet p) {
		
		//on na musica
		if(p.mouseX > 574 && p.pmouseX < 588 && p.mouseY >  220 && p.mouseY <  233) {
			p.image(settings2, 0, 0);
			if(music == 0 || music == 1) {
				music = 1;
			}else  {
				music = 3;
			}
		}
		//off na musica
		else if(p.mouseX > 319 && p.pmouseX < 334 && p.mouseY >  218 && p.mouseY <  234) {
			p.image(settings, 0, 0);
			if(music == 0 || music == 1) {
				music = 0;
			}else {
				music = 4;
			}
		}
		//ok
		else if(p.mouseX > 295 && p.pmouseX < 509 && p.mouseY >  365 && p.mouseY <  449) {
			if(music == 1) {
				song.start();
				music = 2;
			}
			else if(music == 4) {
				song.stop_Music();
			}
		}
		//back
		else if(p.mouseX > 329 && p.pmouseX < 479 && p.mouseY >  505 && p.mouseY <  555) {
			state = 0;
			painted = false;
		}
	}
	
	public void pressedInteraction(PApplet p) {
		if(p.mouseX > 262 && p.pmouseX < 554 && p.mouseY >  301 && p.mouseY <  349) {
			state = 2;
		}
		else if(p.mouseX > 262 && p.pmouseX < 554 && p.mouseY >  400 && p.mouseY <  450) {
			state = 1;
		}
		else if(p.mouseX > 262 && p.pmouseX < 554 && p.mouseY >  501 && p.mouseY <  547) {
			System.exit(0);
		}
	}
	
	public void mouseHover(PApplet p) {
		if(p.mouseX > 262 && p.pmouseX < 554 && p.mouseY >  301 && p.mouseY <  349) {
			p.image(bt1, 0, 0);
		}
		else if(p.mouseX > 262 && p.pmouseX < 554 && p.mouseY >  400 && p.mouseY <  450) {
			p.image(bt2, 0, 0);
		}
		else if(p.mouseX > 262 && p.pmouseX < 554 && p.mouseY >  501 && p.mouseY <  547) {
			p.image(bt3, 0, 0);
		}
		
	}
	
	public void manageWindow(PApplet p) {
		if(state == 0) {
			p.image(img, 0, 0);
			mouseHover(p);
			
		}else if(state == 1) {
			if(!painted) {
				p.image(settings,0,0);
				painted = true;
			}
		}
	}
	
	public int getState() {
		return this.state;
	}

}
