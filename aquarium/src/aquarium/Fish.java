package aquarium;

import processing.core.PVector;

public abstract class Fish extends Thread {

	public int x, y, code;
	public float speed;

	private PVector position;
	private PVector velocity;
	private PVector acceleration;
	private PVector target;

	public Fish(int code, int x, int y, float speed) {
		this.code = code;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.position = new PVector(500,500);
		

	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void move() {
		
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getCode() {
		return code;
	}

	public float getSpeed() {
		return speed;
	}

}
