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
	private Pr2Application.ColorSlot orbColor;
	private int randomColor;
	private float startingRadius;
	private float radius;
	private float maxRadius;
	private boolean decreasing = false;
	private float pulseRate;
	private float x;
	private float y;
	private float bezierYtop;
	private float bezierYbot;
	private float bezierXleft;
	private float bezierXright;
	private float randomizePositionx;
	private float randomizePositiony;
	private float distFromCenter;
	private float bezierXshift;
	private float bezierYshift;
	private double direction; 
		
	public Orb(PApplet parent, float x, float y, Pr2Application.ColorSlot orbColor) {
		this.parent = parent;
		this.orbColor = orbColor;
		// randomize everything
		randomColor = Math.round(parent.random(150,255));
		pulseRate = parent.random(1);
		startingRadius = radius = parent.random(.01f,.1f);
		maxRadius = radius + parent.random(.07f);

		randomizePositionx = parent.random(-0.3f, 0.3f);
		randomizePositiony = parent.random(-0.4f, 0.4f);
		direction = parent.random(1);

		this.x = x + randomizePositionx;
		this.y = y + randomizePositiony;
		
		distFromCenter = PApplet.dist(x, y, this.x, this.y);

		generateBezier(x,y);
		
	}
	
	//generate the curve we want to move this orb along
	public void generateBezier(float x, float y) {
		//use the distance of the orb from the center of the presence to determine the size of its orbit
		bezierYtop = y+distFromCenter;
		bezierYbot = y-distFromCenter;
		bezierXleft = x-distFromCenter;
		bezierXright = x+distFromCenter;
		
		//randomize the speed and direction in which the orb moves
		float t = (float) ((parent.frameCount + distFromCenter) / 100.0) % 1;
		if (direction < .25) {
			bezierXshift = parent.bezierPoint(x, bezierXleft, bezierXright, x, t);
			bezierYshift = parent.bezierPoint(bezierYtop, bezierYbot, bezierYbot, bezierYtop, t);
		} else if (direction < .5) {
			bezierXshift = parent.bezierPoint(x, bezierXright, bezierXleft, x, t);
			bezierYshift = parent.bezierPoint(bezierYtop, bezierYbot, bezierYbot, bezierYtop, t);
		} else if (direction < .75) {
			bezierXshift = parent.bezierPoint(x, bezierXright, bezierXleft, x, t);
			bezierYshift = parent.bezierPoint(bezierYbot, bezierYtop, bezierYtop, bezierYbot, t);
		} else {
			bezierXshift = parent.bezierPoint(x, bezierXleft, bezierXright, x, t);
			bezierYshift = parent.bezierPoint(bezierYbot, bezierYtop, bezierYtop, bezierYbot, t);
		}
	}
	
	public float getRadius() {
		return radius;
	}
	
	public float getPulseRate() {
		return pulseRate;
	}

	public void setLocation(float newX, float newY, float jitter) {
		generateBezier(newX,newY);
		float moveBy = parent.random(-jitter, jitter);
		//x = newX + randomizePositionx + moveBy;
		//y = newY + randomizePositiony + moveBy;
		x = moveBy + bezierXshift + randomizePositionx;
		y = moveBy + bezierYshift + randomizePositiony;
		
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

		parent.pushMatrix();
		parent.lights();
		parent.translate(x, y, -1);
		parent.noStroke();
		
		switch (orbColor) {
		case BLUE:
			parent.fill((200*randomColor)%255, 50, randomColor);
			break;
//		case RED:
//			parent.fill(255, 50, (20*randomColor)%255);
//			break;
		case GREEN:
			parent.fill(0, randomColor, 120);
			break;
		}
		
		parent.sphere(radius);
		parent.popMatrix();	
			
	}
	
}
