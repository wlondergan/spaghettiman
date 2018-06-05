package gameMembers;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

public class Alert extends Drawable{

	private static final int DISPLAY_FRAMES = 40;
	
	private String currentMessage;
	
	private String tempMessage;
	
	private int tempFrames;
	
	private Font font;
	private TrueTypeFont trueTypeFont;
	
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
	
	public void setCurrentMessage(String currentMessage) {
		this.currentMessage = currentMessage;
	}
	
	public void setTempMessage(String message) {
		tempMessage = message;
		tempFrames = 0;
	}

}
