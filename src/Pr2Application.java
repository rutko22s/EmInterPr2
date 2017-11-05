import processing.core.PApplet;

public class Pr2Application extends PApplet {

	KinectBodyDataProvider kinectReader;
	PersonTracker tracker = new PersonTracker();

	public static float PROJECTOR_RATIO = 1080f / 1920.0f;

	public void createWindow(boolean useP2D, boolean isFullscreen, float windowsScale) {
		if (useP2D) {
			if (isFullscreen) {
				fullScreen(P2D);
			} else {
				size((int) (1920 * windowsScale), (int) (1080 * windowsScale), P2D);
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
		translate(1f / zoom, -PROJECTOR_RATIO / zoom);
	}

	public void settings() {
		createWindow(true, false, .25f);
	}

	public void setup() {
		/*
		 * try { kinectReader = new KinectBodyDataProvider("test.kinect", 10); } catch
		 * (IOException e) { System.out.println("Unable to creat e kinect producer"); }
		 */

		kinectReader = new KinectBodyDataProvider(8008);

		kinectReader.start();
	}
	
	public void draw(){
		setScale(.5f);		
		noStroke();
		background(200,200,200);
		
	}

}
