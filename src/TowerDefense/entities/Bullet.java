package TowerDefense.entities;

import java.awt.Graphics;
import java.awt.Image;
import TowerDefense.entities.enemies.Enemy;

public class Bullet extends AbstractEntity {

	public static Image MINIGUN_BULLET;
	public static Image MACHINEGUN_BULLET;
	public static Image ROCKET1_BULLET;
	public static Image ROCKET2_BULLET;
	public static Image MISSILE_EFFECT;
	public static Image EXPLOSION_EFFECT;
	public static final float MINI_BULLET_VELOCITY = 700 ; 
	public static final float MACHINE_BULLET_VELOCITY = 900 ; 
	public static final float MISSILE_VELOCITY = 1000 ; 
	public static final long MISSILE_EXPLOSION_TIME = 200 ; 
	
	private Image img;
	private float velocity;		
	private double AimAngle;	
	private long lastHitTime;	
	private boolean visible;
	private boolean hitEffectOn;
	private Enemy target;
	
	//Constructor
	public Bullet(Image img, float posX, float posY, float velocity) {
		this.img = img;
		this.posX = posX ;
		this.posY = posY ;
		this.velocity = velocity;
		this.visible = false;
		this.hitEffectOn = false;
		this.lastHitTime = 1000;
	}
	
	public void move(long time) {
		float vX = velocity * (float)Math.sin(Math.PI - AimAngle) + target.getvX();
		float vY = velocity * (float)Math.cos(Math.PI - AimAngle) + target.getvY();
		posX += vX*time/1000 ;
		posY += vY*time/1000 ;
	}
	
	public void setHitEffectOn(long time) {
		lastHitTime += time;
		hitEffectOn = true;
		if (lastHitTime > MISSILE_EXPLOSION_TIME) {
			hitEffectOn = false;
		}
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, (int)posX, (int)posY, null);
	}
	public void drawInfo(Graphics g) {} 	

	//getters & setters
	public boolean isVisible() {return visible;}
	public void setVisible(boolean visible) {this.visible = visible;}
	
	public double getAimAngle() {return AimAngle;}
	public void setAimAngle(double aimAngle) {this.AimAngle = aimAngle;}
	
	public Enemy getTarget() {return target;}
	public void setTarget(Enemy target) {this.target = target;}
	
	public boolean isHitEffectOn() {return lastHitTime < MISSILE_EXPLOSION_TIME;}
	public void setHitEffectOn(boolean hitEffectOn) {this.hitEffectOn = hitEffectOn;}
	
	public long getLastHitTime() {return lastHitTime;}
	public void setLastHitTime(long lastHitTime) {this.lastHitTime = lastHitTime;}
}
	