package rooms;
import gameMembers.EnvironmentMember;

public abstract class Item extends EnvironmentMember{
	
	private ItemValue val;

	public Item(float x, float y, String imagePath, float scale, ItemValue e) {
		super(x,y,imagePath,scale);
		val = e;
	}
	
	public enum ItemValue{
		HEALTH, SPEED, ATTACK, POTION, ATTACK_SPEED;//handle this in the Player class, I really don't have the time left to make this class handle everything.
	}
}
