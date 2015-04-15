package client.gui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;

import client.Client;
import client.Clientcode;
import client.gui.SelectOccupationGUI;

public class SelectOccupationListener extends MouseAdapter{
	Client client;
	SelectOccupationGUI gui;
	
	public SelectOccupationListener(Client c,SelectOccupationGUI gui){
		this.client=c;
		this.gui=gui;
	}
	
	public void mouseClicked(MouseEvent e){
		Enumeration<AbstractButton> list= gui.occupationlist.getElements();
		int index=1;
		while(list.hasMoreElements()){
			if(list.nextElement().isSelected()){
				client.send(Clientcode.SELECTED_OCCUPATION+String.valueOf(index));
				gui.setVisible(false);
				break;
			}
			index++;
		}	
	}
	

}
