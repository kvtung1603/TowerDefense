package TowerDefense.entities.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import TowerDefense.Config;

public class TankerEnemy extends Enemy {
	
	public TankerEnemy(float posX, float posY,int level) {
		super(posX, posY,level);
		this.healthPoint = (int)(TANKER_ENEMY_HP* (1+0.3*level));
		this.vX = TANKERENEMY_SPEED;
		this.vY = 0;
	}

	@Override
	public int getReward(){
		return TANKER_ENEMY_REWARD;
	} 
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform asBefore = g2d.getTransform();
		g2d.rotate(angle,(int)posX+32, (int)posY+32);
		g.drawImage(TANKER_BODY_ENEMY_IMG, (int)posX, (int)posY, null);
		g.drawImage(TANKER_HEAD_ENEMY_IMG, (int)posX, (int)posY, null);
		g2d.setTransform(asBefore);
		//draw HP bar
		float percentHP = (float)(healthPoint/(TANKER_ENEMY_HP* (1+0.3*level)));
		g.drawRect((int)posX+8, (int)posY+5, 48, 6);
		g.setColor(Color.RED);
		g.fillRect((int)posX+8, (int)posY+5, (int)(48*percentHP), 6);
		g.setColor(Color.BLACK);
	}
	
	@Override
	public void drawInfo(Graphics g) {
		drawAttributes(g);
		g.drawImage(TANKER_BODY_ENEMY_IMG, Config.INFO_IMAGE_X, Config.INFO_IMAGE_Y,128,128,null);
		g.drawImage(TANKER_HEAD_ENEMY_IMG, Config.INFO_IMAGE_X, Config.INFO_IMAGE_Y,128,128,null);
		g.drawRect(Config.INFO_IMAGE_X, Config.INFO_IMAGE_Y, 128, 128);
		g.drawString("Tanker Enemy ", Config.ATTRIBUTES_X, Config.NAME_Y);		
	}
}
