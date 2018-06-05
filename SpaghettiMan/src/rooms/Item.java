package rooms;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

import gameMembers.EnvironmentMember;

/**
 * This class contains (most) of the information necessary to make an item. The {@link gameMembers.Player} class has all of the logic associated with getting an item in it, however.<br>
 * However, this class contains everything else (the location of the item, the drawing of the item.)
 * @author Hughes
 * @see gameMembers.EnvironmentMember
 * @see gameMembers.Player
 */
public class Item extends EnvironmentMember{

	/**
	 * This is a list of all of the values. It's intended to make random item selection easier.
	 */
	private static final List<ItemValue> VALUES = Collections.unmodifiableList(Arrays.asList(ItemValue.values()));
	/**
	 * This is a list of all of the item values that can be dropped by enemies.
	 */
	private static final List<ItemValue> DROP_VALUES = Collections.unmodifiableList(Arrays.asList(ItemValue.POTION, null, null, null));

	private ItemValue val;

	/**
	 * The only constructor for the class. It assigns this item a value
	 * @param x  the x location of the item
	 * @param y  the y location of the item
	 * @param scale  the scale of the item
	 * @param e  the value of the item, {@link Item.ItemValue}
	 */
	public Item(float x, float y, float scale, ItemValue e) {
		super(x,y,chooseImage(e),scale);
		val = e;
	}

	private static String chooseImage(ItemValue e) {
		switch(e) {
		case POTION:
			return "assets/potion.png";
		case HEALTH:
			return "assets/heart.png";
		case SPEED:
			return "assets/bolt.png";
		case ATTACK:
			return "assets/sword.png";
		case ATTACK_SPEED:
			return "assets/attackspeed.png";
		default:
			return null;
		}
	}

	public ItemValue getVal() {
		return val;
	}

	/**
	 * This enum contains all of the values that an item can have.
	 */
	public enum ItemValue{
		HEALTH, SPEED, ATTACK, POTION, ATTACK_SPEED;//handle this in the Player class, I really don't have the time left to make this class handle everything.
	}

	/**
	 * Picks a random item from the items list. Intended to be used with the constructor so that a random item will be chosen.
	 * @return  a random item value
	 */
	public static ItemValue pickRandomItem() {
		return VALUES.get((int)(Math.random()*VALUES.size()));
	}

	/**
	 * Picks a random drop value, for when an enemy is killed. Only a subset of items will come from this method.
	 * @return
	 */
	public static ItemValue pickRandomDrop() {
		return DROP_VALUES.get((int)(Math.random()*DROP_VALUES.size()));
	}
}
