package gameMembers;

import java.awt.Rectangle;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * EnvironmentMember<br>
 * This class is inherited by anything that's a part of the environment. It gives a hitbox method (for collision) and a method to move the object when the camera moves.<br>
 * Use this class for anything that may interact with the character. If it's part of the UI, make it a {@code Drawable} instead. 
 * @author Hughes
 * @see Drawable
 */
public abstract class EnvironmentMember extends Drawable{
	
	private Image characterImage;//the class's Image
	
	private float scale;//the scale of the character's image. Dynamic currently to allow camera scaling.
	
	
	/**
	 * The constructor for the class. Gives initial values to all of the instance variables.<br>
	 * x and y are the location of the object (inherited from {@code Drawable}).<br>
	 * {@code String imagePath} is the path of the image for this {@code EnvironmentMember}.<br>
	 * {@code float scale} is the scale of the {@code Image} for the character (for example, 0.5 would make the character's {@code Image} and hitbox half of their size at {@code scale = 1})
	 * @param x  the initial starting x coordinate for the object
	 * @param y  the initial starting y coordinate for the object
	 * @param imagePath  the location of the {@code Image} for the object
	 * @param scale  the initial scale of the {@code Image} when it's displayed on the screen (also changes the hitbox)
	 */
	public EnvironmentMember(float x, float y, String imagePath, float scale){
		super(x, y);
		try {
			this.characterImage = new Image(imagePath);
		} catch (SlickException e) {}//catch the error
		this.scale = scale;
	}

	
	/**
	 * Gives a hitbox for the character. Makes collision detection somewhat easier.<p>
	 * This hitbox is in the form of a an array of <code>Java.awt.Rectangle</code>. The relevant constructor for the <code>Rectangle</code> is <br><code>(int xLoc, int yLoc, int xLength, int yLength)</code>.<p>
	 * This class is used because of its intersects method:<p>
	 * <code><text>
	 * 		if(p.getHitBox().intersects(g.getHitbox))
	 * </text></code><p>
	 * is the intended use for the Rectangle. This is baked in and makes collision a lot easier.
	 * @return r  the {@code Rectangle} hitbox of the {@code EnvironmentMember}
	 */
	public Rectangle[] getHitbox(){
		return new Rectangle[] {new Rectangle((int)getX(), (int)getY(), characterImage.getScaledCopy(scale).getWidth(), characterImage.getScaledCopy(scale).getHeight())};
	}
	
	
	/**
	 * This method should be used to determine if 2 {@code EnvironmentMember} objects are colliding with each other. Since the hitbox is defined as an array, it's useful to have this one baked in method.<br>
	 * This method might have to be rewritten, but for now this works fine.
	 * @param e  the {@code EnvironmentMember} to be checked against for collision
	 * @return  whether or not an intersection is occurring
	 */
	public boolean intersects(EnvironmentMember e){
		for(Rectangle i : this.getHitbox())
			for(Rectangle j : e.getHitbox())
				if(i.intersects(j))
					return true;
		return false;
	}
	
	
	/**
	 * {@code getImage}<br>
	 * This {@code Image} represents the {@code EnvironmentMember} graphically.<br>
	 * It's meant to link directly to the source of the image, rather than a scaled down copy of it. {@code float scale} exists for this purpose.
	 * @return the {@code characterImage} of this class
	 */
	public Image getImage() {
		return characterImage;
	}

	
	/**
	 * This method is inherited from the {@link Drawable} class. This implementation takes a <code>Graphics</code> object when called and uses it to draw the object on the screen. 
	 * The intended use of this method is to be able to draw an entire <code>ArrayList</code> or <code>Drawable[]</code> and draw the entire thing using a simple loop:<br>
	 * <br><code>
	 * for(Drawable d : drawables)<br>
	 * d.draw
	 * </code>
	 * @param g  the Graphics object required to draw
	 */
	@Override
	public void draw(Graphics g){
		g.drawImage(characterImage.getScaledCopy(scale), getX(), getY());
	}
	
	
	/**
	 * This method should be called when the screen's scale changes (e.g. the camera zooms out or in.) It modifies the {@code x} and {@code y} values inherited from {@code Drawable} to fit the new scale and changes the scale of the Image to make it look ok as well.
	 * @param scale  The amount that the screen is being scaled by (e.g. .75 reduces the size of this object by 25% from 100%, but if you set the scale to .75 again nothing will happen)
	 */
	public void changeScaleRelativeToScreen(float scale){
		this.setX(getX()*scale);
		this.setY(getY()*scale);
		this.scale = this.scale*scale;
	}
	
	/**
	 * {@code float scale} is the scale of the {@code Image} representing the {@code EnvironmentMember}. This is useful for changing the size of the character with respect to the screen
	 * @return  the scale of the character
	 */
	public float getScale() {
		return scale;
	}


	public void setScale(float scale) {
		this.scale = scale;
	}
	
	/**
	 * Returns the center point of the {@code EnvironmentMember}.<br>
	 * Normally Characters are defined by their corner coordinates but occasionally it is useful to get the center coordinates.
	 * @return
	 */
	public Point getCenter() {
		return new Point(getX() + getImage().getScaledCopy(scale).getWidth()/2, getY() + getImage().getScaledCopy(scale).getHeight()/2);
	}
}
