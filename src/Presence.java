import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * Represents a cluster of orbs vaguely representing a person's location in the
 * space
 * 
 * @author Sara, Lauren, Momal
 *
 */
public class Presence {

	ArrayList<Orb> orbList = new ArrayList<Orb>();
	float xPos;
	float yPos;

	PApplet parent;
	private long lastFused; //keeps track of the time since the presence last fused with another presence
	private float jitter = 0;
	private int randomColor;
	Pr2Application.ColorSlot orbColor;
	
	public Presence(PApplet parent, Body body, Pr2Application.ColorSlot orbColor) {
		lastFused = System.currentTimeMillis();
		this.parent = parent;
		this.orbColor = orbColor;
		
		PVector around = body.getJoint(Body.SPINE_BASE);
		//make several attempts to grab body parts if they are missing
		if(around == null) {
			around = body.getJoint(Body.SPINE_MID);
			if(around == null) {
				around = body.getJoint(Body.SPINE_SHOULDER);
				if(around == null) {
					around = body.getJoint(Body.HEAD);
				}
			}
		}
		
		this.xPos = around.x;
		this.yPos = around.y;
		
		
		//initialize every orb in presence's cluster (for now it's 10)
		for(int i=0; i<10; i++) {
			orbList.add(new Orb(this.parent, xPos, yPos, orbColor));
		}
	}

	/**
	 * Updates the location of all the orbs as the body moves
	 * 
	 * @param body
	 */
	public void draw(Body body) {
		
		PVector around = body.getJoint(Body.SPINE_BASE);
		//make several attempts to grab body parts if they are missing
		if(around == null) {
			around = body.getJoint(Body.SPINE_MID);
			if(around == null) {
				around = body.getJoint(Body.SPINE_SHOULDER);
				if(around == null) {
					around = body.getJoint(Body.HEAD);
				}
			}
		}
		
		if(around != null) {
			this.xPos = around.x;
			this.yPos = around.y;
		}

		//calc time since lastFused
		if((System.currentTimeMillis() - lastFused)%200 == 0) {
			if (jitter < .1) {
				jitter += .001f;
			}
		}
		for(Orb orb : orbList) {
			orb.setLocation(xPos, yPos, jitter);
			orb.draw();
		}
	
	}
	
	
	public void drawBigOrb(float x, float x1, float y){
		Orb bigOrb = new Orb(this.parent, x, x1,  y);
		fuse();
	}

	/**
	 * Maybe orbs get a little brighter when they get close to another body? So
	 * the user knows that getting close to someone else will cause something to
	 * happen Literally this method just makes each orb a little lighter
	 */
	public void glow() {
		for (Orb orb : orbList) {
			orb.glow();
		}
		
	}
	
	/**This is the method that should be called when two or more presences (including this one) fuse
	 * called by the Pr2Application class on every presence when they come together
	 */
	public void fuse() {
		lastFused = System.currentTimeMillis();
	}
	
	
	

}

