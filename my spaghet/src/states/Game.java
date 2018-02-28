package states;
/**
 * @author Hughes
 * The Game class for the SpaghettiMan project. The main gamestate for the project.
 */

import java.awt.Rectangle;
import java.util.ArrayList;

import org.newdawn.slick.Color;//imports
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import enemies.Slime;
import gameMembers.Bullet;
import gameMembers.PlayerTest;

public class Game extends BasicGameState{
	StateBasedGame game; //the StateBasedGame object
	PlayerTest p;
	ArrayList<Bullet> bullets;
	Slime s;
	
	/**
	 * init<br>
	 * runs when the class is created inside a window, use this to initialize the variables and everything needed for the game
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		this.game = game;//don't touch this line
		
		p = new PlayerTest(0,0);//initialize p at the point (0,0)
		
		bullets = new ArrayList<>();//initialize the bullets ArrayList
		
		s = new Slime(0, 300);
		
		gc.setMouseCursor(new Image("assets/cursor.png"), 25, 25);//changes the game's mouse cursor, couldn't do it in GraphicsMain because it isn't an OpenGL class
	}


	/**
	 * render<br>
	 * runs as fast as the computer can handle it, drawing everything to the screen (actually, locked to 60FPS for the moment).<br>
	 * Has a GameContainer object, or the game
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		p.draw(g);
		s.draw(g);
		for(int i =0; i<bullets.size(); i++) {
			bullets.get(i).draw(g);
		}
	}

	/**
	 * update<br>
	 * runs in parallel with the render method. Use this to actually update the game and use render to draw it all.
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame game, int i) throws SlickException {
		p.updateLoc();
		s.move(p.getLoc());
		for(int j =0; j<bullets.size(); j++) {//update all bullet locations
			bullets.get(j).updateLoc();
		}
		for(int j = bullets.size()-1; j>=0; j--)//check intersection for all of the bullets
			if(!(bullets.get(j).getHitbox().intersects(new Rectangle(0,0, game.getContainer().getWidth(), game.getContainer().getHeight()))))//if it doesn't collide with the screen, remove it
					bullets.remove(j);
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
	 * mousePressed<br>
	 * This method is run whenever a mouse button is pressed. 
	 * @Param int button: which button on the mouse was pressed
	 * @Param int x: the current x location of the cursor
	 * @Param int y: the current y location of the cursor
	 */
	@Override
	public void mousePressed(int button, int x, int y){
		bullets.add(new Bullet((float)p.getHitbox().getCenterX(),(float)p.getHitbox().getCenterY(),(float)x, (float)y));
	}
	
	/**
	 * keyReleased<br>
	 * This method is run whenever a keyboard button is released. int key is the key that was released, not sure what char c is tho
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





