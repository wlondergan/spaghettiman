package enemies;

import org.newdawn.slick.Graphics;
import gameMembers.Point;

public class Slime extends BasicEnemy{//this is just a placeholder class, so i'm not gonna bother with comments for now
	
	public static final float SPEED = 1.3f;
	
	private float xVel;
	
	private float yVel;
	
	public Slime(float x, float y){
		super(x, y, "assets/slime.png", 0.5f, 1f);
		newTrajectory(new Point(0,0));
	}
	
	private void newTrajectory(Point location) {
		float theta = (float)Math.atan2(location.x-this.getX(), location.y-this.getY());
		xVel = (float)(Math.sin(theta))*SPEED;
		yVel = (float)(Math.cos(theta))*SPEED;
	}

	public void move(Point p) {
		newTrajectory(p);
		setX(getX()+xVel);
		setY(getY()+yVel);
		getHealthBar().setLoc(p);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getImage(), (float)getX(), (float)getY());
		getHealthBar().draw(g);
	}

	public void update(Point p){
		move(p);
	}
	
	
}
