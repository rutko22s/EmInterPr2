import processing.core.PApplet;

/**
 * A single orb belonging to a Presence
 * @author Sara, Lauren, Momal
 *
 */
public class Orb {
	PApplet parent;
	private int color;
	private float radius;
	private float pulseRate;
	
	public Orb(PApplet parent) {
		this.parent = parent;
		//randomize everything
		parent.colorMode(PApplet.HSB);
		color = parent.color(parent.random(255), 200, 150);
		radius = parent.random(1);
		pulseRate = parent.random(1);
	}
	
	public int getColor() {
		return color;
	}
	
	public float getRadius() {
		return radius;
	}
	
	public float getPulseRate() {
		return pulseRate;
	}

}
