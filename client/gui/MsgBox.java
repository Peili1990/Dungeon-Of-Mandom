package client.gui;

import javax.swing.*;

import java.awt.Point;
import java.awt.event.*;

public class MsgBox extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MsgBox(String msg){
		setLayout(null);
		JLabel label=new JLabel(msg);
		label.setBounds(50, 30, 200, 50);
		add(label);
		JButton Button=new JButton("好吧");
		Button.setBounds(170,100,70,30);
		add(Button);
		setSize(300,200);
		
		Button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
	/*
	public static void main(String arg[]){
		MsgBox a=new MsgBox();
		a.MsgBox1("纯爷们已裸奔，没法再扔装备！");
		
	}
	*/
	public void setLocate(ClientGUI gui){

        int width=gui.getWidth();
        
        Point pt=gui.getLocationOnScreen();

        int x=pt.x+width;

        int y=pt.y;

        setLocation(x,y);
	}

}
