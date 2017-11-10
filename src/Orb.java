import processing.core.PApplet;
import java.awt.Color;
import processing.core.*;
import processing.core.PVector;
/**
 * A single orb belonging to a Presence
 * @author Sara, Lauren, Momal
 *
 */
public class Orb {

	PApplet parent;
	private int color;
	private float startingRadius;
	private float radius;// = 0.05f;
	private float maxRadius;
	private boolean decreasing = false;
	private float pulseRate;

	private float xPos;
	private float yPos;

	private float x;
	private float y;
	
	//private float rx, ry, rz = 1;
	
	float randomizePositionx;
	float randomizePositiony;

	public Orb(PApplet parent, float x, float y) {
		this.parent = parent;
		// randomize everything
		parent.colorMode(PApplet.HSB);
		color = parent.color(parent.random(255), 200, 150);
		// radius = parent.random(1);
		pulseRate = parent.random(1);
		startingRadius = radius = parent.random(.01f,.1f);
		maxRadius = radius + parent.random(.07f);

		randomizePositionx = parent.random(-0.3f, 0.3f);
		randomizePositiony = parent.random(-0.4f, 0.4f);

		this.x = x + randomizePositionx;
		this.y = y + randomizePositiony;
		
		color = Math.round(parent.random(255));

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
	

	

	public void setLocation(float newX, float newY, float jitter) {
		float moveBy = parent.random(-jitter, jitter);
		x = newX + randomizePositionx + moveBy;
		y = newY + randomizePositiony + moveBy;
	}
	
	/**
	 * See note in Presence
	 */
	public void glow() {
		color += .01; //is this how brightening something works?
	}
	
	public void draw(){
		if(radius > maxRadius) 
			decreasing = true;
		if(decreasing) {
			radius -= .001f;
			if(radius < (.01+startingRadius/2)) 
				decreasing = false;
		} else {
			radius += .001f;
		}
		parent.noStroke();
		parent.fill(color);
		
		//parent.specular(255,255,255);
		//parent.specular(color);
		parent.pushMatrix();
		parent.translate(x, y, 1);
//		parent.rotate(x);
//		x += .01f;
		parent.sphere(radius);
		parent.popMatrix();	
		
		
	}

	
	
}
