import processing.core.PApplet;

/**
 * A single orb belonging to a Presence
 * @author Sara, Lauren, Momal
 *
 */
public class Orb {
	PApplet parent;
	private int color;
	private float radius = 0.05f;
	private float pulseRate;
	private float xPos;
	private float yPos;
	
	public Orb(PApplet parent, float x, float y) {
		this.parent = parent;
		//randomize everything
		parent.colorMode(PApplet.HSB);
		color = parent.color(parent.random(255), 200, 150);
		//radius = parent.random(1);
		pulseRate = parent.random(1);
		
		draw(x,y);
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
	
	
	public void draw(float x, float y){
		this.xPos = x;
		this.yPos = y;
		
		
		parent.ellipse(xPos, yPos, radius, radius);
		

		parent.noStroke();
		

		
		parent.fill(0, 51, 102);
		parent.lightSpecular(255,255,255);
		//COMMENT THIS AND YOU WILL NOT GET AN ERROR, BUT THE COLORS FADE
		//parent.directionalLight(204, 204, 204, 0, 0, 1);
		
		
		
		parent.specular(255,255,255);
		parent.pushMatrix();
		
		parent.translate(xPos,yPos, 2);
		parent.sphere(radius);
		parent.popMatrix();
		
		
		
	}
	
	
}
