import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.HashSet;
import java.util.Iterator;

import processing.core.PApplet;
import processing.core.PVector;

public class Pr2Application extends PApplet {

	ArrayList<BigOrb> bigOrbs = new ArrayList<BigOrb>();

	KinectBodyDataProvider kinectReader;
	PersonTracker tracker = new PersonTracker();
	HashMap<Long, Presence> people = new HashMap<Long, Presence>();

	enum ColorSlot {
		RED, GREEN, BLUE
	};

	ColorSlot currentColor;

	public static float PROJECTOR_RATIO = 1080f / 1920.0f;

	public void createWindow(boolean useP2D, boolean isFullscreen, float windowsScale) {
		if (useP2D) {
			if (isFullscreen) {
				fullScreen(P3D);
			} else {
				size((int) (1920 * windowsScale), (int) (1080 * windowsScale), P3D);
			}
		} else {
			if (isFullscreen) {
				fullScreen();
			} else {
				size((int) (1920 * windowsScale), (int) (1080 * windowsScale));
			}
		}
	}

	// use lower numbers to zoom out (show more of the world)
	// zoom of 1 means that the window is 2 meters wide and appox 1 meter tall.
	public void setScale(float zoom) {
		scale(zoom * width / 2.0f, zoom * -width / 2.0f);
		translate(1f / zoom, -PROJECTOR_RATIO / zoom, 2);
	}

	public void settings() {
		createWindow(true, false, .25f);
	}

	public void setup() {
		try {
			kinectReader = new KinectBodyDataProvider("multipersontest2.kinect", 10);
		} catch (IOException e) {
			System.out.println("Unable to create kinect producer");
		}

		 //kinectReader = new KinectBodyDataProvider(8008);

		kinectReader.start();

		// lightSpecular(255, 255, 255);
		// directionalLight(204, 204, 204, 0, 0, 1);
		currentColor = ColorSlot.RED;

	}

	public void draw() {
		setScale(.5f);
		noStroke();
		background(0, 0, 0);
		fill(255, 255, 255);
		
		//boolean bigOrbDrawn = false;

		KinectBodyData bodyData = kinectReader.getData();
		tracker.update(bodyData);
		for (Long id : tracker.getEnters()) {

			Body personBody = tracker.people.get(id);

			PVector spineBase = personBody.getJoint(Body.SPINE_BASE);

			people.put(id, new Presence(this, tracker.getPeople().get(id), currentColor));
			switch (currentColor) {
			case RED:
				currentColor = ColorSlot.GREEN;
				break;
			case GREEN:
				currentColor = ColorSlot.BLUE;
				break;
			case BLUE:
				currentColor = ColorSlot.RED;
				break;
			}

		}
		for (Long id : tracker.getExits()) {
			people.remove(id);
		}
		for (Long person : people.keySet()) {
			if (tracker.getPeople().containsKey(person)) {
				Body body = tracker.getPeople().get(person);
				
				drawOrbCluster(body, people.get(person));

			}

		}

		int numPresence = people.size();
		float x = 0;
		float x1 = 0;
		float xCenter;

		ArrayList<Presence> list = new ArrayList<Presence>();
		list.addAll(people.values());

		if (numPresence > 1) {

			for (int i = 0; i < list.size(); i++) {
				x = list.get(i).xPos;
				for (int j = i; j < list.size(); j++) {
					if (list.get(j) != list.get(i)) {
						x1 = list.get(j).xPos;

						if (x1 < x - 0.0002f || x < x1 - 0.0002f) {
							xCenter = (x1 + x) / 2;
							

							BigOrb closest = null;
							float closestDist = 1f;
							for (BigOrb b : bigOrbs) {
								float dx = Math.abs(b.x - xCenter);
								if (dx <= closestDist) {
									closest = b;
									closestDist = dx;
								}
							}
							if (closest != null) {
								closest.addPresence(list.get(i));
								closest.addPresence(list.get(j));
								
								if(Math.abs(x - x1)  < 0.9){
									closest.draw(this);
								}
								else{
									closest.disappearBigOrb(this);
								}
							} else {
								BigOrb bo = new BigOrb();
								bo.addPresence(list.get(i));
								bo.addPresence(list.get(j));
								bigOrbs.add(bo);
							}

						}
					}
				}
			}

//			Iterator<BigOrb> bigOrbIterator = bigOrbs.iterator();
//			while (bigOrbIterator.hasNext()) {
//				BigOrb bo = bigOrbIterator.next();
//				if (bo.presenceList.size() == 0) {
//					bigOrbIterator.remove();
//				} else {
//					//if (x < x1 - 0.002f) {
//						bo.draw(this);
//						bo.presenceList.clear();
//					//}
//				}
//
//			}
		}


	}

	public void drawOrbCluster(Body body, Presence presence) {
		if (body != null) {
			presence.draw(body);
		}

	}

	public static void main(String[] args) {
		PApplet.main(Pr2Application.class.getName());
	}

}