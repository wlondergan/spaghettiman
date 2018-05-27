package enemies;

import org.newdawn.slick.Graphics;

import gameMembers.Point;

public class JumpyGuy extends BasicEnemy{

	public static final int JUMP_FRAMES = 45;
	public static final int JUMP_VEL = 15;

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
		if(currentFrame == 45)
			followPoint = p;
		if(getX()!=p.x&&getY()!=p.y)
			follow(p, JUMP_VEL);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getImage().getScaledCopy(getScale()), getX(), getY());
	}

}
