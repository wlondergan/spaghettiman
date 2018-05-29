package enemies;

import org.newdawn.slick.Graphics;

import gameMembers.Point;

/**
 * This class makes th
 * 
 * 
 * @author Hughes
 */
public class JumpyGuy extends BasicEnemy{

	public static final int JUMP_FRAMES = 120, JUMPING_FRAMES = 10;
	public static final int JUMP_VEL = 13;

	Point followPoint;

	private int currentFrame;

	public JumpyGuy(float x, float y) {
		super(x, y, "assets/jumpy_guy.png", 1, 30);
		currentFrame = 0;
		followPoint = new Point(x, y);
	}

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
