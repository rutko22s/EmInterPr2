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
	private float radius;
	private float pulseRate;
	private float x;
	private float y;
	
	public Orb(PApplet parent, float initX, float initY) {
		this.parent = parent;
		//randomize everything
		parent.colorMode(PApplet.HSB);
		color = parent.color(parent.random(255), 200, 150);
		radius = parent.random(1);
		pulseRate = parent.random(1);
		x = initX;
		y = initY;
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
	
	public void setLocation(float newX, float newY) {
		x = newX;
		y = newY;
	}
	
	/**
	 * See note in Presence
	 */
	public void grow() {
		radius += .01;
	}
	
	public void draw(){
		//System.out.println("Sphere is at: " + x + ", " + y);
//		parent.size(100, 100, PApplet.P3D);
//		parent.background(0);
		
		parent.noStroke();
		
	//	parent.background(0);
		
//		parent.fill(0, 51, 102);
//		parent.lightSpecular(255,255,255);
//		parent.directionalLight(204, 204, 204, 0, 0, 1);
		
		//parent.translate(20, 50, 0);
		
		parent.specular(255,255,255);
		parent.pushMatrix();
		//parent.translate(.5f,.25f, 1); //this is where x and y need to go
		parent.translate(x, y, 1);
		parent.sphere(.1f);
		parent.popMatrix();
		
		//parent.translate(60, 0, 0);
		//parent.specular(204, 102, 0);
		//parent.sphere(30);
		
		
		
	}
	

}
