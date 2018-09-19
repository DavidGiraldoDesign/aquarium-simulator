package aquarium;

import java.util.Iterator;
import java.util.LinkedList;

import processing.core.PApplet;
import processing.core.PImage;

public class Logic {
	
	private PApplet app;
	private LinkedList<Fish> fishes;

	private int amp, centerAmp;
	private float sum;
	private float curveY, curveX;
	private float fishTargetX, fishTargetY;

	private PImage fishImg;

	public Logic(PApplet app) {
		this.app = app;
		fishImg = app.loadImage("imgs/fish.png");
		this.fishes = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			this.fishes.add(new Oscar(9, (int) app.random(-1000, -10), (int) app.random(0, app.height),
					(int) app.random(100), 3, app.random((float) 0.05, (float) 0.09)));
			this.fishes.getLast().start();
		}
	}

	public void execuate() {
		//this.background();
		app.background(0);
		this.curve();
		this.displayFishes(app.width / 4, app.height / 4, 0, 0);
		synchronized (fishes) {
			Iterator<Fish> iter = fishes.iterator();
			while (iter.hasNext()) {
				Fish fish = (Fish) iter.next();
				this.displayFishes(fish.getX(), fish.getY(), fish.getZ(), fish.getAnglefish());
				fish.setTarget(this.fishTargetX, this.fishTargetY);
				Iterator<Fish> iterB = fishes.iterator();
				while (iterB.hasNext()) {
					Fish fishB = (Fish) iterB.next();
					if (fish != fishB) {
						fish.avoidOther(fishB.getX(), fishB.getY());
					}
				}
				if (fish.getX() > app.width + 100) {
					fish.setX((int) app.random(-500, -10));
				}
			}
		}
	}

	private void curve() {
		this.amp = 200;
		this.centerAmp = 300;
		sum += 0.05;
		this.curveX += 3;
		this.curveY = (PApplet.sin(sum)) * amp;
		app.ellipse(this.curveX, this.centerAmp + this.curveY, 50, 50);
		this.fishTargetX = this.curveX;
		this.fishTargetY = this.centerAmp + this.curveY;
	}

	private void background() {
		app.background(0, 100, 0);
	}

	private void displayFishes(float x, float y, float z, float angleFish) {
		app.imageMode(PApplet.CENTER);
		app.pushMatrix();
		app.translate(x, y, z);
		app.rotateY(PApplet.radians((PApplet.sin(angleFish)) * 5));
		app.image(fishImg, -50, 0);
		app.popMatrix();
	}

}
