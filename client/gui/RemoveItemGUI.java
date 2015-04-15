package client.gui;

import javax.swing.*;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.*;

import gamedata.*;

public class RemoveItemGUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JLabel query;
	public ButtonGroup removelist=new ButtonGroup();
	public JLabel equipimg;
	public JButton removeEquipment;
	private JRadioButton cb;

	
	public RemoveItemGUI(String Occupation,String equipStatus){
		Equipment[] equips=null;
		Object o;
		try{
			Class<?> Type = Class.forName("gamedata." + Occupation);
			o=Type.getConstructor().newInstance();
			Field f = Type.getField("equips");
			equips=(Equipment[])(f.get(o));	
		} catch(Exception e){
			e.printStackTrace();
		}
		setLayout(null);
		JPanel third=new JPanel();
		third.setLayout(new BoxLayout(third,BoxLayout.Y_AXIS));
		while(equipStatus.length()>0){
			int i=Integer.parseInt(equipStatus.substring(0, 1));
			equipStatus=equipStatus.substring(1);
			cb=new JRadioButton(equips[i].Item);
			cb.addActionListener(new SelectionListener(this,Occupation,equips));
			removelist.add(cb);
			third.add(cb);	
		}
		add(query=new JLabel("请选择要废除的装备："));
		add(removeEquipment=new JButton("确定"));
		add(third);
		ImageIcon image = new ImageIcon(this.getClass().getResource("/resource/"+Occupation+"/"+Occupation+".jpg")); 
		image.setImage((image.getImage().getScaledInstance(180,250,Image.SCALE_DEFAULT))); 
		add(equipimg=new JLabel(image));
		equipimg.setBounds(200,10,180,250);
		query.setBounds(50, 10, 200, 50);
		third.setBounds(50, 50, 150, 200);
		removeEquipment.setBounds(50,200,70,30);
		setSize(400,300);	
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
	}
	

	public static void main(String args[]){	
        new RemoveItemGUI("Warrior","123456");

	}
	public void setLocate(ClientGUI gui){

        int width=gui.getWidth();
        
        Point pt=gui.getLocationOnScreen();

        int x=pt.x+width;

        int y=pt.y;

        setLocation(x,y);
	}
	
}

class SelectionListener implements ActionListener{
	RemoveItemGUI gui;
	String occupation;
	Equipment[] equips;
	
	public SelectionListener(RemoveItemGUI gui,String occupation,Equipment[] equips){
		this.gui=gui;
		this.occupation=occupation;
		this.equips=equips;
	}
	public void actionPerformed(ActionEvent e){
		String equipName=((JRadioButton)e.getSource()).getText();
		ImageIcon image=null;
		for(Equipment equip:equips){
			if(equip.Item.equals(equipName)){
				image = new ImageIcon(this.getClass().getResource("/resource/"+occupation+"/"+equip.Item+".jpg"));
			}
		}
		image.setImage((image.getImage().getScaledInstance(180,250,Image.SCALE_DEFAULT)));
		gui.equipimg.setIcon(image);
	}
}
