package TowerDefense.entities.terrains;

import java.util.List;
import java.util.Queue;
import TowerDefense.entities.enemies.Enemy;

public class Spawner extends Road{

	static float spawnRate = 1; //times/s

	private Queue<Enemy> approaching;
	private long lastSpawningTime;

	public Spawner(float posX, float posY, int imgId) {
		super(posX, posY, imgId);
	}
	
	public boolean canSpawn() {
		return lastSpawningTime > 1000/spawnRate;
	}
		
	public void spawnEnemy(long time, List<Enemy> enemiesOnField) {
		lastSpawningTime += time;
		if (canSpawn()) {
			lastSpawningTime = 0;
			Enemy newEnemy = approaching.poll();
			newEnemy.setPosX(posX);
			newEnemy.setPosY(posY);
			enemiesOnField.add(newEnemy);
		}
	}
	
	public Queue<Enemy> getApproaching() {
		return approaching;
	}
	
	public void setApproaching(Queue<Enemy> approaching) {
		this.approaching = approaching;
	}
		
	public boolean stillHasEnemies() {
		return approaching != null && !approaching.isEmpty();
	}
}
