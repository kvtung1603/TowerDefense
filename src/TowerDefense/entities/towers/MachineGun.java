package TowerDefense.entities.towers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import TowerDefense.Config;
import TowerDefense.entities.Bullet;

public class MachineGun extends Tower {

	public MachineGun(float posX, float posY, int type) {
		super(posX, posY, type);
		bullet = new Bullet(Bullet.MACHINEGUN_BULLET, posX,posY, Bullet.MACHINE_BULLET_VELOCITY);		
		range = MACHINEGUN1_RANGE;
		fireRate = MACHINEGUN1_FIRE_RATE;
		damage = MACHINEGUN1_DAMAGE;
		price = MACHINEGUN1_PRICE;		
		priceUpgrade = MACHINEGUN2_PRICE - MACHINEGUN1_PRICE;
	}	

	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		//draw tower base
		g.drawImage(TOWER_BASE_IMG, (int)posX, (int)posY, null);
		//draw the rotated images by AimAngle
		AffineTransform asBefore = g2d.getTransform();
		g2d.rotate(AimAngle, posX+32, posY+32);
		if (type == 1)
			g.drawImage(MACHINEGUN1_IMG, (int)posX, (int)posY, null);
		else if (type == 2)
			g.drawImage(MACHINEGUN2_IMG, (int)posX, (int)posY, null);
		if (effectOn) 
			g2d.drawImage(MACHINEGUN_EFFECT_IMG, (int)posX, (int)posY-40, null);
		g2d.setTransform(asBefore);
		//draw bullet
		if (bullet.isVisible()) bullet.draw(g);
	}
	
	@Override
	public void drawInfo(Graphics g) {
		drawAttributes(g);
		g.drawString("Machine Gun "+type, Config.ATTRIBUTES_X, Config.NAME_Y);
		//draw range
		if (posX < Config.GRID_WIDTH && posY < Config.GRID_HEIGHT) {
		g.drawOval((int)(posX-range+32), (int)(posY-range+32), (int)range*2, (int)range*2);
		}
		//draw info image
		g.drawImage(TOWER_BASE_IMG,Config.INFO_IMAGE_X,Config.INFO_IMAGE_Y,128,128,null);
		if (type == 1)
		g.drawImage(MACHINEGUN1_IMG,Config.INFO_IMAGE_X,Config.INFO_IMAGE_Y,128,128,null);
		else if (type == 2)
		g.drawImage(MACHINEGUN2_IMG,Config.INFO_IMAGE_X,Config.INFO_IMAGE_Y,128,128,null);
		g.drawRect(Config.INFO_IMAGE_X, Config.INFO_IMAGE_Y, 128, 128);
	}
	
	@Override
	public void drawAttachedToMouse(Graphics g, int mouseX, int mouseY) {
		g.drawImage(TOWER_BASE_IMG, mouseX-32, mouseY-32, null);
		if (type == 1)
			g.drawImage(MACHINEGUN1_IMG, mouseX-32, mouseY-32, null);
		else if (type == 2)
			g.drawImage(MACHINEGUN2_IMG, mouseX-32, mouseY-32, null);
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
		return new MachineGun(posX, posY, type);
	}
	
	@Override
	public void upgrade () {
        type = 2;
        fireRate = MACHINEGUN2_FIRE_RATE;
        damage = MACHINEGUN2_DAMAGE;
        price = MACHINEGUN2_PRICE;
        range = MACHINEGUN2_RANGE;
    }
	
	@Override
    public int getUpgradeCost () {
		return MACHINEGUN2_PRICE - MACHINEGUN1_PRICE ;
	}
}
