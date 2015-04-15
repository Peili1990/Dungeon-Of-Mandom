package client.gui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;

import client.Client;
import client.Clientcode;
import client.gui.RemoveItemGUI;

public class RemoveItemListener extends MouseAdapter{
	Client client;
	RemoveItemGUI gui;
	
	public RemoveItemListener(Client c,RemoveItemGUI gui){
		this.client=c;
		this.gui=gui;
	}
	
	public void mouseClicked(MouseEvent e){
		Enumeration<AbstractButton> list= gui.removelist.getElements();
		int index=0;
		while(list.hasMoreElements()){
			if(list.nextElement().isSelected()){
				client.send(Clientcode.REMOVE_EQUIPMENT+String.valueOf(index));
				gui.setVisible(false);
				break;
			}
			index++;
		}	
	}
}


