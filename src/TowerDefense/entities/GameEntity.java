package TowerDefense.entities;

import java.awt.Graphics;

public interface GameEntity {
	static final int WIDTH = 64;
	static final int HEIGHT = 64;
	
	public abstract void draw(Graphics g);
	public abstract void drawInfo(Graphics g);
	
	public abstract float getPosX();
	public abstract float getPosY();
}
