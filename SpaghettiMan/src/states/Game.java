package states;
/**
 * @author Hughes
 * The Game class for the SpaghettiMan project. The main gamestate for the project.
 */

import java.awt.Rectangle;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
//imports
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import enemies.BasicEnemy;
import enemies.Slime;
import gameMembers.Alert;
import gameMembers.Bullet;
import gameMembers.Player;
import rooms.Door;
import rooms.Item;
import rooms.Level;

public class Game extends BasicGameState{

	Rectangle left, right, up, down;

	StateBasedGame game; //the StateBasedGame object

	Player p;

	Level l;

	Alert a;

	/**
	 * init<br>
	 * runs when the class is created inside a window, use this to initialize the variables and everything needed for the game
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		this.game = game;//don't touch this line

		p = new Player(0,0);//initialize p at the point (0,0)

		l = new Level(1);

		gc.setMouseCursor(new Image("assets/cursor.png"), 25, 25);//changes the game's mouse cursor, couldn't do it in GraphicsMain because it isn't an OpenGL class (shrug)

		a = new Alert(200, 0, "Level 1");

		left = new Rectangle(0, 0, 1, 576);

		right = new Rectangle(1023, 0, 1, 576);

		up = new Rectangle(0,0, 1024, 1);

		down = new Rectangle(0, 575, 1024, 1);
	}


	/**
	 * render<br>
	 * runs as fast as the computer can handle it, drawing everything to the screen (actually, locked to 60FPS for the moment).<br>
	 * Has a GameContainer object, or the game
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		p.draw(g);//draw everything to the screen using the draw method of all of the Drawables (TODO: add everything to an array and draw it in one loop?)
		l.getCurrentRoom().draw(g);
		a.draw(g);
	}

	/**
	 * update<br>
	 * runs in parallel with the render method. Use this to actually update the game and use render to draw it all.
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame game, int i) throws SlickException {
		p.updateLoc();
		for(int j = p.getBullets().size()-1; j>=0; j--) {
			if(p.getBullets().get(j).getX() < 0||p.getBullets().get(j).getX() > 1024||p.getBullets().get(j).getY() < 0 || p.getBullets().get(j).getY() > 576)
				p.getBullets().remove(j);
		}

		if(p.getHitbox()[0].intersects(left))
			p.setX(0);
		else if(p.getHitbox()[0].intersects(right))
			p.setX(1024-p.getImage().getScaledCopy(p.getScale()).getWidth());

		if(p.getHitbox()[0].intersects(up))
			p.setY(0);
		else if(p.getHitbox()[0].intersects(down))
			p.setY(576-p.getImage().getScaledCopy(p.getScale()).getHeight());

		p.update(l.getCurrentRoom().getMembers());

		if(gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
			p.mousePressed(gc.getInput().getMouseX(), gc.getInput().getMouseY());

		for(Door d : l.getCurrentRoom().getDoors())
			if(p.intersects(d))
				if(d.dir == Door.DoorDirection.END) {
					l = new Level(l.getStage()+1);
					a.setCurrentMessage("Level " + l.getStage());
					return;
				}
				else if(d.dir == Door.DoorDirection.LEFT) {
					l.move(Level.LEFT);
					p.setX(Door.getX(Door.DoorDirection.RIGHT)-p.getImage().getScaledCopy(p.getScale()).getWidth());
				}
				else if(d.dir == Door.DoorDirection.UP) {
					l.move(Level.UP);
					p.setY(Door.getY(Door.DoorDirection.DOWN)-p.getImage().getScaledCopy(p.getScale()).getHeight());
				}
				else if(d.dir == Door.DoorDirection.RIGHT) {
					l.move(Level.RIGHT);
					p.setX(Door.getX(Door.DoorDirection.LEFT)+10);
				}
				else {
					l.move(Level.DOWN);
					p.setY(Door.getY(Door.DoorDirection.UP)+10);
				}

		for(BasicEnemy t : l.getCurrentRoom().getMembers())
			t.update(p.getCenter());

		for(int n = l.getCurrentRoom().getMembers().size()-1; n>=0; n--)
			if(l.getCurrentRoom().getMembers().get(n).getHealthBar().isDead()) {
				Item.ItemValue e = Item.pickRandomDrop();
				if(e!=null)
					l.getCurrentRoom().getItems().add(new Item(l.getCurrentRoom().getMembers().get(n).getX(), l.getCurrentRoom().getMembers().get(n).getY(), 1, e));
				l.getCurrentRoom().getMembers().remove(n);
			}

		for(int r = l.getCurrentRoom().getItems().size()-1; r>=0; r--)
			if(p.intersects(l.getCurrentRoom().getItems().get(r))) {
				Item.ItemValue e = l.getCurrentRoom().getItems().get(r).getVal();
				p.intersectsItem(e);
				l.getCurrentRoom().getItems().remove(r);

				switch(e) {
				case ATTACK:
					a.setTempMessage("Attack Up");
					break;
				case ATTACK_SPEED:
					a.setTempMessage("Attack Speed Up");
					break;
				case HEALTH:
					a.setTempMessage("Health Increased");
					break;
				case POTION:
					a.setTempMessage("Health Restored");
					break;
				case SPEED:
					a.setTempMessage("Speed Increased");
					break;
				}
			}
	}

	/**
	 * keyPressed<br>
	 * This method is run whenever a key is pressed. Use this to handle input.<br>
	 * Passed int key, the key that was pressed. Ignore char c.
	 */
	@Override
	public void keyPressed(int key, char c){
		p.keyDown(key);
		switch(key){//switch around the key input

		case Input.KEY_1://if it's "1" on the keyboard, do:
			game.enterState(1);//enters the #1 gameState (the class whose getId() method returns 1)
			break;

		case Input.KEY_3://if it's "3" on the keyboard, do:
			game.enterState(3);//enters the #3 gameState (the class whose getId() method returns 3)
			break;

		}

	}

	/**
	 * keyReleased<br>
	 * This method is run whenever a keyboard button is released. int key is the key that was released, not sure what char c is tho, pretend it's not a thing
	 */
	@Override
	public void keyReleased(int key, char c){
		p.keyUp(key);//calls the keyUp method of the PlayerTest object and lets it handle that problem
	}

	/**
	 * getId
	 * this method returns the numerical id of the class. Used to show the "state-ness" of this class.
	 */
	@Override
	public int getID() {
		return 2;//it's the second state
	}
}





