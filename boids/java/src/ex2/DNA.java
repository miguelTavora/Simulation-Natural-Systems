package ex2;

public class DNA {
	
	protected float maxSpeed;
	protected float visionDistance;
	
	
	public DNA() {
		maxSpeed=random(8f,10f);
		visionDistance=10f;
	}
	public DNA(DNA dna, boolean mutate) {
		this.maxSpeed=dna.maxSpeed;
		this.visionDistance=dna.visionDistance;
		if(mutate) mutate();
	}
	public void mutate() {
		
		maxSpeed+=random(-0.25f,0.25f);
		maxSpeed=Math.max(10, maxSpeed);
		visionDistance +=random(-0.25f,0.25f);
		visionDistance=Math.max(10, visionDistance);
		
	}
	
	public static float random(float min,float max) {
		return (float)(min+(max-min)*Math.random());
	}

}
