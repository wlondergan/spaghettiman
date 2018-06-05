package rooms;

import gameMembers.EnvironmentMember;

/**
 * This class is meant to represent a door.<br>
 * Doors are unmoving objects that exist in corners of the room and change rooms when they are touched.<br>
 * Each door possessses a DoorDirection member of the DoorDirection enum. When constructing an {@code Door} object a direction is given (left, right, up, down).<br>
 * The {@code Door} will place itself in the correct location in the room based its location.<br>
 * This class extends the {@link EnvironmentMember} class so that it can intersect the main character for switching rooms.
 * @author Hughes
 * @see EnvironmentMember
 */
public class Door extends EnvironmentMember{

	/**
	 * This is the direction of the door. A {@code LEFT} door location value will put the {@code Door} halfway up the left wall, for example.
	 */
	public DoorDirection dir;
	//TODO: boolean opening/closing doors when enemies are still alive?
	
	/**
	 * This is the only constructor for the class. The only parameter is the door direction.
	 * @param dir  The direction of the {@code Door}, or which wall the {@code Door} should be on. A {@link Door.DoorDirection} variable.
	 */
	public Door(DoorDirection dir) {
		super(getX(dir), getY(dir), getImage(dir), 1);
		this.dir = dir;
	}
	
	/**
	 * This enum is possessed by the {@code Door} class with the intent of giving a direction to any {@code Door} which is kind of important in a square room.
	 */
	public static enum DoorDirection{
		LEFT, RIGHT, UP, DOWN, END;
	}
	
	public static float getX(DoorDirection dir) {//gets the x location for any door given the direction
		if(dir == DoorDirection.END)
			return 1024/2-50;
		if(dir == DoorDirection.UP||dir == DoorDirection.DOWN)
			return 1024/2-50;
		else if(dir == DoorDirection.LEFT)
			return 0;
		else
			return 1024-10;
	}
	
	public static float getY(DoorDirection dir) {//gets the y location for any door given the direction
		if(dir == DoorDirection.END)
			return 576/2-50;
		if(dir == DoorDirection.LEFT||dir == DoorDirection.RIGHT)
			return 576/2-50;
		else if(dir == DoorDirection.UP)
			return 0;
		else
			return 576-10;
	}
	
	private static String getImage(DoorDirection dir) {//gets the image path for a door given the direction
		if(dir == DoorDirection.END)
			return "assets/hole.png";
		if(dir == DoorDirection.LEFT || dir==DoorDirection.RIGHT)
			return "assets/DoorSide.png";
		else
			return "assets/DoorTop.png";
	}
	
}
