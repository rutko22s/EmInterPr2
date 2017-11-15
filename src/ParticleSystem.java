import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 * 
 * @author lauren ferrara
 *
 * 
 * ParticleSystem class - 

// A class to describe a group of Particles
// An ArrayList is used to manage the list of Particles 

 *
 */
public class ParticleSystem {

	 ArrayList<Particle> particles;    // An arraylist for all the particles
	  PVector origin;                   // An origin point for where particles are birthed
	  PImage img;
	  PApplet app;

	  
	  
	  ParticleSystem(PApplet applet, int num, PVector v, PImage img_) {
		this.app = applet;
		 // Initialize the arraylist
		particles = new ArrayList<Particle>();   
		// Store the origin point
	    origin = v.copy();      
	    img = img_;
	    
	    for (int i = 0; i < num; i++) {
	      // Add "num" amount of particles to the arraylist
	      particles.add(new Particle(app, origin, img));     
	      
	    }
	    
	  }
	 

	  void run() {
	    for (int i = particles.size()-1; i >= 0; i--) {
	      Particle p = particles.get(i);
	      p.run();
	      
	      if (p.isDead()) {
	        particles.remove(i);
	        
	      }
	      
	    }
	    
	  }

	  
//	  // Method to add a force vector to all particles currently in the system
//	  void applyForce(PVector dir) {
//	    // Enhanced loop!!!
//	    for (Particle p : particles) {
//	      p.applyForce(dir);
//	      
//	    }
//	    
//	  }  

	  
	  void addParticle() {
	    particles.add(new Particle(app, origin, img));
	    
	  }
	
	
}
