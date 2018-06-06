package rooms;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;

import enemies.BasicEnemy;
import gameMembers.Drawable;

/**
 * This class represents a room in a level.<br>
 * Each level is separated up into rooms, all of which share the characteristics of this class.<br>
 * This class possesses an {@code ArrayList} of {@link gameMembers.EnvironmentMember} objects which are intended to be enemy objects.<br>
 * @author Hughes
 */
public class Room extends Drawable{
	private ArrayList<BasicEnemy> members;
	private ArrayList<Door> doors;
	private ArrayList<Item> items;
	
	/**
	 * The only constructor of the class. It assigns passed in values to the members and doors lists.
	 * @param members  The {@code ArrayList} of members
	 * @param doors  The {@code ArrayList} of doors
	 * @param items  The {@code ArrayList} of items
	 */
	public Room(ArrayList<BasicEnemy> members, ArrayList<Door> doors, ArrayList<Item> items) {
		super(0,0);
		this.members = members;
		this.doors = doors;
		this.items = items;
	}

	/**
	 * Overrides the {@code draw} method from the {@link gemeMembers.Drawable} class.<br>
	 * This method individually draws all of the members of this class from the lists.
	 */
	@Override
	public void draw(Graphics g) {
		//TODO: make the room look pretty
		for(Door d: doors)
			d.draw(g);
		for(BasicEnemy e: members)
			e.draw(g);
		for(Item i: items)
			i.draw(g);
	}

	public ArrayList<BasicEnemy> getMembers() {
		return members;
	}

	public ArrayList<Door> getDoors() {
		return doors;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
}
