package TowerDefense.entities.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import TowerDefense.Config;

public class SmallEnemy extends Enemy{
	
	public SmallEnemy(float posX, float posY,int level) {
		super(posX, posY,level);
		this.healthPoint = (int)(SMALL_ENEMY_HP* (1+0.2*level));
		this.vX = (int)(SMALLENEMY_SPEED*(1+0.1*level));
		this.vY = 0;
	}
	
	@Override
	public int getReward(){
		return SMALL_ENEMY_REWARD;
	}
	
	@Override
	public void draw(Graphics g) {
		//draw enemy's image
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform asBefore = g2d.getTransform();
		g2d.rotate(angle,(int)posX+32, (int)posY+32);
		g.drawImage(SMALL_ENEMY_IMG, (int)posX, (int)posY, null);
		g2d.setTransform(asBefore);
		//draw HP bar
		float percentHP = (float)(healthPoint/(SMALL_ENEMY_HP* (1+0.2*level)));
		g.drawRect((int)posX+16, (int)posY+10, 32, 6);
		g.setColor(Color.RED);
		g.fillRect((int)posX+16, (int)posY+10, (int)(32*percentHP), 6);
		g.setColor(Color.BLACK);
	}
	
	@Override
	public void drawInfo(Graphics g) {
		drawAttributes(g);
		g.drawImage(SMALL_ENEMY_IMG, Config.INFO_IMAGE_X, Config.INFO_IMAGE_Y,128,128,null);
		g.drawRect(Config.INFO_IMAGE_X, Config.INFO_IMAGE_Y, 128, 128);
		g.drawString("Small Enemy ", Config.ATTRIBUTES_X, Config.NAME_Y);
	}	
}
