package gameMembers;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 * PlayerTest<br>
 * An example class for a possible player character. Extends the EnvironmentMember class.
 * @see EnvironmentMember
 * @author Hughes
 */
public class PlayerTest extends EnvironmentMember{
	
	/**
	 * Calls the EnvironmentMember constructor.
	 * @see EnvironmentMember
	 * @param x
	 * @param y
	 * @param ImagePath
	 */
	public PlayerTest(int x, int y){
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
