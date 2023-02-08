package fractals;
import AutomatosCelulares.AutomatoCelularElementarApp;
import AutomatosCelulares.JogoDaVida;
import AutomatosCelulares.RegraDaMinoria;
import ChaosGame.Fern;
import ChaosGame.IProcessingApp2;
import ChaosGame.Triangle;
import fractalsRunFromTime.JsetsApp;
import fractalsRunFromTime.JsetsApp2;
import fractalsRunFromTime.JsetsApp3;
import fractalsRunFromTime.JsetsApp4;
import fractalsRunFromTime.MandelbrotApp;
import processing.core.PApplet;

@SuppressWarnings("unused")
public class ProcessingSetup extends PApplet{

	/*private static LSystemApp lsys;
	private static LSystemApp lsys2;*/
	private static IProcessingApp app;
	private static IProcessingApp2 app2;
	
	
	@Override
	public void settings() {
		size(800, 600);
	}
	
	@Override
	public void setup(){		
		app.setup(this);
		//app2.setup(this);
	}
	
	@Override
	public void draw() {	
		app.draw(this);
		//app2.draw(this, frameRate);
	}

	@Override
	public void mousePressed(){
		app.mousePressed(this);
		//app2.mousePressed(this);
	}
	
	@Override
	public void keyPressed(){
		app.keyPressed(this);
		//app2.keyPressed(this);
	}
		
	public static void main(String[] args) {
		//app = new LSystemAlgae();
		//app = new LSystemApp2();
		//app = new LSystemApp3();
		//app = new LSystemTree();
		//app = new LSystemApp5();
		//app = new LSystemApp6();
		//app = new LSystemApp7();
		//app = new LSystemSnowFlake();
		//app = new LSystemAntiSnow();
		//app = new LSystemKochTriangle();
		//app = new LSystemApp4();
		//app = new LSystemFlorest();
		
		
		//app2 = new Triangle();
		//app2 = new Fern();
		
		
		//app2 = new JsetsApp();
		//app2 = new JsetsApp2();
		//app2 = new MandelbrotApp();
		//app2 = new JsetsApp3();
		//app2 = new JsetsApp4();
		
		
		//app = new AutomatoCelularElementarApp();
		//app = new JogoDaVida();
		app = new RegraDaMinoria();
		
		
		PApplet.main(ProcessingSetup.class);
	}
}
