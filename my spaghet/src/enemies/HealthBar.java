package enemies;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import gameMembers.Drawable;
import gameMembers.Point;

/**
 * <h1>{@code HealthBar}</h1><p>
 * This class is meant to handle everything related to the health of a monster on the screen. It should only be used as a variable object in a monster's class. This object should be passed the new location of the monster every turn and will follow it around the screen a few frames behind.
 * <p>
 * This class's graphical representation is composed of an {@code Image} and 2 instances of {@code Rectangle}. The {@code Rectangle} is used to represent the health of the character.
 * <p>
 * {@code HealthBar} also contains a {@code float[][]} that holds the points of the last location that the character was in. This is intended to give the {@code HealthBar} a "following" characteristic.
 * <p>
 * @author Hughes
 */
public class HealthBar extends Drawable{


	public static final int FOLLOW_FRAMES = 20;//the number of frames behind the character that the health bar follows


	private ArrayList<Point> lastPoints;//array of points that the character has been in


	private float scale;//the scale of the object, allows for camera movement


	/**
	 * This {@code float} represents the total amount of health that a character has. Since it's {@code final}, it can't be changed.
	 */
	public final float healthTotal;


	private float hp;//the remaining health of the player


	private Image healthBarFrame;//the frame for the health bar


	/**
	 * The constructor sets up the object with the character in mind. It immediately sets hp to maximum.
	 * <p>
	 * {@code float scale} should be experimented with. It'll take some trying to find the correct amount to scale this object by.
	 * 
	 * @param health  the total amount of health that the character has
	 * @param x  the current x location of the character
	 * @param y  the current y location of the character
	 * @param scale the scale of the HealthBar
	 */
	public HealthBar(float health, float x, float y, float scale){
		super(x, y);
		lastPoints = new ArrayList<>();
		for(int i = 0; i<FOLLOW_FRAMES; i++){
			lastPoints.add(new Point(x, y+40));
		}
		String s;
		healthTotal = health;
		hp = health;
		this.scale = scale;
	}

	/**
	 * <h1>{@code draw}</h1>
	 * <p>
	 * Draws the HealthBar to the screen.
	 * @param g  the {@code Graphics} object required to make this work
	 */
	public void draw(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(getCurrentLoc().x, getCurrentLoc().y, 100*scale, 20*scale);
		g.setColor(Color.red);
		g.fillRect(getCurrentLoc().x, getCurrentLoc().y, (hp/healthTotal)*100*scale, 20*scale);
	}


	/**
	 * <h1>{@code newPoint}</h1>
	 * <p>
	 * Use this method every time that you update the class that has this object. This is meant to take the current position of the character and pass it to this class so that this object can "follow" the character around a few frames behind.
	 * @param x  the x location of the character
	 * @param y  the y location of the character
	 */
	public void newPoint(Point p){
		lastPoints.remove(lastPoints.size()-1);
		lastPoints.add(0,p);
	}

	
	/**
	 * <h1>{@code takeDamage}</h1>
	 * <p>
	 * This method is called when the hp should be changed. Give a negative value to increase the health and a positive one to take away health.<br>
	 * It also caps health so that it can't go below 0 or above maximum.
	 * @param damage  The amount to modify health by
	 */
	public void takeDamage(float damage){
		if(hp-damage < 0)
			hp = 0;
		else if(hp-damage > healthTotal)
			hp = healthTotal;
		else
			hp -=damage;
	}

	
	/**
	 * <h1>{@code getCurrentLoc}</h1>
	 * <p>
	 * Returns the current location of the object in {@code Point(x,y)} form. May have to be unpacked.
	 * @return The current {@code Point} location of the {@code HealthBar}
	 */
	public Point getCurrentLoc(){
		return lastPoints.get(lastPoints.size()-1);
	}


	public float getHP(){
		return hp;
	}


}
