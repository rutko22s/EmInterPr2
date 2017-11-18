import java.util.HashSet;

import processing.core.PApplet;
import processing.core.PShape;

/**
 * An energy orb generated between Presence, that grows to full size when they come together
 * @author Sara, Momal, Lauren
 *
 */
public class BigOrb {
	
	HashSet<Presence> presenceList = new HashSet<Presence>();
	float x;
	float y;
	
	float radius = 0.01f;
	
	
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
	
	public void draw(PApplet papp){
		for(Presence p : presenceList) {
			p.fuse();
		}

		radius = radius + 0.003f;
		float bigRadius = .1f;	 
		radius = (radius > bigRadius) ? bigRadius : radius;

		finalDraw(papp);
	}
	
	private void finalDraw(PApplet papp) {
		papp.pushMatrix();
		papp.noStroke();
		papp.fill(0, 220, 255);
		papp.lightSpecular(204,204,204);	
		papp.directionalLight(102,102,102, 0, y-1, 0.3f);
		papp.specular(255,255,255);
		papp.translate(x, y, 1);		
		papp.shininess(1f);			
		papp.sphere(radius);		
		papp.popMatrix();
	}
	

	public void disappearBigOrb(PApplet papp){		
		float smallRadius = 0.01f;
		radius = radius - .009f;
		radius = (radius > smallRadius) ? radius : smallRadius;
		
		finalDraw(papp);
	}
}
