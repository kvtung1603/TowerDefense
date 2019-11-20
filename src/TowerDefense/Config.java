package TowerDefense;

import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import TowerDefense.entities.Bullet;
import TowerDefense.entities.enemies.Boss;
import TowerDefense.entities.enemies.DarkEnemy;
import TowerDefense.entities.enemies.Enemy;
import TowerDefense.entities.enemies.NormalEnemy;
import TowerDefense.entities.enemies.SmallEnemy;
import TowerDefense.entities.enemies.TankerEnemy;
import TowerDefense.entities.terrains.Terrain;
import TowerDefense.entities.towers.MachineGun;
import TowerDefense.entities.towers.MiniGun;
import TowerDefense.entities.towers.SniperTower;
import TowerDefense.entities.towers.Tower;


public final class Config {
	
		public static final int MAX_FPS = 60;
		public static final int SCREEN_WIDTH = 1366;
		public static final int SCREEN_HEIGHT = 768;
		//FONT
		public static final Font gameFont = new Font("Dialog", Font.PLAIN, 40);
		public static final Font enityFont = new Font(Font.MONOSPACED, Font.PLAIN, 24);
		//GRID
	    public static final int GRID_X = 0 ;
	    public static final int GRID_Y = 0 ;
	    public static final int GRID_HEIGHT = 640 ;
	    public static final int GRID_WIDTH = 1088 ;
	    //INFO
	    public static final int INFO_X = 0 ;
	    public static final int INFO_Y = 640 ;
	    public static final int INFO_HEIGHT = 128 ;
	    public static final int INFO_WIDTH = 1088 ;
	    //LEVEL
	    public static final int LEVEL_X = 20 ;
	    public static final int LEVEL_Y = 700 ;
	    //LIVES
	    public static final int LIVES_X = 380 ;
	    public static final int LIVES_Y = 700 ;
	    //CASH
	    public static final int CASH_X = 740 ;
	    public static final int CASH_Y = 700 ;
	    //INFO OF ENTITY
	    public static final int INFO_ENTITY_X = 1088 ;
	    public static final int INFO_ENTITY_Y = 180 ;
	    public static final int INFO_ENTITY_WIDTH = 278 ;
	    public static final int INFO_ENTITY_HEIGHT = 390 ;
	    public static final int INFO_IMAGE_X = 1160 ;
	    public static final int INFO_IMAGE_Y = 196 ;
	    public static final int ATTRIBUTES_X = 1108 ;
	    public static final int NAME_Y = 350 ;
	    //Tower
	    public static final int DAMAGE_Y = 380 ;
	    public static final int RANGE_Y = 410 ;
	    public static final int F_RATE_Y = 440 ;
	    public static final int PRICE_Y = 470 ;
	    public static final int PRICEUPGRADE_Y = 500;
	    //Enemy
	    public static final int SPEED_ENEMY_Y = 440 ;
	    public static final int LEVEL_ENEMY_Y = 470 ;
	    public static final int HP_ENEMY_Y = 500 ;
	    //UPGRADE / SELL BUTTON
	    public static final int SELL_BUTTON_X = 1108 ;
	    public static final int SELL_BUTTON_Y = 520 ;
	    public static final int SELL_WIDTH = 80 ;
	    public static final int SELL_HEIGHT = 40 ;
	    public static final int UPGRADE_BUTTON_X = 1208 ;
	    public static final int UPGRADE_BUTTON_Y = 520 ;
	    public static final int UPGRADE_WIDTH = 120 ;
	    public static final int UPGRADE_HEIGHT = 40 ;
	    //NEXT LEVEL BUTTON
	    public static final int NEXT_LEVEL_BUTTON_X = 1114 ;
	    public static final int NEXT_LEVEL_BUTTON_Y = 608 ;
	    public static final int NEXT_LEVEL_BUTTON_WIDTH = 226 ;
	    public static final int NEXT_LEVEL_BUTTON_HEIGHT = 60 ;
	    //MAIN MENU BUTTON
	    public static final int MAIN_MENU_BUTTON_X = 1114 ;
	    public static final int MAIN_MENU_BUTTON_Y = 688 ;
	    public static final int MAIN_MENU_BUTTON_WIDTH = 226 ; 
	    public static final int MAIN_MENU_BUTTON_HEIGHT = 60 ;
	
	    //SHOP
	    public static final int SHOP_X = 1113;
	    public static final int SHOP_Y = 19;
	    public static final int SHOP_W = 220;
	    public static final int SHOP_H = 143;
	    
	    public static final float SHOP_MINIGUN_X = 1114;
	    public static final float SHOP_MINIGUN_Y = 50;
	    public static final float SHOP_MACHINEGUN_X = 1191;
	    public static final float SHOP_MACHINEGUN_Y = 50;
	    public static final float SHOP_ROCKET_X = 1268;
	    public static final float SHOP_ROCKET_Y = 50;	    
	    
	    // GAME OVER
	    public static final int GAME_OVER_X = 400;
	    public static final int GAME_OVER_Y = 300;
	    public static final int GAME_OVER_WIDTH = 250;
	    public static final int GAME_OVER_HEIGHT = 80;
	    
	    
	    public static List<Tower> SHOP = new ArrayList<Tower>();
	
	public static void load() {
		Toolkit toolkit = Toolkit.getDefaultToolkit(); 
		//load terrain images
			for (int i = 0; i < Terrain.TERRAIN_IMG.length; ++i) { 
				Terrain.TERRAIN_IMG[i] = toolkit.getImage("data\\img\\terrain\\"+i+".png");
			}			
		// load menu
			Menu.BACK_GROUND_IMG = toolkit.getImage("data\\img\\tower\\BACK_GROUND_IMG.png");
			Menu.PLAY_BUTTON_IMG = toolkit.getImage("data\\img\\tower\\PLAY_BUTTON.png");
		//load tower images
			Tower.TOWER_BASE_IMG = toolkit.getImage("data\\img\\tower\\TowerBase.png");
			Tower.MINIGUN1_IMG = toolkit.getImage("data\\img\\tower\\MiniGun1.png");
			Tower.MINIGUN2_IMG = toolkit.getImage("data\\img\\tower\\MiniGun2.png");
			Tower.MINIGUN_EFFECT_IMG = toolkit.getImage("data\\img\\tower\\Effect1.png");
			Tower.MACHINEGUN1_IMG = toolkit.getImage("data\\img\\tower\\MachineGun1.png");
			Tower.MACHINEGUN2_IMG = toolkit.getImage("data\\img\\tower\\MachineGun2.png");
			Tower.MACHINEGUN_EFFECT_IMG = toolkit.getImage("data\\img\\tower\\Effect2.png");
			Tower.SNIPERTOWER1_IMG = toolkit.getImage("data\\img\\tower\\SniperTower1.png");
			Tower.SNIPERTOWER2_IMG = toolkit.getImage("data\\img\\tower\\SniperTower2.png");
			Tower.Rocket_IMG = toolkit.getImage("data\\img\\tower\\MLaunched.png");
		//load bullet images
			Bullet.MINIGUN_BULLET = toolkit.getImage("data\\img\\bullet\\Bullet1.png");
			Bullet.MACHINEGUN_BULLET = toolkit.getImage("data\\img\\bullet\\Bullet2.png");
			Bullet.ROCKET1_BULLET = toolkit.getImage("data\\img\\bullet\\Rocket1.png");
			Bullet.ROCKET2_BULLET = toolkit.getImage("data\\img\\bullet\\Rocket2.png");
			Bullet.MISSILE_EFFECT = toolkit.getImage("data\\img\\bullet\\MissileEffect.png");
			Bullet.EXPLOSION_EFFECT = toolkit.getImage("data\\img\\bullet\\Explosion.png");
		//load enemy images
			Enemy.NORMAL_ENEMY_IMG = toolkit.getImage("data\\img\\enemy\\NormalEnemy.png");
			Enemy.SMALL_ENEMY_IMG = toolkit.getImage("data\\img\\enemy\\SmallEnemy.png");
			Enemy.TANKER_BODY_ENEMY_IMG = toolkit.getImage("data\\img\\enemy\\TankerBody.png");
			Enemy.TANKER_HEAD_ENEMY_IMG = toolkit.getImage("data\\img\\enemy\\TankerHead.png");
			Enemy.BOSS_ENEMY_IMG = toolkit.getImage("data\\img\\enemy\\Boss.png");
			Enemy.DARK_ENEMY_IMG = toolkit.getImage("data\\img\\enemy\\DarkEnemy.png");
		//load shop
			SHOP.add(new MiniGun(SHOP_MINIGUN_X, SHOP_MINIGUN_Y, 1));		
			SHOP.add(new MachineGun(SHOP_MACHINEGUN_X, SHOP_MACHINEGUN_Y, 1));		
			SHOP.add(new SniperTower(SHOP_ROCKET_X, SHOP_ROCKET_Y, 1));	
	}
	
	public static int[][] get_TEST_MAP(int id) {		
		int[][] MAP_GAME= {
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{ 0, 0, 0, 0, 3, 1, 1, 1, 1, 1, 1, 4, 0, 0, 0, 0,-0},
				{ 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0},
				{ 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0},
				{ 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0},
				{ 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0},
				{ 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 6, 1, 1, 1, 1,-2},
				{-1, 1, 1, 1, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				};			
		return MAP_GAME;
	}
	public static int[][] MAP;
		
	public static Queue<Enemy> getLevel(int level) {
		Queue<Enemy> wave = new LinkedList<Enemy>();
		switch (level) {
			case 1:
				wave.add(new DarkEnemy(0,0,2));
				wave.add(new NormalEnemy(0,0,2));
				wave.add(new NormalEnemy(0,0,2));
				return wave;
			case 2:
				for (int i = 0; i < 5; i++) 
					wave.add(new NormalEnemy(0,0,2));
				return wave;
			case 3:
				wave.add(new TankerEnemy(0,0,2));
				for (int i = 0; i < 9; i++)
					wave.add(new NormalEnemy(0,0,3));
				for (int i = 0; i < 9; i++)
					wave.add(new SmallEnemy(0,0,2));
				return wave;				
			case 4:
				wave.add(new TankerEnemy(0,0,2));
				for (int i = 0; i < 5; i++)
					wave.add(new NormalEnemy(0,0,4));
				wave.add(new TankerEnemy(0,0,3));
				for (int i = 0; i < 10; i++)
					wave.add(new SmallEnemy(0,0,3));
				for (int i = 0; i < 10; i++)
					wave.add(new NormalEnemy(0,0,3));			
				return wave;
			case 5:
				wave.add(new TankerEnemy(0,0,2));
				for (int i = 0; i < 5; i++) 
					wave.add(new DarkEnemy(0,0,3));
				for (int i = 0; i < 5; i++)
					wave.add(new NormalEnemy(0,0,4));
				wave.add(new TankerEnemy(0,0,3));
				for (int i = 0; i < 10; i++)
					wave.add(new SmallEnemy(0,0,3));
				for (int i = 0; i < 5; i++) 
					wave.add(new DarkEnemy(0,0,1));
				for (int i = 0; i < 10; i++)
					wave.add(new NormalEnemy(0,0,3));			
				return wave;
			case 6:				
				wave.add(new TankerEnemy(0,0,3));
				for (int i = 0; i < 10; i++)
					wave.add(new NormalEnemy(0,0,4));
				wave.add(new TankerEnemy(0,0,4));
				for (int i = 0; i < 7; i++)
					wave.add(new DarkEnemy(0,0,3));
				for (int i = 0; i < 10; i++) 
					wave.add(new SmallEnemy(0,0,5));
				wave.add(new TankerEnemy(0,0,4));
				for (int i = 0; i < 5; i++)
					wave.add(new SmallEnemy(0,0,4));				
				return wave;
			case 7:
				wave.add(new TankerEnemy(0,0,5));
				for (int i = 0; i < 10; i++)
					wave.add(new NormalEnemy(0,0,5));
				wave.add(new TankerEnemy(0,0,4));
				wave.add(new Boss(0,0,4));				
				for (int i = 0; i < 7; i++)
					wave.add(new DarkEnemy(0,0,3));
				for (int i = 0; i < 10; i++) 
					wave.add(new SmallEnemy(0,0,5));
				wave.add(new TankerEnemy(0,0,4));
				for (int i = 0; i < 7; i++)
					wave.add(new DarkEnemy(0,0,3));
				for (int i = 0; i < 5; i++)
					wave.add(new SmallEnemy(0,0,4));				
				return wave;
				
			default:
				System.out.println("end level");
				break;
			}
			return null;
		}
}
