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
//		papp.lights();
//		papp.translate(x, y, 1);
//		papp.noStroke();
//		papp.fill(255,255,255);
		
		papp.noStroke();
		//papp.background(0);
		papp.fill(0, 51, 102);
		papp.ambientLight(102, 102, 102);
		papp.lightSpecular(204, 204, 204);
	
		papp.directionalLight(102, 102, 102, 0, -1, 0.3f);
		
		//papp.lightSpecular(102, 102, 102);
		//papp.directionalLight(102, 102, 102, 0, 1, 1);
		//papp.lightSpecular(102, 102, 102);

		papp.specular(255, 255, 255);
	//	papp.ambient(0,51,102);
		//papp.emissive(0, 26, 51);
		//papp.directionalLight(102, 102, 102, 0, 1, 1);
		papp.translate(x, y, 1);
		
		papp.shininess(0.7f);
		//papp.specular(255, 255, 255);
		
		
		
		
		
		
		float bigRadius = 0.5f;
		radius +=.01;
		radius = (radius > bigRadius) ? bigRadius : radius;
		//float bigRadius = 1f;
		
		papp.sphere(radius);
		
		papp.popMatrix();	
	}
}
