package TowerDefense;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import TowerDefense.entities.enemies.Enemy;
import TowerDefense.entities.terrains.Terrain;
import TowerDefense.entities.towers.Tower;

public class GameStage extends JPanel{
	
	private GameField field;
	
	public GameStage(int mapID) {
		this.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		this.enableEvents(MouseEvent.MOUSE_EVENT_MASK);
		this.enableEvents(MouseEvent.MOUSE_MOTION_EVENT_MASK); 
		field = new GameField(mapID);		
	}
	
	public void play() {
		long time;
		long now = System.currentTimeMillis();
			
		while (field.isPlaying()) {				
				time = System.currentTimeMillis() - now;
				now = System.currentTimeMillis();			
				field.enitiesInteract(time);			
				repaint();
			try {
				Thread.sleep(1000/Config.MAX_FPS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
		
	
	@Override
	protected void processMouseEvent(MouseEvent e) {
		if (e.getID() == MouseEvent.MOUSE_PRESSED) {
		
			field.checkMainButtons(e);
			if (field.click == null) 
				field.selectEntityIfClick(e);
			else 
				field.useClick(e);
		}
    }
	
	@Override
	protected void processMouseMotionEvent(MouseEvent e) {
		if (e.getID() == MouseEvent.MOUSE_MOVED) {
			field.selectEntityIfHover(e);
		}
	}	
	
	@Override
	public void paint(Graphics g) {
		if (field.player.getLives() > 0) {
			g.clearRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
			g.setColor(new Color(200, 200, 200));
			g.fillRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
			g.setColor(Color.BLACK);
			drawShop(g);
			drawGameField(g);
			drawFrame(g);
		} else if (field.player.getLives() <= 0) {
			field.player.setLives(0);
			drawShop(g);
			drawFrame(g);
			g.setColor(new Color(200, 200, 200));
			g.fillRect(Config.GAME_OVER_X, Config.GAME_OVER_Y, Config.GAME_OVER_WIDTH, Config.GAME_OVER_HEIGHT);
			g.setColor(Color.BLACK);
			g.drawString("Game Over", Config.GAME_OVER_X + 20, Config.GAME_OVER_Y + 50);				
		}
	}

	private void drawFrame(Graphics g) {
		g.drawRect(Config.GRID_X, Config.GRID_Y, Config.GRID_WIDTH, Config.GRID_HEIGHT);
		
	    g.drawRect(Config.INFO_X, Config.INFO_Y, Config.INFO_WIDTH, Config.INFO_HEIGHT);
	    
	    g.setFont(Config.gameFont);
	
	    g.drawString("Level: " + field.player.getLevel(), Config.LEVEL_X, Config.LEVEL_Y);

	    g.drawString("Lives: " + field.player.getLives(), Config.LIVES_X, Config.LIVES_Y);
	
	    g.drawString("Cash: " + field.player.getCash(), Config.CASH_X, Config.CASH_Y);
	
	    g.drawRect(Config.INFO_ENTITY_X, Config.INFO_ENTITY_Y, Config.INFO_ENTITY_WIDTH, Config.INFO_ENTITY_HEIGHT);
	    //Next Level button
        g.drawRect(Config.NEXT_LEVEL_BUTTON_X, Config.NEXT_LEVEL_BUTTON_Y, Config.NEXT_LEVEL_BUTTON_WIDTH, Config.NEXT_LEVEL_BUTTON_HEIGHT );
        
        g.drawString("Next Level", Config.NEXT_LEVEL_BUTTON_X+15, Config.NEXT_LEVEL_BUTTON_Y+50);
        
        g.drawRect(Config.MAIN_MENU_BUTTON_X, Config.MAIN_MENU_BUTTON_Y, Config.MAIN_MENU_BUTTON_WIDTH, Config.MAIN_MENU_BUTTON_HEIGHT );
        
        g.drawString("Main Menu", Config.MAIN_MENU_BUTTON_X+10, Config.MAIN_MENU_BUTTON_Y+50);
	}
	private void drawShop(Graphics g) {
		for (Tower tower : field.shop)
			tower.draw(g);
			g.drawRect((int)Config.SHOP_MINIGUN_X, (int)Config.SHOP_MINIGUN_Y, 64, 64);			
			g.drawRect((int)Config.SHOP_MACHINEGUN_X, (int)Config.SHOP_MACHINEGUN_Y, 64, 64);			
			g.drawRect((int)Config.SHOP_ROCKET_X, (int)Config.SHOP_ROCKET_Y, 64, 64);
			
	}
	
	private void drawGameField(Graphics g) {
		for (Terrain[] i : field.map) { //for-each loop
			for (Terrain j : i) {
				j.draw(g);
			}
		}
	
		for (Tower t : field.towers) {
			t.draw(g);
		}
		
		
		for (Enemy e : field.enemies) {
			e.draw(g);
		}
	
		if (field.click != null) {
			field.click.drawInfo(g);		
			if (field.click.isInRect(Config.SHOP_X, Config.SHOP_Y, Config.SHOP_W, Config.SHOP_W)) {
				Point mouse = this.getMousePosition();
				if (mouse != null) {
					int mouseX = mouse.x;
					int mouseY = mouse.y;
					((Tower)field.click).drawAttachedToMouse(g, mouseX, mouseY);
				}
			}
		} else
		if (field.hover != null)
			field.hover.drawInfo(g);
		}
	
	

}		
		