package gameMembers;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import enemies.BasicEnemy;
import enemies.ShootyGuy;

/**
 * The player character. Directly interacted with by the player of the game.<br>
 * Key presses are intended to work directly with this class.
 * @see EnvironmentMember
 * @author Hughes
 */
public class Player extends EnvironmentMember{

	private int health;
	private int currentFrame;

	private float vel;
	private float diagVel;
	private static final int INVINCIBILITY_FRAMES = 20;
	private int bulletFrames;
	private float damage;

	private ArrayList<Bullet> bullets;

	/**
	 * Calls the EnvironmentMember constructor.
	 * @see EnvironmentMember
	 * @param x  The x location of the Player
	 * @param y  The y location of the Player
	 */
	public Player(int x, int y){
		super(x, y, "assets/dude.png", 0.25f);
		health = 100;
		currentFrame = 20;
		bullets = new ArrayList<Bullet>();
		vel = 3f;
		diagVel = vel/1.414f;
		bulletFrames = 20;
		damage = 15;

	}

	private boolean left, right, up, down;

	/**
	 * This method accepts a key input as the parameter. <br>
	 * This should be called as an input from some other class that handles input.<p>
	 * Every input comes in as an integer and should be used in conjunction with the Input constants.<br>
	 * For example, {@code Input.KEY_A} will be the integer passed to this method when the "A" button is pressed on the keyboard.
	 * 
	 * @param key  the key that was pressed, as an integer from the {@code Input} class.
	 */
	public void keyDown(int key){
		switch(key){
		case Input.KEY_A://turns the direction booleans on when a key is pressed.
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

	/**
	 * The opposite of the {@link keyDown} method. It turns off direction variables when the appropriate keys are pressed.<br>
	 * See {@code keyDown} for a more complete description of what the {@code key} integer is.
	 * @param key  The key that was released, as an integer from the {@code Input} class.
	 */
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

	/**
	 * This method updates the location of the character based on what keys are pressed.<br>
	 * It handles diagonal movement as an up and down movement, both of which are roughly the normal velocity divided by the square root of two, per the pythagorean theorem.<br>
	 * <p>
	 * This method should be called from the main class that handles game objects, to update the location of the character.
	 */
	public void updateLoc(){
		if(left&&up||up&&right||right&&down||left&&down) {
			if(left&&down) {
				setX(getX()-diagVel);//diagonal movements
				setY(getY()+diagVel);
			}
			if(left&&up) {
				setX(getX()-diagVel);
				setY(getY()-diagVel);
			}
			if(right&&down) {
				setX(getX()+diagVel);
				setY(getY()+diagVel);
			}
			if(right&&up) {
				setX(getX()+diagVel);
				setY(getY()-diagVel);
			}
			return;
		}
		if(left)
			setX(getX()-vel);//vertical movements
		if(right)
			setX(getX()+vel);
		if(up)
			setY(getY()-vel);
		if(down)
			setY(getY()+vel);
	}

	/**
	 * This method handles the collisions and damage from the player and enemies.<br>
	 * It takes in a list of enemies from the room and decides whether or not the character should accept any damage from them.
	 * If the character is damaged, it goes into invincibility frames, during which period of time the character can't take any damage from the environment.<br>
	 * Otherwise, it checks to see if the character is intersecting any enemies (or their projectiles) and determines whether or not the character should take any damage. 
	 * @param enemies  an {@code ArrayList} of enemies that the character could take or give damage from/to
	 */
	public void update(ArrayList<EnvironmentMember> enemies) {
		
		for(EnvironmentMember e: enemies) {
			for(Bullet b : bullets) {
				if(b.intersects(e)) {
					BasicEnemy t = (BasicEnemy)e;
					t.getHealthBar().takeDamage(damage);
				}
			}
		}
		if(currentFrame<20) {
			currentFrame++;
			return;
		}
		for(EnvironmentMember e: enemies) {
			if(e instanceof ShootyGuy) {
				ShootyGuy t = (ShootyGuy)e;//gross but what can you do
				for(int i = t.getBullets().size()-1; i>=0; i--) {
					if(intersects(t.getBullets().get(i))) {
						health -= 10;
						t.getBullets().remove(i);
					}
				}
			}

			if(intersects(e))
				health-=20;
		}


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

	public int getHealth() {
		return health;
	}
}
