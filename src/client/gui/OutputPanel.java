package client.gui;

import java.awt.*;

import javax.swing.*;


public class OutputPanel extends Panel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected JTextArea showTA;
	protected JLabel heroimg;
	protected JTextArea heroequip;
	protected JScrollBar bar;
	
	public OutputPanel(){
		setLayout(new FlowLayout());
		JScrollPane scroll = new JScrollPane(showTA=new JTextArea(20,40)); 
		bar=scroll.getVerticalScrollBar();
		showTA.setLineWrap(true);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scroll);
		Panel heroPanel=new Panel();
		heroPanel.setLayout(new BorderLayout(5,5));
		heroPanel.add("North",new JTextArea(10,20));
		heroPanel.add("South",heroequip=new JTextArea(10,20));
		add(heroPanel);
		
		
		
	}
	
	

}
