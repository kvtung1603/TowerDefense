package TowerDefense;

import java.util.List;

import TowerDefense.entities.enemies.Enemy;
import TowerDefense.entities.terrains.Mountain;
import TowerDefense.entities.towers.Tower;

public class Player {

	private int cash;
	private int lives;
	private int level;	
	
	public Player() {
		this.cash = 900;
		this.lives = 4;
		this.level = 0;
	}
	
	public void buyTower(Tower t, Mountain m, List<Tower> towers) {
		if (t.isInRect(Config.SHOP_X, Config.SHOP_Y, Config.SHOP_W, Config.SHOP_H)) {
			Tower newTower = t.clone();
			newTower.setPosX(m.getPosX());
			newTower.setPosY(m.getPosY());
			m.setOccupied(newTower);
			towers.add(newTower);
			cash -= newTower.getPrice();
		}
	}
	
	public void playerRemoveTower(Tower t , Mountain m , List <Tower> towers) {
		if (t.getPosX() < Config.GRID_WIDTH && t.getPosY() < Config.GRID_HEIGHT) {
	        cash += t.getPrice()* Tower.DEPRECIATION ;
	        towers.remove(t) ;
	        m.setOccupied(null) ;
		}
    }

    public void playerUpgradeTower(Tower t) {
    	if (t.getPosX() < Config.GRID_WIDTH && t.getPosY() < Config.GRID_HEIGHT && t.getType() == 1 && cash >= t.getUpgradeCost() ) {
            t.upgrade();
            cash = cash - t.getUpgradeCost();
        }
    }
	
	public void receiveReward(Enemy e) {
		cash += e.getReward();
	}

//getters & setters
	public int getCash() {return cash;}
	public void setCash(int cash) {this.cash = cash;}

	public int getLives() {return lives;}
	public void setLives(int lives) {this.lives = lives;}
	public boolean isAlive() {return lives > 0;}

	public int getLevel() {return level;}
	public void nextLevel() {this.level += 1;}
}
	