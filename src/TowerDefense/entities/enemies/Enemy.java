package TowerDefense.entities.enemies;

import java.awt.Graphics;
import java.awt.Image;
import TowerDefense.Config;
import TowerDefense.entities.AbstractEntity;
import TowerDefense.entities.terrains.Terrain;
import TowerDefense.entities.terrains.Target;

public abstract class Enemy extends AbstractEntity{
	
	public static Image SMALL_ENEMY_IMG;
	//Normal Enemy
	public static Image NORMAL_ENEMY_IMG;
	//Tanker Enemy
	public static Image TANKER_HEAD_ENEMY_IMG;
	public static Image TANKER_BODY_ENEMY_IMG ;
	//Boss Enemy
	public static Image BOSS_ENEMY_IMG;
	public static Image DARK_ENEMY_IMG;
	
	///Target
	public static Target ENEMY_TARGET;

	//Speed
	static float SMALLENEMY_SPEED = 120;	
	static float NORMALENEMY_SPEED = 80;
	static float TANKERENEMY_SPEED = 60;
	static float BOSSENEMY_SPEED = 75;
	static float DARK_ENEMY_SPEED = 90;
	//HP
	static int NORMAL_ENEMY_HP = 300;
	static int SMALL_ENEMY_HP = 120;
	static int TANKER_ENEMY_HP = 1600;
	static int BOSS_ENEMY_HP = 2500;
	static int DARK_ENEMY_HP = 700;
	
	//Enemy Reward
	static int NORMAL_ENEMY_REWARD = 20;
	static int SMALL_ENEMY_REWARD = 15;
	static int TANKER_ENEMY_REWARD = 75;
	static int BOSS_ENEMY_REWARD = 100;			
	static int DARK_ENEMY_REWARD = 30;
	
	protected float vX;
	protected float vY;
	protected int healthPoint;
	protected double angle;
	protected int level;
	protected int reward;
	protected int kt = 0;
	
	public Enemy(float posX, float posY, int level) {
		this.posX = posX;
		this.posY = posY;
		this.level = level;
	}
	
	public void move(long time) {
		int i = (int)(posY/64);
		int j = (int)(posX/64);
		switch (Config.MAP[i][j]) {
			case 3: //UP_RIGHT
				if (posY <= i*64 + 5 && vX == 0) {
						vX = -vY;
						vY = 0;
						angle = 0;
				}
				break;
			case 4: //RIGHT_DOWN
				if (vY == 0) {
						vY = vX;
						vX = 0;
						angle = Math.PI/2;
				}
				break;
			case 5: //RIGHT_UP
				if (vY == 0) {
					vY = -vX;
					vX = 0;
					angle = -Math.PI/2;
				}
				break;
			case 6: //DOWN_RIGHT
				if (vX == 0) {
					vX = vY;
					vY = 0;
					angle = 0;				
				}
				break;
			case -2: 
				setHP(0);
				setKt(1);				
				ENEMY_TARGET.getPlayer().setLives(ENEMY_TARGET.getPlayer().getLives()-1);
				break;
			default: break;
		}
		posX += vX*time/1200;
		posY += vY*time/1200;
	}
		
	public boolean isAlive() {
		return healthPoint > 0 ;
	}
		
	public void drawAttributes(Graphics g){
		g.setFont(Config.enityFont);
		g.drawString("Speed: " + Math.abs(vX + vY), Config.ATTRIBUTES_X, Config.SPEED_ENEMY_Y - 60);
		g.drawString("Level: " + this.level, Config.ATTRIBUTES_X, Config.LEVEL_ENEMY_Y - 60);
		g.drawString("HP: " + this.healthPoint, Config.ATTRIBUTES_X, Config.HP_ENEMY_Y - 60);
		g.drawImage(Terrain.TERRAIN_IMG[0], Config.INFO_IMAGE_X, Config.INFO_IMAGE_Y,128,128,null);
	}
		
	@Override
	public abstract void draw(Graphics g);
	public abstract void drawInfo(Graphics g);
	public abstract int getReward();

		
	
	//getters & setters
	public float getvX() {return vX;}
	public void setvX(float vX) {this.vX = vX;}

	public float getvY() {return vY;}
	public void setvY(float vY) {this.vY = vY;}

	public int getHP() {return healthPoint;}
	public void setHP(int healthPoint) {this.healthPoint = healthPoint;}
	public void setKt(int kt) {this.kt = kt;}
	public int getKt() {return kt;}
	
}
