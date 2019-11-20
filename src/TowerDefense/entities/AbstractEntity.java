package TowerDefense.entities;

import java.awt.Graphics;

public abstract class AbstractEntity implements GameEntity{

	protected float posX;
	protected float posY;

	public abstract void draw(Graphics g);
	public abstract void drawInfo(Graphics g);
	
	public boolean isInRect(int x, int y, int w, int h) {
		return (x < posX && posX < x+w) && (y < posY && posY < y+h);
	}
	
	public float getDistance(AbstractEntity other) {
		float deltaX = posX - other.posX;
		float deltaY = posY - other.posY;
		return (float)Math.sqrt(deltaX*deltaX + deltaY*deltaY);
	}
	
	//getters & setters
	@Override
	public float getPosX() {return posX;}
	public float getPosY() {return posY;}
	public void setPosX(float posX) {this.posX = posX;}
	public void setPosY(float posY) {this.posY = posY;}
	
}
