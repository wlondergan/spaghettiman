package rooms;

import gameMembers.EnvironmentMember;

public class Door extends EnvironmentMember{

	public DoorDirection dir;
	
	public Door(DoorDirection dir) {
		super(getX(dir), getY(dir), "door path", 1);
		this.dir = dir;
	}
	
	public enum DoorDirection{
		LEFT, RIGHT, UP, DOWN;
	}
	
	private static float getX(DoorDirection dir) {
		if(dir == DoorDirection.UP||dir == DoorDirection.DOWN)
			return 1024/2-50;
		else if(dir == DoorDirection.LEFT)
			return 0;
		else
			return 1024-10;
	}
	
	private static float getY(DoorDirection dir) {
		if(dir == DoorDirection.LEFT||dir == DoorDirection.RIGHT)
	}
	
}
