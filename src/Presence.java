import java.util.ArrayList;

import processing.core.PApplet;

/**
 * Represents a cluster of orbs vaguely representing a person's location in the space
 * @author Sara, Lauren, Momal
 *
 */
public class Presence {
	ArrayList<Orb> orbList = new ArrayList<Orb>();
	float xPos;
	float yPos;
	
	PApplet parent;	
	
	public Presence(PApplet parent, float x, float y) {
		this.parent = parent;
		
		this.xPos = x;
		this.yPos = y;
		
		//draw();
		
		
	}

	public void draw(){
			
			float randomizePosition = parent.random(0.10f, 0.15f);
				
			Orb orb = new Orb(parent,  xPos, yPos);
			Orb orb1 = new Orb(parent,  xPos + randomizePosition, yPos + randomizePosition);
			Orb orb2 = new Orb(parent,  xPos , yPos - randomizePosition);
			Orb orb3 = new Orb(parent,  xPos, yPos + randomizePosition);
			Orb orb4 = new Orb(parent,  xPos - randomizePosition, yPos );
			Orb orb5 = new Orb(parent,  xPos + randomizePosition, yPos );
			
			
		
		
		
	}
	
	
	

	
	
}
