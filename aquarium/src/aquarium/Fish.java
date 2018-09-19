package aquarium;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class Fish extends Thread {

	public float x, y,z;
	public int code;
	public float speed;

	private PVector position;
	private PVector velocity;
	private PVector acceleration;
	private PVector target;
	private float targetX, targetY;
	private PVector avoidTarget;
	private PVector avoidAcceleration;

	private float anglefish;
	private float sumAngle;

	public Fish(int code, int x, int y,int z, float speed, float sumAngle) {
		this.code = code;
		this.x = x;
		this.y = y;
		this.z=z;
		this.speed = speed;
		this.position = new PVector(this.x, this.y);
		this.velocity = new PVector(0, 0);
		this.sumAngle = sumAngle;
	}

	@Override
	public void run() {
		while (true) {
			try {
				this.move();
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void move() {
		this.target = new PVector(this.targetX, this.targetY);
		this.acceleration = PVector.sub(this.target, this.position);
		this.acceleration.setMag((float) 0.2);
		this.velocity.add(this.acceleration);
		this.velocity.limit(this.speed);
		this.position.add(this.velocity);
		this.x = this.position.x;
		this.y = this.position.y;
	}

	public void avoidOther(float xOther, float yOther) {
		if (PApplet.dist(this.x, this.y, xOther, yOther) < 50) {
			this.avoidTarget = new PVector(xOther, yOther);
			this.avoidAcceleration = PVector.sub(this.position, this.avoidTarget);
			this.avoidAcceleration.setMag(5);
			this.velocity.add(this.avoidAcceleration);
			this.velocity.limit(10);
			this.position.add(this.velocity);
			this.x = this.position.x;
			this.y = this.position.y;
		}
	}

	public float getAnglefish() {
//		if (this.anglefish > 5) {
//			this.anglefish = 0;
//		}
		this.anglefish += this.sumAngle;
		return this.anglefish;
	}

	public void setTarget(float targetX, float targetY) {
		this.targetX = targetX;
		this.targetY = targetY;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public float getZ() {
		return z;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getCode() {
		return code;
	}

	public float getSpeed() {
		return speed;
	}

}
