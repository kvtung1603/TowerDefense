package TowerDefense.entities.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import TowerDefense.Config;

public class DarkEnemy extends Enemy {
	
	protected int speed_up = 1;
	
	public DarkEnemy(float posX, float posY, int level) {
		super(posX, posY,level);
		this.healthPoint = (int)(DARK_ENEMY_HP*(1+0.2*level));
		this.vX = DARK_ENEMY_SPEED;
		this.vY = 0;
	}

	@Override
	public int getReward(){
		return DARK_ENEMY_REWARD;
	}
		
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform asBefore = g2d.getTransform();
		g2d.rotate(angle,(int)posX+32, (int)posY+32);
		g.drawImage(DARK_ENEMY_IMG, (int)posX, (int)posY, null);
		g2d.setTransform(asBefore);
	//draw HP bar
		float percentHP = (float)(healthPoint/(DARK_ENEMY_HP*(1+0.2*level)));
		g.drawRect((int)posX+16, (int)posY+10, 32, 6);
		g.setColor(Color.RED);
		g.fillRect((int)posX+16, (int)posY+10, (int)(32*percentHP), 6);
		g.setColor(Color.BLACK);
		
		if (this.vX == 0 && this.angle == - Math.PI/2 && vY == -DARK_ENEMY_SPEED) {
			this.vY -= 50;
		} else if(this.vX == 0 && this.angle == Math.PI/2 && speed_up == 1  ) {
			this.vY += 90;
			speed_up = 0;
		}
	}
		
	@Override
	public void drawInfo(Graphics g) {
		drawAttributes(g);
		g.drawImage(DARK_ENEMY_IMG, Config.INFO_IMAGE_X, Config.INFO_IMAGE_Y,128,128,null);
		g.drawRect(Config.INFO_IMAGE_X, Config.INFO_IMAGE_Y, 128, 128);
		g.drawString("Dark Enemy ", Config.ATTRIBUTES_X, Config.NAME_Y);
	}		
}


