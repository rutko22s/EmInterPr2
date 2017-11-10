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
	Body previousBody;

	//public Presence(PApplet parent, float x, float y) {

	public Presence(PApplet parent, Body body) {

		this.parent = parent;
		
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
		for(int i=0; i<6; i++) {
//			//generate "random" x and y coords
//			float startingX;
//			float startingY;
//			if (around != null) {
//				startingX = parent.random(around.x - (float) .5, around.x + (float) .5);
//				startingY = parent.random(around.y - (float) .5, around.y + (float) .5);
//				System.out.println("This orb is initialized at: " + startingX + ", " + startingY);
//			} else {
//				//last resort, just default to a location
//				startingX = parent.random( -(float).05, (float).05);
//				startingY = parent.random( -(float).05, (float).05);
//			}
			orbList.add(new Orb(this.parent, xPos, yPos));
		}
		previousBody = body;
	}

	/**
	 * Updates the location of all the orbs as the body moves
	 * 
	 * @param body
	 */
	public void draw(Body body) {
		parent.fill(0, 51, 102);
		parent.lightSpecular(255, 255, 255);
		parent.directionalLight(204, 204, 204, 0, 0, 1);

		
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
//		for (Orb orb : orbList) {
//			orb.setLocation(xPos, yPos);
//			orb.draw();
//		}
		float randomizePosition = parent.random(0.12f, 0.15f);

		orbList.get(0).setLocation(xPos, yPos);
		orbList.get(0).draw();
		orbList.get(1).setLocation(xPos + randomizePosition, yPos + randomizePosition);
		orbList.get(1).draw();
		orbList.get(2).setLocation(xPos, yPos - randomizePosition);
		orbList.get(2).draw();
		orbList.get(3).setLocation(xPos,yPos + randomizePosition);
		orbList.get(3).draw();
		orbList.get(4).setLocation( xPos - randomizePosition, yPos);
		orbList.get(4).draw();
		orbList.get(5).setLocation( xPos + randomizePosition, yPos);
		orbList.get(5).draw();
//		
		
//		float randomizePosition = parent.random(0.10f, 0.15f);
//		
//		Orb orb = new Orb(parent,  xPos, yPos);
//		Orb orb1 = new Orb(parent,  xPos + randomizePosition, yPos + randomizePosition);
//		Orb orb2 = new Orb(parent,  xPos , yPos - randomizePosition);
//		Orb orb3 = new Orb(parent,  xPos, yPos + randomizePosition);
//		Orb orb4 = new Orb(parent,  xPos - randomizePosition, yPos );
//		Orb orb5 = new Orb(parent,  xPos + randomizePosition, yPos );
	
	


	
	}

	/**
	 * Maybe orbs grow a little when starting to get close to another body? So
	 * the user knows that getting close to someone else will cause something to
	 * happen Literally this method just makes each orb grow in size marginally
	 */
	public void grow() {
		for (Orb orb : orbList) {
			orb.grow();
		}
		
	}

	

}
