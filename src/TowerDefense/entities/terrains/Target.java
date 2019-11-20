package TowerDefense.entities.terrains;

import TowerDefense.Player;

public class Target extends Road{

	private Player player;
	public Target(float posX, float posY, int imgId, Player player) {
		super(posX, posY, imgId);
		this.player = player;
	}

	public Player getPlayer(){
		return this.player;
	}
	
	public void setPlayer(Player player){
		this.player = player;
	}
}
