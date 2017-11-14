import processing.core.PApplet;
import processing.core.*;
import processing.core.PVector;

public class ShootingStar {

	public static float TWO_PI = (float) (3.14*2f);
	
	public ShootingStar() {
		// TODO Auto-generated constructor stub
	}

	public void setup(){
		
	}
	public void draw(PApplet papp){
		
		
		
		 papp.pushMatrix();
		 papp.translate(100*0.8f, 100*0.5f);
		// papp.rotate(frameCount / -100.0);
		 star(0, 0, 30, 70, 5); 
		  papp.popMatrix();
	}
	
	void star(float x, float y, float radius1, float radius2, int npoints) {
		  float angle = TWO_PI / npoints;
		  float halfAngle = (float) (angle/2.0);
		 // beginShape();
		  for (float a = 0; a < TWO_PI; a += angle) {
		    float sx = x + cos(a) * radius2;
		    float sy = y + sin(a) * radius2;
		    vertex(sx, sy);
		    sx = x + cos(a+halfAngle) * radius1;
		    sy = y + sin(a+halfAngle) * radius1;
		    vertex(sx, sy);
		  }
		 // endShape(CLOSE);
		}
	
	
	
}
