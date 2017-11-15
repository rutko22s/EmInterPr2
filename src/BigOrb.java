import java.util.HashSet;

import processing.core.PApplet;
import processing.core.PShape;

public class BigOrb {
	
	HashSet<Presence> presenceList = new HashSet<Presence>();
	float x;
	float y;
	
	float radius = 0;
	
	
	public BigOrb(){
	}
	
	public void addPresence(Presence p) {
		p.fuse();	
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
		papp.pushMatrix();

		papp.noStroke();
		papp.fill(0, 51, 102);
		papp.ambientLight(102, 102, 102);
		papp.lightSpecular(204, 204, 204);
	
		papp.directionalLight(102, 102, 102, 0, -1, 0.3f);

		papp.specular(255, 255, 255);
		papp.translate(x, y, 1);
		
		papp.shininess(0.7f);			
		
		//float bigRadius = 0.5f;
		float bigRadius = .3f*presenceList.size();
		if(radius > bigRadius) {
			radius -=.01;
		} else {
			radius +=.02;
			radius = (radius > bigRadius) ? bigRadius : radius;
		}
		
		
		papp.sphere(radius);
		papp.filter(papp.BLUR,2);
		
		papp.popMatrix();
	}
	

	public void disappearBigOrb(PApplet papp){
		float smallRadius = 0;
		radius +=.01;
		radius = (radius > smallRadius) ? smallRadius : radius;
		
	}
}
