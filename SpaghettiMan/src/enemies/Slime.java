package enemies;

import org.newdawn.slick.Graphics;
import gameMembers.Point;

public class Slime extends BasicEnemy{//this is just a placeholder class, so i'm not gonna bother with comments for now
	
	public static final float SPEED = 1.3f;
	
	private float xVel;
	
	private float yVel;
	
	public Slime(float x, float y){
		super(x, y, "assets/slime.png", 0.5f, 1f);
	}

	public void move(Point p) {
		follow(p, SPEED);
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
