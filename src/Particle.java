import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 * 
 * @author lauren ferrara
 * @author victoria guerra
 * @author nikkole spencer
 * 
 * Particle class - A simple Particle class, renders the particle as an image
 *
 */

public class Particle {

	  PVector loc;
	  PVector vel;
	  PVector acc;
	  
	  PImage img;
	  PApplet app;
	  
	  float lifespan;
	 
	  static float IMG_SIZE = .2f;

	  
	  public Particle(PApplet app, PVector l, PImage img_) {
	    this.app = app;
		acc = new PVector(0, 0);
		
	    float vx = (float)(app.randomGaussian() * .001);
	    float vy = (float)(app.randomGaussian()* .001);
	    
	    acc.x += vx;
	    acc.y += vy;
	    
		vel = new PVector(0,0);
	    loc = l.copy();
	    lifespan = 100.0f;
	    img = img_;
	    
	  }

	  
	 public void run() {
	    update();
	    render();
	    
	  }

	 
//	 /**
//	  * applyForce - Method to apply a force vector to the Particle object.
//	  * Note we are ignoring "mass" here
//	  * 
//	  * @param f
//	  */
//	
//	  public void applyForce(PVector f) {
//	    acc.add(f);
//	    
//	  }  

	  
	  // Method to update position
	 public void update() {
	    vel.add(acc);
	    loc.add(vel);
	    lifespan -= 2.5;
	    acc.mult(0); // clear Acceleration
	    
	  }

	 
	  // Method to display
	  public void render() {
	    app.imageMode(PApplet.CENTER);
	    app.tint(255, lifespan);
	    app.image(img, loc.x, loc.y, IMG_SIZE,IMG_SIZE);
	 
	  }

	  
	  // Is the particle still useful?
	  public boolean isDead() {
	    if (lifespan <= 0.0) {
	      return true;
	      
	    } else {
	      return false;
	      
	    }
	    
	  }
	

}

