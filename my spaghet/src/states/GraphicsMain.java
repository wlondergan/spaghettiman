package states;

import org.newdawn.slick.AppGameContainer; //imports
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
/**
 * The Main class for the SpaghettiMan project. Basically just sets up the game and doesn't do much else
 * @author Hughes
 */

public class GraphicsMain extends StateBasedGame{
	/**
	 * GraphicsMain
	 * @author Hughes
	 * The constructor for the GraphicsMain class. Don't touch this.
	 */
	public GraphicsMain(String gamename)
	{
		super(gamename);//Calls the constructor of the StateBasedGame class, basically makes everything work
	}

	/**
	 * initStatesList
	 * @author Hughes
	 * implements a method required to make the StateBasedGame work. Basically black magic.
	 */
	public void initStatesList(GameContainer appgc) {
		addState(new MainMenu());//adds the states to the StateBasedGame, MainMenu is the class that's started when you run the program (don't touch this unless you add more states to the game)
		addState(new Game());
		addState(new PauseScreen());
	}
	
	/**
	 * main
	 * @author Hughes
	 * The main method. Starts the whole window thing. Pretend I know how it works.
	 */
	public static void main(String[] args){
		try{//throws an exception so you need to use the try-catch block
			AppGameContainer appgc = new AppGameContainer(new GraphicsMain("squares"));
			appgc.setDisplayMode(1024, 576, false); // To do: Implement an extra window that lets you pick screen resolution, maybe
			appgc.setTargetFrameRate(60);//locks the framerate
			appgc.setVSync(true);//turns on vsync
			appgc.setShowFPS(false);//turns off the fps counter 
			appgc.start();//starts the window
		}catch (SlickException ex){}
	}
}
