package boids;

public class DNA {
	
	protected float maxSpeed;
	protected float visionDistance;
	public float visionAngle;
	protected float deltaTWander;
	protected float radiusWander;
	protected float phiWander;
	protected float deltaPhiWander;
	
	
	public DNA() {
		maxSpeed=random(0.1f,0.5f);
		visionDistance=random(0.4f,0.8f);
		deltaTWander = 0.5f;
		radiusWander = 0.5f; //10f
		phiWander = (float) Math.PI;
		deltaPhiWander = (float) Math.PI*2;
	}
	public DNA(DNA dna, boolean mutate) {
		this.maxSpeed=dna.maxSpeed;
		this.visionDistance=dna.visionDistance;
		visionAngle = (float)Math.PI;
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
