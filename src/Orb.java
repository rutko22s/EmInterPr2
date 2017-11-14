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
//	private int color;
	private Pr2Application.ColorSlot orbColor;
	private int randomColor;
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

	public Orb(PApplet parent, float x,float x1, float y){
		this.parent = parent;
		
		this.xPos =  (x1 - x )/2;
		this.yPos = y;
		 biggerDraw();
		
		
		
	}
	
	
	
	public Orb(PApplet parent, float x, float y, Pr2Application.ColorSlot orbColor) {
		this.parent = parent;
		this.orbColor = orbColor;
		// randomize everything
//		parent.colorMode(PApplet.HSB);
//		color = parent.color(parent.random(100,255), 255, 255);
		randomColor = Math.round(parent.random(150,255));
		pulseRate = parent.random(1);
		startingRadius = radius = parent.random(.01f,.1f);
		maxRadius = radius + parent.random(.07f);

		randomizePositionx = parent.random(-0.3f, 0.3f);
		randomizePositiony = parent.random(-0.4f, 0.4f);

		this.x = x + randomizePositionx;
		this.y = y + randomizePositiony;
		
//		color = Math.round(parent.random(255));

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
		//TODO
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
		//parent.specular(255,255,255);
		//parent.specular(color);
		parent.pushMatrix();
		parent.lights();
		parent.translate(x, y, 1);
//		parent.rotate(x);
//		x += .01f;
		parent.noStroke();
		
		switch (orbColor) {
		case RED:
			parent.fill(randomColor, 0, 50);
			break;
		case GREEN:
			parent.fill(0, randomColor, 50);
			break;
		case BLUE:
			parent.fill(0, 50, randomColor);
			break;
		}
		//parent.fill(randomColor, 200, 200);
		
		parent.sphere(radius);
		parent.popMatrix();	
		
		
	}
	
	public void biggerDraw(){
		parent.pushMatrix();
	//	parent.lights();
		parent.translate(xPos, yPos, 1);
		parent.noStroke();
	//	parent.fill(255,255,255);
		
		float bigRadius = 0.5f;
		//float bigRadius = 1f;
		
		parent.sphere(bigRadius);
		parent.popMatrix();	
	}

	
	
}
