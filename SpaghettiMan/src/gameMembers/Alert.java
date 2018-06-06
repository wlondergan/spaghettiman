package gameMembers;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

/**
 * The Alert class is a {@link Drawable} object that displays a message on the screen. <br>
 * Temporary messages can be set to display for a short amount of frames and then go back to the permanent message.
 * @author Hughes
 */
public class Alert extends Drawable{
	
	/**
	 * The number of frames that the temporary message displays for
	 */
	private static final int DISPLAY_FRAMES = 40;
	
	private String currentMessage;
	
	private String tempMessage;
	
	private int tempFrames;
	
	private Font font;
	
	private TrueTypeFont trueTypeFont;
	
	/**
	 * Sets a new message at location x,y and with a value of s.<br>
	 * The only constructor for the class.
	 * @param x  the x location
	 * @param y  the y location
	 * @param s  the String value of the alert (initially)
	 */
	public Alert(float x, float y, String s) {
		super(x,y);
		currentMessage = s;
		tempMessage = "";
		tempFrames = DISPLAY_FRAMES;
		font = new Font("Arial", Font.BOLD, 20);
		trueTypeFont = new TrueTypeFont(font, true);
	}
	
	@Override
	public void draw(Graphics g) {
		
		if(tempFrames<DISPLAY_FRAMES) {
			tempFrames++;
			trueTypeFont.drawString(getX(), getY(), tempMessage, Color.lightGray);
			return;
		}
		// render some text to the screen
		trueTypeFont.drawString(getX(), getY(), currentMessage, Color.lightGray);
	}
	
	/**
	 * Sets the current permanent message of the alert
	 * @param currentMessage  the new message
	 */
	public void setCurrentMessage(String currentMessage) {
		this.currentMessage = currentMessage;
	}
	
	/**
	 * Sets a temporary message for the Alert.<br>
	 * This message will display for a few frames and then the message will go back to being the permanent message.
	 * @param message  the temporary message
	 */
	public void setTempMessage(String message) {
		tempMessage = message;
		tempFrames = 0;
	}

}
