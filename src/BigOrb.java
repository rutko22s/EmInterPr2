import java.util.HashSet;

import processing.core.PApplet;

public class BigOrb {
	
	HashSet<Presence> presenceList = new HashSet<Presence>();
	float x;
	float y;
	
	float radius = 0;
	
	public BigOrb(){
	}
	
	public void addPresence(Presence p) {
		presenceList.add(p);
		updateLocation();
	}
	
	public void updateLocation() {
		x = 0;
		y = 0;
		for(Presence p : presenceList) {
			x+=p.xPos;
			y+=p.yPos;
		}
		x /= presenceList.size();
		y /= presenceList.size();
	}
	
	public void draw(PApplet papp) {
		papp.pushMatrix();
		papp.lights();
		papp.translate(x, y, 1);
		papp.noStroke();
		papp.fill(255,255,255);
		
		float bigRadius = 0.5f;
		radius +=.01;
		radius = (radius > bigRadius) ? bigRadius : radius;
		//float bigRadius = 1f;
		
		papp.sphere(radius);
		papp.popMatrix();	
	}
}
