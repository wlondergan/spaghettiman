package enemies;

import org.newdawn.slick.Graphics;
import gameMembers.Point;

/**
 * The class for a slime character. <br>
 * Slowly follows the player character around the level.
 * @author Hughes
 */
public class Slime extends BasicEnemy{
	
	/**
	 * The walk speed of the Slime.
	 */
	public static final float SPEED = 1.3f;
	
	/**
	 * Sets the location of the slime. Only constructor.
	 * @param x  the x location
	 * @param y  the y location
	 */
	public Slime(float x, float y){
		super(x, y, "assets/slime.png", 1f, 40);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getImage(), getX(), getY());
		getHealthBar().draw(g);
	}

	/**
	 * Follows the character around based on its location.
	 */
	@Override
	public void update(Point p){
		follow(p, SPEED);
		getHealthBar().setLoc(this.getLoc());
	}
	
	
}
