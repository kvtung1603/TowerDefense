package TowerDefense.entities.towers;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import TowerDefense.Config;
import TowerDefense.entities.AbstractEntity;
import TowerDefense.entities.Bullet;
import TowerDefense.entities.GameTile;
import TowerDefense.entities.enemies.Enemy;

public abstract class Tower extends AbstractEntity implements GameTile{
	
	public static Image TOWER_BASE_IMG;
	public static Image MINIGUN1_IMG;
	public static Image MINIGUN2_IMG;
	public static Image MINIGUN_EFFECT_IMG;
	public static Image MACHINEGUN1_IMG;
	public static Image MACHINEGUN2_IMG;
	public static Image MACHINEGUN_EFFECT_IMG;
	public static Image SNIPERTOWER1_IMG;
	public static Image SNIPERTOWER2_IMG;
	public static Image Rocket_IMG;
	
	//MiniGun
	static float MINIGUN1_RANGE = 180; 
	static float MINIGUN1_FIRE_RATE = 1.5f; 
	static int MINIGUN1_DAMAGE = 50;
	static int MINIGUN1_PRICE = 200;
		
	static int MINIGUN2_RANGE = 200;	
	static float MINIGUN2_FIRE_RATE = 2.5f;
	static int MINIGUN2_DAMAGE = 75;
	static int MINIGUN2_PRICE = 300;
		
	//MachineGun
	static float MACHINEGUN1_RANGE = 120; 
	static float MACHINEGUN1_FIRE_RATE = 3.5f; 
	static int MACHINEGUN1_DAMAGE = 35;
	static int MACHINEGUN1_PRICE = 250;
		
	static int MACHINEGUN2_RANGE = 150;
	static float MACHINEGUN2_FIRE_RATE = 5f; 
	static int MACHINEGUN2_DAMAGE = 50;
	static int MACHINEGUN2_PRICE = 350;
		
		
	//Sniper Tower
	static float Rocket1_RANGE = 250; 
	static float Rocket1_FIRE_RATE = 0.8f; 
	static int Rocket1_DAMAGE = 80;		
	static int  Rocket1_PRICE = 250;
		
	static float Rocket2_RANGE = 300;
	static float Rocket2_FIRE_RATE = 1;
	static int  Rocket2_DAMAGE = 110;
	static int  Rocket2_PRICE = 400;
	
	public static final double DEPRECIATION = 0.75;

	protected Bullet bullet;
	protected float range;			
	protected float fireRate;		
	protected int damage;
	protected int price;
	protected int type;
	protected long lastAttackTime;	
	protected double AimAngle;		
	protected boolean effectOn;
	protected int priceUpgrade;

	public Tower(float posX, float posY, int type) {
		this.posX = posX;
		this.posY = posY;
		this.type = type;
		lastAttackTime = 0;
	}	
	
	public boolean canAttack () {
		return (lastAttackTime >= 1000/fireRate);
	}
	
	public Enemy chooseTarget(List<Enemy> enemies) {
		if (enemies.isEmpty()) 
			return null;
		Enemy target = null;
		for (Enemy e : enemies) {
			if (this.getDistance(e) < range) {
				if (target == null)
					target = e;
				else if (e.getHP() < target.getHP())
					target = e;
			}
		}
		return target;
	}
	
	public void shootEnemy(List<Enemy> enemies, long time) {
		Enemy target = chooseTarget(enemies) ;
		lastAttackTime += time ;
		if (target != null) {			
			AimAngle = Math.atan2(posY-target.getPosY(), posX-target.getPosX()) - Math.PI/2;
			if (canAttack()) {
				lastAttackTime = 0 ;
				bullet.setPosX(posX);
				bullet.setPosY(posY);
				bullet.setTarget(target);
				bullet.setAimAngle(AimAngle);
				bullet.setVisible(true);
			}
		}
		moveBullet(time);	}

	public void moveBullet(long time) {
		if (bullet.isVisible()) {
			if (this.getDistance(bullet) > this.getDistance(bullet.getTarget())) {
				bullet.setVisible(false);
				bullet.setHitEffectOn(true);
				bullet.setLastHitTime(0);
				bullet.getTarget().setHP(bullet.getTarget().getHP() - damage);
			}
			else 
				bullet.move(time);
		}
		//if bullet has just been fired, turn on gun effect
		effectOn = bullet.isVisible() && this.getDistance(bullet) < 64;
		bullet.setLastHitTime(bullet.getLastHitTime() + time);
	}
	
	public void drawAttributes(Graphics g) {
		g.setFont(Config.enityFont);
		g.drawString("Damage: "+ damage, Config.ATTRIBUTES_X, Config.DAMAGE_Y);
		g.drawString("Range: "+ range, Config.ATTRIBUTES_X, Config.RANGE_Y);
		g.drawString("Fire rate: "+ fireRate, Config.ATTRIBUTES_X, Config.F_RATE_Y);
		g.drawString("Price: $" + price, Config.ATTRIBUTES_X, Config.PRICE_Y);
		g.drawString("Upgrade: $" + priceUpgrade, Config.ATTRIBUTES_X, Config.PRICEUPGRADE_Y);
		//draw upgrade & sell buttons for tower in grid
		if (posX < Config.GRID_WIDTH && posY < Config.GRID_HEIGHT) {
			g.drawRect(Config.SELL_BUTTON_X, Config.SELL_BUTTON_Y, 80, 40);
			g.drawString("Sell", Config.SELL_BUTTON_X+10, Config.SELL_BUTTON_Y+30);
			if (type == 1) {
				g.drawRect(Config.UPGRADE_BUTTON_X, Config.UPGRADE_BUTTON_Y, 120, 40);
				g.drawString("Upgrade", Config.UPGRADE_BUTTON_X+10, Config.UPGRADE_BUTTON_Y+30);
			}
		}
	}
	
	//abstract methods
	public abstract void upgrade() ;
    public abstract int getUpgradeCost() ;
	public abstract void draw(Graphics g);
	public abstract void drawInfo(Graphics g);
	public abstract void drawAttachedToMouse(Graphics g, int mouseX, int mouseY);
	public abstract Tower clone();

    //getters & setters
	public float getRange() {return range;}
	public void setRange(float range) {this.range = range;}

	public float getFireRate() {return fireRate;}
	public void setFireRate(float fireRate) {this.fireRate = fireRate;}

	public int getDamage() {return damage;}
	public void setDamage(int damage) {this.damage = damage;}

	public int getPrice() {return price;}
	public void setPrice(int price) {this.price = price;}

	public double getAimAngle() {return AimAngle;}
	
	public int getType() {return type;}
	public void setType(int type) {this.type = type;}	
	
}
	