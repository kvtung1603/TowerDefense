package TowerDefense.entities.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import TowerDefense.Config;

public class NormalEnemy extends Enemy {
	
	public NormalEnemy(float posX, float posY, int level) {
		super(posX, posY, level);
		this.healthPoint = (int)(NORMAL_ENEMY_HP*(1+0.3*level));
		this.vX = NORMALENEMY_SPEED;
		this.vY = 0;
	}
	
	@Override
	public int getReward(){
		return NORMAL_ENEMY_REWARD;
	}

	@Override
	public void draw(Graphics g) {
		//draw enemy's image
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform asBefore = g2d.getTransform();
		g2d.rotate(angle,(int)posX+32, (int)posY+32);
		g.drawImage(NORMAL_ENEMY_IMG, (int)posX, (int)posY, null);
		g2d.setTransform(asBefore);
		//draw HP bar
		float percentHP = (float)(healthPoint/(NORMAL_ENEMY_HP*(1+0.3*level)));
		g.drawRect((int)posX+16, (int)posY+10, 32, 6);
		g.setColor(Color.RED);
		g.fillRect((int)posX+16, (int)posY+10, (int)(32*percentHP), 6);
		g.setColor(Color.BLACK);
	}
	
	@Override
	public void drawInfo(Graphics g) {
		drawAttributes(g);
		g.drawImage(NORMAL_ENEMY_IMG, Config.INFO_IMAGE_X, Config.INFO_IMAGE_Y,128,128,null);
		g.drawRect(Config.INFO_IMAGE_X, Config.INFO_IMAGE_Y, 128, 128);
		g.drawString("Normal Enemy ", Config.ATTRIBUTES_X, Config.NAME_Y);
	}	
}
