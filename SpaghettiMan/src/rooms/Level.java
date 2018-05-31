package rooms;
import java.util.ArrayList;

import enemies.JumpyGuy;
import enemies.ShootyGuy;
import enemies.Slime;
import gameMembers.EnvironmentMember;
import enemies.BasicEnemy;

/**
 * This class takes care of level generation and storage.<br>
 * It contains a number of static methods to help generate a level, most notably the {@code generateLevel} method which generate the entire level.<br>
 * The {@code Level} class can also have an object constructed of it which contains all of the rooms and enemies and doors and enemies needed to make the game run.
 * @author Hughes
 */
public class Level{
	
	private int stage;//the current stage (level?) of the level
	private Room[][] currentLevel;//the array that stores the level data as Rooms
	public int currentR, currentC;//the current location in the level
	
	/**
	 * The only constructor. Takes stage as a parameter, although perhaps that should just be removed altogether.<br>
	 * Constructs the entire level and gives values to all of the rooms.
	 * @param stage  The current stage of the level
	 */
	public Level(int stage) {
		currentLevel = generateLevel(stage);
		currentR = currentC = (currentLevel.length-1)/2;
		this.stage = stage;
	}
	
	/**
	 * This method is the heart of the class. It generates every level procedurally.
	 * @param stage  The level of the game
	 * @return the level as a {@code Room[][]}
	 */
	public static Room[][] generateLevel(int stage) {
		Room[][] level = new Room[stage+1][stage+1];//the level starts at 2x2 and increases by one every level
		//start by generating the starting room and generate the rest of the level. The starting room is always in the center room, so (0,0) on level 1 and (1,1) on level 2
		level[(level.length-1)/2][(level.length-1)/2] = new Room(new ArrayList<BasicEnemy>(), generateDoors(level, (level.length-1)/2, (level.length-1)/2), new ArrayList<Item>());
		
		int bossX, bossY;
		do {
			bossX = (int)(Math.random()*level.length);
			bossY = (int)(Math.random()*level.length);
		}while(level[bossX][bossY]!=null);
		level[bossX][bossY] = new Room(new ArrayList<BasicEnemy>()/*TODO: generate boss room*/, generateDoors(level, bossX, bossY), new ArrayList<Item>());
		
		int itemX, itemY;
		do {
			itemX = (int)(Math.random()*level.length);
			itemY = (int)(Math.random()*level.length);
		}while(level[itemX][itemY]!=null);
		level[itemX][itemY] = new Room(new ArrayList<BasicEnemy>()/*TODO: generate item room*/, generateDoors(level, itemX, itemY), new ArrayList<Item>());
		
		for(int i = 0; i<level.length; i++)
			for(int j = 0; j<level.length; j++)
				if(level[i][j] == null)
					level[i][j] = new Room(regRoom(stage), generateDoors(level, i, j), new ArrayList<Item>());
		return level;
	}

	/**
	 * Generates an item room list of enemies and objects.
	 * @return  The {@code ArrayList} of objects for an item room
	 */
	public static ArrayList<BasicEnemy> itemRoom() {
		ArrayList<BasicEnemy> item = new ArrayList<BasicEnemy>();
		//add nothing to the list for now, but later I need to put an item in the room
		return item;
	}
	
	/**
	 * Generates a normal room with normal enemies based on the level.
	 * @param level  The current level, used to determine difficulty
	 * @return  An {@code ArrayList} of enemies for the room
	 */
	public static ArrayList<BasicEnemy> regRoom(int level) {
		ArrayList<BasicEnemy> enemies = new ArrayList<BasicEnemy>();
		//TODO: Add enemies to this using procedural generation
		enemies.add(new ShootyGuy(500,500));
		enemies.add(new JumpyGuy(500,500));
		enemies.add(new Slime(500,500));
		
		return enemies;
	}
	
	/**
	 * Generates a set of doors for a new room given its location in the level.
	 * @param level  The preexisting level
	 * @param row  The row of the room
	 * @param col  The column of the room
	 * @return {@code ArrayList} of doors for the room
	 */
	public static ArrayList<Door> generateDoors(Room[][] level, int row, int col){
		ArrayList<Door> doors = new ArrayList<Door>();
		if(row>0)
			doors.add(new Door(Door.DoorDirection.LEFT));
		if(row<level.length-1)
			doors.add(new Door(Door.DoorDirection.RIGHT));
		if(col>0)
			doors.add(new Door(Door.DoorDirection.UP));
		if(col<level.length-1)
			doors.add(new Door(Door.DoorDirection.DOWN));
		return doors;
	}
	
	public static final int LEFT = 1, UP = 2, RIGHT = 3, DOWN = 4;
	/**
	 * This method should be invoked whenever a {@code Door} is touched by the character.<br>
	 * It moves the location of the character in the direction of the {@code Door} that they touch.<p>
	 * There are 4 constant direction {@code Integers}: {@code LEFT}, {@code RIGHT}, {@code UP}, and {@code DOWN}.<br>
	 * They should be passed to this method in the direction of movement.
	 * @param direction  The direction to be moved
	 */
	public void move(int direction) {
		switch(direction) {
		case LEFT:
			currentR--;
			break;
		case UP:
			currentC--;
			break;
		case RIGHT:
			currentR++;
			break;
		case DOWN:
			currentC++;
			break;
		}
	}
	
	public Room getCurrentRoom() {
		return currentLevel[currentR][currentC];
	}
}
