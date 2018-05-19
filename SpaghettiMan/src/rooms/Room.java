package rooms;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import gameMembers.Drawable;
import gameMembers.EnvironmentMember;

public class Room extends Drawable{
	private ArrayList<EnvironmentMember> members;
	private ArrayList<Door> doors;
	
	public Room(ArrayList<EnvironmentMember> members, ArrayList<Door> doors) {
		super(0,0);
		this.members = members;
		this.doors = doors;
	}

	@Override
	public void draw(Graphics g) {
		//TODO: make the room look pretty
		for(Door d: doors)
			d.draw(g);
		for(EnvironmentMember e: members)
			e.draw(g);
	}

	public ArrayList<EnvironmentMember> getMembers() {
		return members;
	}

	public ArrayList<Door> getDoors() {
		return doors;
	}
	
}
