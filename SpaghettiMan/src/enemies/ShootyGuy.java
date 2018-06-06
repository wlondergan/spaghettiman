package enemies;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import gameMembers.Bullet;
import gameMembers.Point;

/**
 * This class is an enemy class for the SpaghettiMan game. <br>
 * ShootyGuy is the only character who shoots stuff; all of the other enemies do other things like run after the main character.
 * @author Hughes
 */
public class ShootyGuy extends BasicEnemy{
	
	/**
	 * The speed at which the character walks
	 */
	public static final float WALK_VEL = .5f;
	
	/**
	 * The speed of the character's bullets
	 */
	public static final float BULLET_VEL = 10;
	
	/**
	 * The amount of time that the character waits in between shooting bullets
	 */
	public static final int WAIT_FRAMES = 60;
	
	//the character's bullets
	private ArrayList<Bullet> bullets;
	
	//the current frame, lets the character decide when to shoot bullets
	private int currentFrame;
	
	/**
	 * The only constructor for the class. It defines an x and y location for the shooty guy.
	 * @param x  the x location of this character
	 * @param y  the y location of this character
	 */
	public ShootyGuy(float x, float y) {
		super(x, y, "assets/shooty_guy.png", 1, 20);
		bullets = new ArrayList<Bullet>();
		currentFrame = 0;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getImage().getScaledCopy(getScale()), getX(), getY());
		for(Bullet b: bullets)
			b.draw(g);
		getHealthBar().draw(g);
	}
	
	@Override
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
