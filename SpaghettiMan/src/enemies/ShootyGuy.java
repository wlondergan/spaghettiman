package enemies;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import gameMembers.Bullet;
import gameMembers.Point;

public class ShootyGuy extends BasicEnemy{
	
	public static final float WALK_VEL = .5f;
	
	public static final float BULLET_VEL = 10;
	
	public static final int WAIT_FRAMES = 60;
	
	private ArrayList<Bullet> bullets;
	
	private int currentFrame;
	
	public ShootyGuy(float x, float y) {
		super(x, y, "assets/shooty_guy.png", 1, 20);
		bullets = new ArrayList<Bullet>();
		currentFrame = 0;
	}

	public void draw(Graphics g) {
		g.drawImage(getImage().getScaledCopy(getScale()), getX(), getY());
		for(Bullet b: bullets)
			b.draw(g);
		getHealthBar().draw(g);
	}
	
	public void update(Point p) {
		currentFrame++;
		if(currentFrame == WAIT_FRAMES) {
			bullets.add(new Bullet(BULLET_VEL, this.getX()+(getImage().getScaledCopy(getScale()).getWidth()/2), this.getY()+(getImage().getScaledCopy(getScale()).getHeight()/2), p.x, p.y));
			currentFrame = 0;
		}
		follow(p, WALK_VEL);
		for(Bullet b: bullets)
			b.updateLoc();
		getHealthBar().setLoc(getLoc());
	}
	
	public ArrayList<Bullet> getBullets(){
		return bullets;
	}
}
