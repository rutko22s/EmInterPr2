//package edu.mtholyoke.cs.comsc243.kinectUDP;

//This is a multi user render demo. I think this works.

import java.util.Set;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * @author eitan
 *
 */
public class MultiUserRenderDemo extends PApplet {
	
	
	PersonTracker personTracker;

	KinectBodyDataProvider kinectReader;
	public static float PROJECTOR_RATIO = 1080f/1920.0f;

	public void createWindow(boolean useP2D, boolean isFullscreen, float windowsScale) {
		if (useP2D) {
			if(isFullscreen) {
				fullScreen(P2D); 			
			} else {
				size((int)(1920 * windowsScale), (int)(1080 * windowsScale), P2D);
			}
		} else {
			if(isFullscreen) {
				fullScreen();  			
			} else {
				size((int)(1920 * windowsScale), (int)(1080 * windowsScale));
			}
		}		
	}
	
	// use lower numbers to zoom out (show more of the world)
	// zoom of 1 means that the window is 2 meters wide and appox 1 meter tall.
	public void setScale(float zoom) {
		scale(zoom* width/2.0f, zoom * -width/2.0f);
		translate(1f/zoom , -PROJECTOR_RATIO/zoom );		
	}

	public void settings() {
		createWindow(true, true, .5f);
	}

	public void setup(){

		/*
		 * use this code to run your PApplet from data recorded by UPDRecorder 
		 */
		/*
		try {
			kinectReader = new KinectBodyDataProvider("test.kinect", 10);
		} catch (IOException e) {
			System.out.println("Unable to creat e kinect producer");
		}
		 */
		personTracker = new PersonTracker();
		
		kinectReader = new KinectBodyDataProvider(8008);
		kinectReader.start();

	}
	
	Long personID1 = null;
	Long personID2 = null;
	
	public void draw(){
		setScale(.5f);
		noStroke();



		background(200,200,200);

		// leave trails instead of clearing background \ 
		//noStroke();
		//fill(0,0,0, 10);
		//rect(-1,-1, 2, 2); //draw transparent rect of the window

//		KinectBodyData bodyData = kinectReader.getMostRecentData();
		KinectBodyData bodyData = kinectReader.getData();
		
		personTracker.update(bodyData);
		Set <Long> enters = personTracker.getEnters();
		Set <Long> exits = personTracker.getExits();
		
		if(personID1 == null) {
			
			for(Body body : personTracker.people.values()) {
				if(personID2 != body.id) {
					personID1 = body.id;
					break;
				}
			}
			
	
		}
		if(exits.contains(personID1)) {
			personID1 = null;
		}
		
		if(personID2 == null){
			for(Body body : personTracker.people.values()){
				if(personID1 != body.id){
					personID2 = body.id;
					break;
				}
			}
			
			
		}
		if(exits.contains(personID2)){
			personID2 = null;
		}
		
		
		
		if(personID1 != null){
			
			Body person1 = personTracker.getPeople().get(personID1);
			
			PVector head1 = person1.getJoint(Body.HEAD);
			PVector shoulderLeft1 = person1.getJoint(Body.SHOULDER_LEFT);
			PVector shoulderRight1 = person1.getJoint(Body.SHOULDER_RIGHT);
			
			
			if(head1 != null && shoulderLeft1 != null && shoulderRight1 != null ){
				stroke(255,0,0, 100);
				noFill();
				strokeWeight(.05f);
				
				triangle(head1.x, head1.y, shoulderLeft1.x, shoulderLeft1.y, 
						shoulderRight1.x, shoulderRight1.y);
			}
			
			
		}
		
		
		if(personID2 != null){
			
			Body person2 = personTracker.getPeople().get(personID2);
			
			PVector head2 = person2.getJoint(Body.HEAD);
			PVector shoulderLeft2 = person2.getJoint(Body.SHOULDER_LEFT);
			PVector shoulderRight2 = person2.getJoint(Body.SHOULDER_RIGHT);
			
			
			if(head2 != null && shoulderLeft2 != null && shoulderRight2 != null){
				stroke(255,0,0, 100);
				noFill();
				strokeWeight(.05f);
				
				triangle(head2.x, head2.y, shoulderLeft2.x, shoulderLeft2.y, 
						shoulderRight2.x, shoulderRight2.y);
			}
		}
		
		
		
		
		
	}

	

	public static void main(String[] args) {
		PApplet.main(MultiUserRenderDemo.class.getName());
	}

}
