package states;
/**
 * PauseScreen
 * @author Hughes
 * The PauseScreen class for the project. Should implement a PauseScreen, but only once we get further into the project
 * 
 * EDITS:
 * 1/4/2017: created class, commented thoroughly (Hughes)
 */

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PauseScreen extends BasicGameState{
	StateBasedGame game;//the StateBasedGame object, don't touch this

	PlayButton unpause;
	PlayButton mainMenu;

	@Override
	/**
	 * init
	 * @author Hughes
	 * initialized when this class is first created, use this to give values to starting variables and stuff
	 */
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		this.game = game; //don't touch this line
		unpause = new PlayButton(387, 300, "assets/unpausebutton.png");
		mainMenu = new PlayButton(387, 450, "assets/menubutton.png");
	}

	@Override
	/**
	 * render
	 * @author Hughes
	 * draws the screen in parallel with update, don't use this method to update the game state (AT ALL!!)
	 */
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(new Image("assets/pausescreen.png"), 0,0);
		unpause.draw(g);
		mainMenu.draw(g);
	}

	@Override
	/**
	 * update
	 * @author Hughes
	 * updates the game state in parallel with render: currently locked to 60fps
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
		if(gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			if(unpause.isClicked(gc.getInput().getMouseX(), gc.getInput().getMouseY()))
				game.enterState(2);
			else if(mainMenu.isClicked(gc.getInput().getMouseX(), gc.getInput().getMouseY())) {
				game.enterState(1);
				game.getState(2).init(gc, sbg);
			}
			
		}
	}

	@Override
	/**
	 * keyPressed
	 * @author Hughes
	 * This method is run whenever a key is pressed. Use this to handle input.
	 */
	public void keyPressed(int key, char c){
		switch(key){
		case Input.KEY_P:
			game.enterState(2);
			break;
		case Input.KEY_ESCAPE:
			game.enterState(2);
			break;
		}
	}

	@Override
	/**
	 * getId
	 * @author Hughes
	 * this method returns the numerical id of the class. Used to show the "state-ness" of this class.
	 */
	public int getID() {
		return 3; //third state after MainMenu and Game
	}
}
