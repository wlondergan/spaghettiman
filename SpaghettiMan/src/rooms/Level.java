package rooms;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import gameMembers.EnvironmentMember;
import gameMembers.Drawable;

public class Level{
	
	private int stage;
	private Room[][] currentLevel;
	public int currentR, currentC;
	
	public Level(int stage) {
		currentLevel = generateLevel(stage);
		currentR = currentC = currentLevel.length/2;
	}
	
	public static Room[][] generateLevel(int stage) {
		Room[][] level = new Room[stage+1][stage+1];//the level starts at 2x2 and increases by one every level
		//start by generating the starting room and generate the rest of the level. The starting room is always in the center room, so (0,0) on level 1 and (1,1) on level 2
		level[level.length/2][level.length/2] = new Room(new ArrayList<EnvironmentMember>(), generateDoors(level, level.length/2, level.length/2));
		
		int bossX, bossY;
		do {
			bossX = (int)(Math.random()*level.length);
			bossY = (int)(Math.random()*level.length);
		}while(level[bossX][bossY]!=null);
		level[bossX][bossY] = new Room(new ArrayList<EnvironmentMember>()/*TODO: generate boss room*/, generateDoors(level, bossX, bossY));
		
		int itemX, itemY;
		do {
			itemX = (int)(Math.random()*level.length);
			itemY = (int)(Math.random()*level.length);
		}while(level[itemX][itemY]!=null);
		level[itemX][itemY] = new Room(new ArrayList<EnvironmentMember>()/*TODO: generate item room*/, generateDoors(level, bossX, bossY));
		
		for(int i = 0; i<level.length; i++)
			for(int j = 0; j<level.length; j++)
				if(level[i][j] == null)
					level[i][j] = new Room(regRoom(stage), generateDoors(level, i, j));
		return level;
	}

	public static ArrayList<EnvironmentMember> itemRoom() {
		ArrayList<EnvironmentMember> item = new ArrayList<EnvironmentMember>();
		//add nothing to the list for now, but later I need to put an item in the room
		return item;
	}
	
	public static ArrayList<EnvironmentMember> regRoom(int level) {
		ArrayList<EnvironmentMember> enemies = new ArrayList<EnvironmentMember>();
		//TODO: Add enemies to this using procedural generation
		return enemies;
	}
	
	public static ArrayList<Door> generateDoors(Room[][] level, int row, int col){
		ArrayList<Door> doors = new ArrayList<Door>();
		if(row!=0)
			doors.add(new Door(Door.DoorDirection.LEFT));
		if(row!=level.length-1)
			doors.add(new Door(Door.DoorDirection.RIGHT));
		if(col!=0)
			doors.add(new Door(Door.DoorDirection.UP));
		if(col!= level.length-1)
			doors.add(new Door(Door.DoorDirection.DOWN));
		return doors;
	}
	
	public static final int LEFT = 1, UP = 2, RIGHT = 3, DOWN = 4;
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
