package aquarium;

import processing.core.PApplet;

public class Main extends PApplet{
	private Logic l;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("aquarium.Main");
	}
	
	@Override
	public void settings() {
		// TODO Auto-generated method stub
		//super.settings();
		size(1200,700);
	}
	
	@Override
	public void setup() {
		// TODO Auto-generated method stub
		//super.setup();
		l= new Logic(this);
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		//super.draw();
		l.execuate();
	}
	

}
