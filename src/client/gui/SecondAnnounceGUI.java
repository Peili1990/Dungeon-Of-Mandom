package client.gui;


import java.awt.*;

import javax.swing.*;

import gamedata.*;

public class SecondAnnounceGUI extends Frame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JLabel monster_inf;
	public JLabel monster_img;
	public JButton set;
	public JButton remove;
	
	public SecondAnnounceGUI(int monster_atk){
		JPanel second=new JPanel();
		setLayout(null);
		second.setLayout(new BorderLayout(50,15));
		second.add("North", monster_inf = new JLabel("你看见的怪物是 " + monster_atk
				+ Monster.findMonster(monster_atk),JLabel.CENTER));
		second.add("Center",set=new JButton("我还能打十个！"));
		second.add("South",remove=new JButton("我有特殊的攻略技巧..."));
		second.setBounds(50, 320, 200, 100);
		second.setVisible(true);
		ImageIcon image = new ImageIcon(this.getClass().
				getResource("/resource/Monster/"+Monster.findMonster(monster_atk)+".jpg"));
		image.setImage((image.getImage().getScaledInstance(180,250,Image.SCALE_DEFAULT))); 
		add(monster_img=new JLabel(image));
		monster_img.setBounds(60,50,180,250);
		add(second);
		setSize(300,450);
		setResizable(false);
	}
	
	public void setLocate(ClientGUI gui){

        int width=gui.getWidth();
        
        Point pt=gui.getLocationOnScreen();

        int x=pt.x+width;

        int y=pt.y;

        setLocation(x,y);
	}
	
	
	 public static void main(String args[]){
		new SecondAnnounceGUI(2);
	}
    
}



