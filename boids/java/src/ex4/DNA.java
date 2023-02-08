package ex4;

public class DNA {
	
	protected float maxSpeed;
	protected float visionDistance;
	protected float deltaTWander;
	protected float radiusWander;
	protected float phiWander;
	protected float deltaPhiWander;
	
	
	public DNA() {
		maxSpeed=random(0.4f,6f);
		visionDistance=20f;//random(4f,5f);
		deltaTWander = 3f;
		radiusWander = 10f;
		phiWander = (float) Math.PI;
		deltaPhiWander = (float) Math.PI*2;
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
