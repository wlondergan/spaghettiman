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
				setX(getX()-diagVel);
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
			setX(getX()-vel);
		if(right)
			setX(getX()+vel);
		if(up)
			setY(getY()-vel);
		if(down)
			setY(getY()+vel);
	}

	public void update(ArrayList<EnvironmentMember> enemies) {
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
			for(Bullet b : bullets) {
				if(b.intersects(e)) {
					BasicEnemy t = (BasicEnemy)e;
					t.getHealthBar().takeDamage(damage);
				}
			}
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
