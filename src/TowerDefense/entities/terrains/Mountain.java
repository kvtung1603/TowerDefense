package TowerDefense.entities.terrains;

import TowerDefense.entities.towers.Tower;

public class Mountain extends Terrain{
//Attributes
	private Tower occupied;
	
//Methods
	public Mountain(float posX, float posY, int imgId) {
		this.posX = posX;
		this.posY = posY;
		this.imgId = imgId;
	}
	
	public boolean isOccupied() {
		return (occupied != null);
	}
	
	//getters & setters
	public Tower getOccupied() {return occupied;}
	public void setOccupied(Tower occupied) {this.occupied = occupied;}
	
}
