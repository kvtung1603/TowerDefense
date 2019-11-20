package TowerDefense;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Main {
	private static JFrame frame;
	private static Menu mainMenu;	
	private static GameStage stage; 

	public static void main(String[] args) {
		Config.load();	
		frame = new JFrame("Tower Defense");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container content = frame.getContentPane();
		Dimension mySize = new Dimension(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        content.setPreferredSize(mySize);
        frame.pack();
        
		mainMenu = new Menu();
		frame.setContentPane(mainMenu);
		frame.setVisible(true);
		frame.setLocation(290, 100);
	
		while (mainMenu.chooseMap() == true) {

			int mapID = mainMenu.getSelectedOption();
			stage = new GameStage(mapID);
			frame.setContentPane(stage);
			frame.setVisible(true);		
			stage.play();
			frame.setContentPane(mainMenu);
			mainMenu.setSelectedOption(0);
		}
		
		frame.dispose();
	}
}
