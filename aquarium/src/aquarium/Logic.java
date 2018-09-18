package aquarium;

import java.util.Iterator;
import java.util.LinkedList;

import processing.core.PApplet;

public class Logic {
	private PApplet app;
	private LinkedList<Fish> fishes;

	private int amp, centerAmp;
	private float sum;
	private float curveY, curveX;
	private float fishTargetX, fishTargetY;

	public Logic(PApplet app) {
		this.app = app;
		this.fishes = new LinkedList<>();

		for (int i = 0; i < 10; i++) {
			this.fishes.add(new Oscar(9, (int) app.random(-100, -50), (int) app.random(app.height), 3));
			this.fishes.getLast().start();
		}

	}

	public void execuate() {
		// TODO Auto-generated method stub
		this.background();
		this.curve();
		synchronized (fishes) {
			Iterator<Fish> iter = fishes.iterator();

			while (iter.hasNext()) {
				Fish fish = (Fish) iter.next();
				this.dislayFishes(fish.getX(), fish.getY());
				fish.setTarget(this.fishTargetX, this.fishTargetY);

				Iterator<Fish> iterB = fishes.iterator();
				while (iterB.hasNext()) {
					Fish fishB = (Fish) iterB.next();
					if (fish != fishB) {
						fish.avoidOther(fishB.getX(), fishB.getY());
					}
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

	private void dislayFishes(float x, float y) {
		app.fill(255);
		app.noStroke();
		app.ellipse(x, y, 10, 10);
	}
}
