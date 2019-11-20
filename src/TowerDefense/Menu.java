	package TowerDefense;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class Menu extends JPanel {

	public static Image BACK_GROUND_IMG;
	public static Image PLAY_BUTTON_IMG;
	
	private static int NULL = 0;		
	private static int EXIT = 2;
		
	
	private int selectedOption;
	
	public Menu() {
		this.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		this.enableEvents(MouseEvent.MOUSE_EVENT_MASK);
		this.enableEvents(MouseEvent.MOUSE_MOTION_EVENT_MASK);
	}
	public boolean chooseMap() {
		while (selectedOption == NULL) {
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (selectedOption == -1){
			return false;
		}
		else
			return selectedOption != EXIT;
	}

	@Override
	protected void processMouseEvent(MouseEvent e) {
		if (e.getID() == MouseEvent.MOUSE_PRESSED) {
			int mouseX = e.getX();
			int mouseY = e.getY();
			if (500 < mouseX && mouseX < 500+300 && 600 < mouseY && mouseY < 600+100){
				selectedOption = 1;
			}
		}
    }	
	
	@Override
	public void paint(Graphics g) {		
		g.setFont(Config.gameFont);
		g.drawImage( BACK_GROUND_IMG,0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, null);			
		g.drawImage( PLAY_BUTTON_IMG,500, 600, 300, 100, null);
		// g.drawRect(500, 600, 300, 100);
	}

    //getters & setters
	public int getSelectedOption() {
		return selectedOption;
	}
	public void setSelectedOption(int selectedOption) {
		this.selectedOption = selectedOption;
	}
}
	