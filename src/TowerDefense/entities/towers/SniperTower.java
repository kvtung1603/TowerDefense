package TowerDefense.entities.towers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import TowerDefense.Config;
import TowerDefense.entities.Bullet;

public class SniperTower extends Tower{
	
	public SniperTower(float posX, float posY, int type) {
		super(posX, posY, type);
		range = Rocket1_RANGE;
		damage = Rocket1_DAMAGE;
		price = Rocket1_PRICE;
		fireRate = Rocket1_FIRE_RATE;
		bullet = new Bullet(Bullet.ROCKET1_BULLET, posX, posY, Bullet.MISSILE_VELOCITY);		
		priceUpgrade = Rocket2_PRICE - Rocket1_PRICE;		
	}

	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;		
		g.drawImage(TOWER_BASE_IMG, (int)posX, (int)posY, null);
		AffineTransform asBefore = g2d.getTransform();
		g2d.rotate(AimAngle, posX+32, posY+32);
		if (type == 1)
			g.drawImage(SNIPERTOWER1_IMG, (int)posX, (int)posY, null);
		else if (type == 2)
			g.drawImage(SNIPERTOWER2_IMG, (int)posX, (int)posY, null);
		if (effectOn)
			g2d.drawImage(Rocket_IMG, (int)posX, (int)posY, null);
	
		if (bullet.isVisible()) {
			g2d.setTransform(asBefore);
			g2d.rotate(AimAngle, bullet.getPosX()+32, bullet.getPosY()+32);
			bullet.draw(g);
			g2d.drawImage(Bullet.MISSILE_EFFECT, (int)bullet.getPosX(),(int)bullet.getPosY()+32, null);
		}
		g2d.setTransform(asBefore);
		if (bullet.isHitEffectOn()) {
			g2d.drawImage(Bullet.EXPLOSION_EFFECT, (int)bullet.getPosX(), (int)bullet.getPosY(), 64, 64, null);
		}
	}
	@Override
	public void drawInfo(Graphics g) {
		drawAttributes(g);
		g.drawString("Sniper Tower " + type, Config.ATTRIBUTES_X, Config.NAME_Y);
		//draw range
		if (posX < Config.GRID_WIDTH && posY < Config.GRID_HEIGHT) {
			g.drawOval((int)(posX-range+32), (int)(posY-range+32), (int)range*2, (int)range*2);
		}
		//draw info image
		g.drawImage(TOWER_BASE_IMG, Config.INFO_IMAGE_X, Config.INFO_IMAGE_Y,128,128,null);
		if (type == 1)
			g.drawImage(SNIPERTOWER1_IMG, Config.INFO_IMAGE_X, Config.INFO_IMAGE_Y, 128,128,null);
		else if (type == 2)
			g.drawImage(SNIPERTOWER2_IMG, Config.INFO_IMAGE_X, Config.INFO_IMAGE_Y, 128,128,null);
			g.drawRect(Config.INFO_IMAGE_X, Config.INFO_IMAGE_Y, 128, 128);
	}
	@Override
	public void drawAttachedToMouse(Graphics g, int mouseX, int mouseY) {
		g.drawImage(TOWER_BASE_IMG, mouseX-32, mouseY-32, null);
		if (type == 1)
			g.drawImage(SNIPERTOWER1_IMG, mouseX-32, mouseY-32, null);
		else if (type == 2)
			g.drawImage(SNIPERTOWER2_IMG, mouseX-32, mouseY-32, null);
		//draw projection to grid
		if (mouseX < Config.GRID_WIDTH && mouseY < Config.GRID_HEIGHT) {
			int i = mouseY/64;
			int j = mouseX/64;
			if (Config.MAP[i][j] == 0 ) {
				g.drawOval(j*64-(int)range+32, i*64-(int)range+32, (int)range*2, (int)range*2);
				g.setColor(new Color(255, 255, 255, 150));
				g.fillRect(j*64, i*64, 64, 64);
				g.setColor(Color.BLACK);
			}
		}
	}

	@Override
	public Tower clone() {
		return new SniperTower(posX, posY, type);
	}
	
	@Override
	public void upgrade () {
        type = 2 ;
        damage = Rocket2_DAMAGE;
        price = Rocket2_PRICE;
        range = Rocket2_RANGE;
        bullet = new Bullet(Bullet.ROCKET2_BULLET, posX, posY, Bullet.MISSILE_VELOCITY);
    }
	
	@Override
    public int getUpgradeCost () {
		return Rocket2_PRICE - Rocket1_PRICE;
    }
}
