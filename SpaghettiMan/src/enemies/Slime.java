package enemies;

import org.newdawn.slick.Graphics;
import gameMembers.Point;

public class Slime extends BasicEnemy{
	
	public static final float SPEED = 1.3f;
	
	public Slime(float x, float y){
		super(x, y, "assets/slime.png", 0.5f, 40);
	}

	public void move(Point p) {
		follow(p, SPEED);
		getHealthBar().setLoc(this.getLoc());
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
