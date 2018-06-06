package enemies;

import org.newdawn.slick.Graphics;

import gameMembers.Point;

/**
 * This class is a jumping enemy representation. <br>
 * It follows the player much faster than any other enemy in the game, but only intermittently.
 * @author Hughes
 */
public class JumpyGuy extends BasicEnemy{

	//these constants can be tweaked in between plays but never change during the game.
	/**
	 * The number of frames that the character waits in between jumps.
	 */
	public static final int JUMP_FRAMES = 120;
	/**
	 * The number of frames that the character jumps for.
	 */
	public static final int JUMPING_FRAMES = 10; 
	/**
	 * The speed of the character's jump.
	 */
	public static final int JUMP_VEL = 13;

	//the point that this character is following during a jump
	private Point followPoint;

	//tracks the current frame to allow this character to determine when to jump
	private int currentFrame;

	/**
	 * The constructor for the character.<br>
	 * Defines an x and y location for it.
	 * @param x  the x location of the character
	 * @param y  the y location of the character
	 */
	public JumpyGuy(float x, float y) {
		super(x, y, "assets/jumpy_guy.png", 1, 30);
		currentFrame = 0;
		followPoint = new Point(x, y);
	}

	/**
	 * Updates the location of the character, given the Player's location.
	 * This also increments the frame counter of the JumpyGuy, allowing it to jump.
	 * @param p
	 */
	@Override
	public void update(Point p) {
		currentFrame++;
		if(currentFrame > JUMP_FRAMES) {
			followPoint = p;
			follow(p, JUMP_VEL);
			if(currentFrame == JUMP_FRAMES+JUMPING_FRAMES)
				currentFrame = 0;
		}
		getHealthBar().setLoc(this.getLoc());
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getImage().getScaledCopy(getScale()), getX(), getY());
		getHealthBar().draw(g);
	}

}
