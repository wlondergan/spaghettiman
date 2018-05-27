package enemies;

import org.newdawn.slick.Graphics;

import gameMembers.EnvironmentMember;
import gameMembers.Point;

/**
 * <h1>{@code BasicEnemy}</h1>
 * <p>
 * This class represents any enemy in the game. They all possess health bars and a few basic things that define them as enemies. <br>
 * You should extend this class if you have any intent to create a new enemy, even a boss (TODO: Create a boss class??)
 */
public abstract class BasicEnemy extends EnvironmentMember{
	
	//this represents the HealthBar of the class. All Enemies should have a health bar, even bosses
	private HealthBar healthBar;
	
	/**
	 * sets all of the default settings for the basicEnemy.
	 * @param x  the initial x location for the character
	 * @param y  the initial y location for the character
	 * @param imagePath  the sprite path of the character (animations?)
	 * @param scale  the scale of the character
	 * @param health  the health of the character
	 */
	public BasicEnemy(float x, float y, String imagePath, float scale, float health){
		super(x, y, imagePath, scale);
		healthBar = new HealthBar(health, x, y, scale);
	}
	
	/**
	 * {@code update}<p>
	 * This method should be implemented in the subclass so that all Enemies can have their locations updated. For now, the only thing they need is the location of the player character (obstacles?)
	 * @param p  the Point location of the player character
	 */
	public abstract void update(Point p);
	
	@Override
	public abstract void draw(Graphics g);
	
	/**
	 * This method provides a convenient way to follow a {@code Point}. It uses the pythagorean theorem to determine a proper x and y velocity to follow the Point.
	 * Many classes use this method so it's easier to write it once and then never have to use it again.
	 * @param location  the {@code Point} location to be followed
	 * @param vel  the speed of the character calling this method
	 */
	public void follow(Point location, float vel) {
		float theta = (float)Math.atan2(location.x-this.getX(), location.y-this.getY());
		setX(getX()+ (float)(Math.sin(theta))*vel);
		setY(getY()+ (float)(Math.cos(theta))*vel);
	}
	
	
	public HealthBar getHealthBar(){
		return healthBar;
	}
	
}
