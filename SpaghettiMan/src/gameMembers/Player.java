package gameMembers;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 * The player character. Directly interacted with by the player of the game.<br>
 * Key presses are intended to work directly with this class.
 * @see EnvironmentMember
 * @author Hughes
 */
public class Player extends EnvironmentMember{

	private static final float VEL = 3f;
	private static final float DIAG_VEL = VEL/1.414f;

	/**
	 * Calls the EnvironmentMember constructor.
	 * @see EnvironmentMember
	 * @param x  The x location of the Player
	 * @param y  The y location of the Player
	 */
	public Player(int x, int y){
		super(x, y, "assets/dude.png", 0.25f);
	}

	private boolean left, right, up, down;

	public void keyDown(int key){
		switch(key){
		case Input.KEY_A:
			left = true;
			break;
		case Input.KEY_D:
			right = true;
			break;
		case Input.KEY_S:
			down = true;
			break;
		case Input.KEY_W:
			up = true;
			break;
		}
	}

	public void keyUp(int key){
		switch(key){
		case Input.KEY_A:
			left = false;
			break;
		case Input.KEY_D:
			right = false;
			break;
		case Input.KEY_S:
			down = false;
			break;
		case Input.KEY_W:
			up = false;
			break;
		}
	}

	public void updateLoc(){
		if(left&&up||up&&right||right&&down||left&&down) {
			if(left&&down) {
				setX(getX()-DIAG_VEL);
				setY(getY()+DIAG_VEL);
			}
			if(left&&up) {
				setX(getX()-DIAG_VEL);
				setY(getY()-DIAG_VEL);
			}
			if(right&&down) {
				setX(getX()+DIAG_VEL);
				setY(getY()+DIAG_VEL);
			}
			if(right&&up) {
				setX(getX()+DIAG_VEL);
				setY(getY()-DIAG_VEL);
			}
			return;
		}
		if(left)
			setX(getX()-3);
		if(right)
			setX(getX()+3);
		if(up)
			setY(getY()-3);
		if(down)
			setY(getY()+3);
	}

	@Override
	public void draw(Graphics g){
		g.drawImage(getImage().getScaledCopy(getScale()), getX(), getY());
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}

	public boolean isUp() {
		return up;
	}

	public boolean isDown() {
		return down;
	}

}
