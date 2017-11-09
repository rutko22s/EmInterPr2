import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * Represents a cluster of orbs vaguely representing a person's location in the space
 * @author Sara, Lauren, Momal
 *
 */
public class Presence {
	PApplet parent;
	ArrayList<Orb> cluster;
	Body previousBody;
	
	public Presence(PApplet parent, Body body) {
		this.parent = parent;
		cluster = new ArrayList<Orb>();
		
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
		
		//initialize every orb in presence's cluster (for now it's 10)
		for(int i=0; i<10; i++) {
			//generate "random" x and y coords
			float startingX;
			float startingY;
			if (around != null) {
				startingX = parent.random(around.x - (float) .5, around.x + (float) .5);
				startingY = parent.random(around.y - (float) .5, around.y + (float) .5);
				System.out.println("This orb is initialized at: " + startingX + ", " + startingY);
			} else {
				//last resort, just default to a location
				startingX = parent.random( -(float).05, (float).05);
				startingY = parent.random( -(float).05, (float).05);
			}
			cluster.add(new Orb(this.parent, startingX, startingY));
		}
		previousBody = body;
	}
	
	/**
	 * Updates the location of all the orbs as the body moves
	 * @param body
	 */
	public void draw(Body body) {
		parent.fill(0, 51, 102);
		parent.lightSpecular(255,255,255);
		parent.directionalLight(204, 204, 204, 0, 0, 1);
		
		for(Orb orb : cluster) {
			//move the orbs based on where the body was previously vs where it is now
			//this is to make orbs move in a continuous way rather than randomly relocate around the spine every moment
			//if we cant find the new location, we just wait to update the location
			//		the orb-body relationship doesn't have to be 1-1 so this is ok
			if(previousBody != null && previousBody.getJoint(Body.SPINE_BASE) != null) {
				PVector oldSpine = previousBody.getJoint(Body.SPINE_BASE);
				if(oldSpine != null) {
					PVector newSpine = body.getJoint(Body.SPINE_BASE);
					if(newSpine != null) {
						orb.setLocation(oldSpine.x-newSpine.x, oldSpine.y-newSpine.y);
					}
				}
			}
			orb.draw();
		}
		
		
		previousBody = body; //keep track of where the body was so we can update orb locations w/o re-randomizing them
	}
	
	/**
	 * Maybe orbs grow a little when starting to get close to another body?
	 * So the user knows that getting close to someone else will cause something to happen
	 * Literally this method just makes each orb grow in size marginally
	 */
	public void grow() {
		for(Orb orb : cluster) {
			orb.grow();
		}
	}

}
