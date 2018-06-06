package states;

import java.awt.Rectangle;
import gameMembers.EnvironmentMember;

/**
 * This class represents a button. It defines an image and can determine when it's clicked.
 * @author Hughes
 */
public class PlayButton extends EnvironmentMember{

	/**
	 * @param x  the x location
	 * @param y  the y location
	 * @param imagepath  the path of the image
	 */
	public PlayButton(float x, float y, String imagepath) {
		super(x, y, imagepath, .5f);
	}
	
	/**
	 * Determines whether or not the PlayButton was clicked.
	 * @param x  the x location of the mouse
	 * @param y  the y location of the mouse
	 * @return  whether or not the mouse was on the button when it clicked
	 */
	public boolean isClicked(int x, int y) {
		if(new Rectangle(x,y,1,1).intersects(this.getHitbox()[0]))
			return true;
		return false;
	}
}
