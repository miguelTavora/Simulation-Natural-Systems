package ex1;

public class DNA {
	
	protected float maxSpeed;
	protected float visionDistance;
	
	
	public DNA() {
		maxSpeed=random(0.4f,6f);
		visionDistance=20f;//random(4f,5f);
	}
	public DNA(DNA dna, boolean mutate) {
		this.maxSpeed=dna.maxSpeed;
		this.visionDistance=dna.visionDistance;
		if(mutate) mutate();
	}
	public void mutate() {
		
		maxSpeed+=random(-0.25f,0.25f);
		maxSpeed=Math.max(0, maxSpeed);
		visionDistance +=random(-0.25f,0.25f);
		visionDistance=Math.max(0, visionDistance);
		
	}
	
	public static float random(float min,float max) {
		return (float)(min+(max-min)*Math.random());
	}

}
