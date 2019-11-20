  package TowerDefense;

import java.awt.*;
import javax.swing.*;

public class RotateImage extends JPanel {
	Image image;
	
	public RotateImage() {
		super();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d=(Graphics2D)g;
		g2d.translate(170, 0);
		g2d.rotate(Math.PI/2);
		
	}
}
