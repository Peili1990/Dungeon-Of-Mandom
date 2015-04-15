package client.gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectOccupationGUI extends JFrame{

	/**
	 * 
	 */
	
	public JLabel query;
	public JLabel occupationimg;
	public ButtonGroup occupationlist=new ButtonGroup();
	public JButton ok;
	private String lists[]={"战士","野蛮人","盗贼","法师"};
	
	private static final long serialVersionUID = 1L;
	
	public SelectOccupationGUI(){
		setLayout(null);
		JPanel List=new JPanel();
		List.setLayout(new BoxLayout(List,BoxLayout.Y_AXIS));
		for(String str:lists){
			JRadioButton cb=new JRadioButton(str,true);
			cb.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					System.out.println(((JRadioButton)e.getSource()).getText());
					ImageIcon image = new ImageIcon(this.getClass().getResource("/resource/Warrior/Warrior.jpg")); 
					image.setImage((image.getImage().getScaledInstance(180,250,Image.SCALE_DEFAULT))); 
					occupationimg.setIcon(image);
				}
			});
			occupationlist.add(cb);
			List.add(cb);
		}
		add(query=new JLabel("请选择职业："));
		add(List);
		add(ok=new JButton("确定"));
		ImageIcon image = new ImageIcon(this.getClass().getResource("/resource/Warrior/Warrior.jpg")); 
		image.setImage((image.getImage().getScaledInstance(180,250,Image.SCALE_DEFAULT))); 
		add(occupationimg=new JLabel(image));
		occupationimg.setBounds(200,10,180,250);
		query.setBounds(50, 10, 200, 50);
		List.setBounds(50, 50, 150, 120);
		ok.setBounds(50,200,70,30);		
		setSize(400,300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	public static void main(String args[]){
		new SelectOccupationGUI();
	}
	
	public void setLocate(ClientGUI gui){

        int width=gui.getWidth();
        
        Point pt=gui.getLocationOnScreen();

        int x=pt.x+width;

        int y=pt.y;

        setLocation(x,y);
	}

}
