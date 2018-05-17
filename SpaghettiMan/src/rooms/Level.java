package rooms;
import java.util.ArrayList;
import gameMembers.EnvironmentMember;

public class Level implements Runnable{
	private int stages;
	private Room[][] currentLevel;
	
	public Level(int stage) {
		run();
	}
	
	public static Room[][] generateLevel(int stage) {
		boolean bossGenerated = false;
		boolean itemGenerated = false;
		
		Room[][] level = new Room[stage+1][stage+1];//the level starts at 2x2 and increases by one every level
		
		//start by generating the starting room and generate the rest of the level. The starting room is always in the center room, so (0,0) on level 1 and (1,1) on level 2
		level[level.length/2][level.length/2] = new Room();
		
		
		
	}

	public static ArrayList<EnvironmentMember> itemRoom() {
		ArrayList<EnvironmentMember> item = new ArrayList<EnvironmentMember>();
		//add nothing to the list for now, but later I need to put an item in the room
		return item;
	}
	
	public static ArrayList<Door> generateDoors(Room[][] level, int row, int col){
		ArrayList<Door> doors = new ArrayList<Door>();
		if(row!=0)
			doors.add(new Door(Door.DoorDirection.LEFT));
			
	}
	
	
	
	@Override
	public void run() {
		currentLevel = generateLevel(stages);
	}
}
