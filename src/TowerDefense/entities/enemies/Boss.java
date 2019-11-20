package TowerDefense.entities.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import TowerDefense.Config;

public class Boss extends Enemy {
	
	protected int healing = 1;
	
	public Boss(float posX, float posY, int level) {
		super(posX, posY,level);
		this.healthPoint = (int)(BOSS_ENEMY_HP*(1+0.35*level));
		this.vX = BOSSENEMY_SPEED;
		this.vY = 0;
	}

	@Override
	public int getReward(){
		return BOSS_ENEMY_REWARD;
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform asBefore = g2d.getTransform();
		g2d.rotate(angle,(int)posX+32, (int)posY+32);
		g.drawImage(BOSS_ENEMY_IMG, (int)posX, (int)posY, null);
		g2d.setTransform(asBefore);
		//draw HP bar
		float percentHP = (float)(healthPoint/(BOSS_ENEMY_HP* (1+0.35*level)));
		g.drawRect((int)posX, (int)posY, 64, 6);
		g.setColor(Color.RED);
		g.fillRect((int)posX, (int)posY, (int)(64*percentHP), 6);
		g.setColor(Color.BLACK);		
		
		if (healthPoint <= 1000 && healing > 0) {
			healthPoint += 300;
			healing -= 1;
		}
		
	}
	
	@Override
	public void drawInfo(Graphics g) {
		drawAttributes(g);
		g.drawImage(BOSS_ENEMY_IMG, Config.INFO_IMAGE_X, Config.INFO_IMAGE_Y,128,128,null);
		g.drawRect(Config.INFO_IMAGE_X, Config.INFO_IMAGE_Y, 128, 128);
		g.drawString("BOSS Enemy ", Config.ATTRIBUTES_X, Config.NAME_Y);
	}
	
}
