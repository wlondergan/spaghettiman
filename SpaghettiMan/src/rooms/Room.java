package rooms;

import java.util.ArrayList;

import gameMembers.EnvironmentMember;

public class Room {
	private ArrayList<EnvironmentMember> members;
	private ArrayList<Door> doors;
	
	public Room(ArrayList<EnvironmentMember> members, ArrayList<Door> doors) {
		this.members = members;
		this.doors = doors;
	}
	
	
}
