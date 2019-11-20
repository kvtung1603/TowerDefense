package TowerDefense.entities.terrains;

import java.awt.Graphics;
import java.awt.Image;
import TowerDefense.entities.AbstractEntity;
import TowerDefense.entities.GameTile;

public abstract class Terrain extends AbstractEntity implements GameTile{
	
	public static Image[] TERRAIN_IMG = new Image[8];	

	protected int imgId;	

	@Override
	public void draw(Graphics g) {
		g.drawImage(TERRAIN_IMG[imgId], (int)posX, (int)posY, null);
	}
	
	public void drawInfo(Graphics g) {}
}
