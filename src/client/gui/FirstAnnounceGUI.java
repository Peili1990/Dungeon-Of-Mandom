package client.gui;

import java.awt.*;

import javax.swing.*;

public class FirstAnnounceGUI extends Frame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JButton accept;
	public JButton run;
	
	
	
	public FirstAnnounceGUI(){
		JPanel first=new JPanel();
		setLayout(null);
		first.setLayout(new BorderLayout(50,0));
		first.add("North",accept=new JButton("最近的地城弱爆了！"));
		first.add("South",run=new JButton("我想起我家里有事"));
		first.setBounds(50, 100, 200, 100);
		ImageIcon image = new ImageIcon(this.getClass().getResource("/resource/reminder.png")); 
		image.setImage((image.getImage().getScaledInstance(250,39,Image.SCALE_DEFAULT))); 
		JLabel reminder=new JLabel(image);
		reminder.setBounds(25,40,250,39);
		add(reminder);
		add(first);
		setSize(300,250);
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
		new FirstAnnounceGUI();
	}
}
	

