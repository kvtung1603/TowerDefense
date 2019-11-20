package TowerDefense;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import TowerDefense.entities.AbstractEntity;
import TowerDefense.entities.terrains.*;
import TowerDefense.entities.towers.*;
import TowerDefense.entities.enemies.*;

public class GameField {

	private boolean playing = true;
	
	Player player;	
		
	List<Tower> shop;	
		
	Terrain[][] map;
	
	Spawner spawner;
	
	Target target;
	
	List<Tower> towers;				
	
	List<Enemy> enemies;			
	
	AbstractEntity click;	
	
	AbstractEntity hover;		

	public GameField(int mapID) {
		player = new Player();
		shop = Config.SHOP;
		towers = new LinkedList<Tower>();
		enemies = new LinkedList<Enemy>();
		map = new Terrain[10][17];
		loadMap(mapID);
	}
	
	public void selectEntityIfHover(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		//if mouse's on grid
		if (mouseX < Config.GRID_WIDTH && mouseY < Config.GRID_HEIGHT) {
			int i = mouseY/64;
			int j = mouseX/64;
		if (map[i][j] instanceof Mountain) {
				Mountain mountain = (Mountain)map[i][j];
				hover = mountain.getOccupied();
		}
		else {
			for (Enemy enemy : enemies) {
				if (mouseInRect(mouseX, mouseY, (int)enemy.getPosX(), (int)enemy.getPosY(), 64, 64)) {
					hover = enemy; 
					break;
				}
			}
		}
		} else
			if (mouseInRect(mouseX, mouseY, Config.SHOP_X, Config.SHOP_Y, Config.SHOP_W, Config.SHOP_W)) {
				for (Tower tower : shop) {
					if (mouseInRect(mouseX, mouseY, (int)tower.getPosX(), (int)tower.getPosY(), 64, 64)) {
						hover = tower; 
						break;
					}
				}				
			} 
		else hover = null;
		}
	public void selectEntityIfClick(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
			
		if (mouseX < Config.GRID_WIDTH && mouseY < Config.GRID_HEIGHT) {				
				int i = mouseY/64;
				int j = mouseX/64;
				if (map[i][j] instanceof Mountain) {
					Mountain mountain = (Mountain)map[i][j];
					click = mountain.getOccupied();
				}
			} else		
				if (mouseInRect(mouseX, mouseY, Config.SHOP_X, Config.SHOP_Y, Config.SHOP_W, Config.SHOP_W)) {
					for (Tower tower : shop) {
						if (mouseInRect(mouseX, mouseY, (int)tower.getPosX(), (int)tower.getPosY(), 64, 64)) {					
							if (player.getCash() - tower.getPrice() >= 0) 
								click = tower;
								break;							
					}
				}
			}
		}
		
	public void useClick(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();			
		if (mouseX < Config.GRID_WIDTH && mouseY < Config.GRID_HEIGHT) {
			int i = mouseY/64;
			int j = mouseX/64;				
		if (map[i][j] instanceof Mountain) {
			Mountain mountain = (Mountain)map[i][j];
			if (mountain.isOccupied())
				click = mountain.getOccupied();				
				else player.buyTower((Tower)click, mountain, towers);
			}
		} else if (mouseInRect(mouseX, mouseY, Config.UPGRADE_BUTTON_X, Config.UPGRADE_BUTTON_Y, Config.UPGRADE_WIDTH, Config.UPGRADE_HEIGHT)) {
                player.playerUpgradeTower((Tower)click);
		} else if (mouseInRect(mouseX, mouseY, Config.SELL_BUTTON_X, Config.SELL_BUTTON_Y, Config.SELL_WIDTH, Config.SELL_HEIGHT)) {
                int j = (int) click.getPosX() / 64;
                int i = (int) click.getPosY() / 64;
                if (i < 10 && j < 17)
                player.playerRemoveTower((Tower)click, (Mountain)map[i][j], towers);
		}		
        click = null;
	}
	
	public void checkMainButtons(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		//Next level button
		if (mouseInRect(mouseX, mouseY, Config.NEXT_LEVEL_BUTTON_X, Config.NEXT_LEVEL_BUTTON_Y, Config.NEXT_LEVEL_BUTTON_WIDTH, Config.NEXT_LEVEL_BUTTON_HEIGHT) && player.getLives() > 0) {
			System.out.println("Next level");
			nextLevel();
		} 
		//Main menu button
		if (mouseInRect(mouseX, mouseY, Config.MAIN_MENU_BUTTON_X, Config.MAIN_MENU_BUTTON_Y, Config.MAIN_MENU_BUTTON_WIDTH, Config.MAIN_MENU_BUTTON_HEIGHT)) {
				System.out.println("Back to Main Menu");
				playing = false;
		}
	}
	
	public void nextLevel() {
	
		if (enemies.isEmpty() && player.isAlive()) {
			player.nextLevel();
			spawner.setApproaching(Config.getLevel(player.getLevel()));
		}
	}
	
	public void checkEndGame(Graphics g) {
		if (player.getLives() <= 0) {
			g.setColor(new Color(200, 200, 200));
			g.fillRect(Config.GAME_OVER_X, Config.GAME_OVER_Y, Config.GAME_OVER_WIDTH, Config.GAME_OVER_HEIGHT);
			g.setColor(Color.BLACK);
			g.drawString("Game Over", Config.GAME_OVER_X + 20, Config.GAME_OVER_Y + 50);
		
		}
	}

	public void enitiesInteract(long time) {
		
		if (spawner.stillHasEnemies()) {
			spawner.spawnEnemy(time, enemies);
		}
		
		for (Tower t : towers) {
			t.shootEnemy(enemies, time);
		}
		for (int i = 0; i < enemies.size(); ++i) {
			while (!enemies.get(i).isAlive()) {
				if (enemies.get(i).getKt() == 0) {
					  player.receiveReward(enemies.get(i));
				} 
				enemies.remove(i);
				if (i >= enemies.size()) return;
			}
			enemies.get(i).move(time);
		}
	}
	


	private boolean mouseInRect(int mouseX, int mouseY, int x, int y, int w, int h) {
		return (x < mouseX && mouseX < x+w) && (y < mouseY && mouseY < y+h);
	}
	
	private void loadMap(int mapID) {
	
		if (mapID == 0){
			playing = false;
		}
		else {
			Config.MAP = Config.get_TEST_MAP(mapID);		
			int[][] a = Config.MAP;
			for (int i = 0; i < 10; ++i) {
				for (int j = 0; j < 17; ++j) {	
					if (a[i][j] == -2) {
						map[i][j] = new Target(j*64, i*64, 7, player);
						target = (Target)map[i][j];
						Enemy.ENEMY_TARGET = target;
					}
				
					else if (a[i][j] == -1) {
						map[i][j] = new Spawner(j*64, i*64, 1);
						spawner = (Spawner)map[i][j];
					}
				
					else if (a[i][j] == 0) {
						map[i][j] = new Mountain(j*64, i*64, 0);
					}
					else if (1 <= a[i][j] && a[i][j] <= Terrain.TERRAIN_IMG.length) {
						map[i][j] = new Road(j*64, i*64, a[i][j]);
					}
				}
			}
		}
	}
	public boolean isPlaying() {return playing;}
	public void setPlaying(boolean playing) {this.playing = playing;}
}
	
		