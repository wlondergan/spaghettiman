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
 * This class is meant to handle everything related to the health of a monster on the screen. It should only be used as a variable object in a monster's class. This object should be passed the new location of the monster every turn and will follow it around the screen.
 * <p>
 * This class's graphical representation is composed of an {@code Image} and 2 instances of {@code Rectangle}. The {@code Rectangle} is used to represent the health of the character. (TODO: change this, maybe?)
 * <p>
 * @author Hughes
 */
public class HealthBar extends Drawable{

	private float scale;//the scale of the object, allows for camera movement


	/**
	 * This {@code float} represents the total amount of health that a character has. Since it's {@code final}, it can't be changed.
	 */
	public final float healthTotal;


	private float hp;//the remaining health of the player


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
		g.fillRect(getLoc().x, getLoc().y, 100*scale, 20*scale);//these numbers might have to be tweaked
		g.setColor(Color.red);
		g.fillRect(getLoc().x, getLoc().y, (hp/healthTotal)*100*scale, 20*scale);//these as well
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


	public float getHP(){
		return hp;
	}

	
	public void setHP(float hp){
		this.hp = hp;
	}

}
