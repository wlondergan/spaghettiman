package states;

import java.awt.Rectangle;
import gameMembers.EnvironmentMember;

public class PlayButton extends EnvironmentMember{

	public PlayButton(float x, float y, String imagepath) {
		super(x, y, imagepath, .5f);
	}
	
	public boolean isClicked(int x, int y) {
		if(new Rectangle(x,y,1,1).intersects(this.getHitbox()[0]))
			return true;
		return false;
	}
}
