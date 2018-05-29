package gameMembers;

import org.newdawn.slick.Graphics;

/**
 * Anything that will be drawn to the screen needs to inherit {@code Drawable} so that all objects of this type can be drawn to the screen at the same time. Keep in mind that {@code EnvironmentMember} inherits this class so use that class if it's going to interact with the environment.<p>
 * Otherwise, use this class for generic uses that don't actually interact with anything, like UI elements.<p>
 * This class has an x and y so it can be drawn to the screen.
 * @author Hughes
 * @see EnvironmentMember
 */
public abstract class Drawable {
	
	private Point loc;//the location of the Drawable object
	
	
	/**
	 * Sets the initial x and y location of the object
	 * @param x  the x location of the object in Cartesian space
	 * @param y  the y location of the object in Cartesian space
	 */
	public Drawable(float x, float y){
		loc = new Point(x,y);
	}
	
	/**
	 * {@code draw}<p>
	 * This method is required to be implemented by any class that inherits it. This allows draw to be called on a generic {@code Drawable[]}.<p>
	 * Implementation of this method should involve putting a graphical representation of the class on the screen in some way.<p>
	 * An example of how to draw:<p>
	 * <code>
	 * g.drawImage(this.getImage());
	 * </code>
	 * @param g  The {@code Graphics} object passed in from the class that has a {@code draw} method. This allows the object to draw itself<p>
	 */
	public abstract void draw(Graphics g);

	public float getX() {//rando getters and setters
		return loc.x;
	}

	public void setX(float x) {
		loc.x = x;
	}

	public float getY() {
		return loc.y;
	}

	public void setY(float y) {
		loc.y = y;
	}

	public Point getLoc() {
		return loc;
	}

	public void setLoc(Point loc) {
		this.loc = loc;
	}
}
