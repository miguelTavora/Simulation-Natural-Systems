package ex7;

import processing.core.PApplet;
import ex7.Exercicio7App;

public class ProcessingSetupTPC extends PApplet
{
	private static IProcessingTPCApp app;
	private int lastUpdate;
	
	@Override
	public void settings()
	{
		size(800, 600);
	}
	
	@Override
	public void setup()
	{		
		app.setup(this);
		lastUpdate = millis();
	}
	
	@Override
	public void draw()
	{	
		int now = millis();
		float dt = (now - lastUpdate)/1000f;
		lastUpdate = now;
		app.draw(this, dt);
	}

	@Override
	public void mousePressed()
	{
		app.mousePressed(this);
	}
	
	@Override
	public void keyPressed()
	{
		app.keyPressed(this);
	}
		
	public static void main(String[] args) 
	{
		app = new Exercicio7App();
		PApplet.main(ProcessingSetupTPC.class);
	}
}

