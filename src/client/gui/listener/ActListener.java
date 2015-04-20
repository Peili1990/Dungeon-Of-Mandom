package client.gui.listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import client.Client;
import client.gui.ClientGUI;

public class ActListener extends KeyAdapter{
	Client client;
	ClientGUI sframe;
	public ActListener(Client c,ClientGUI sf){
		client=c;
		sframe=sf;
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			client.send(sframe.sendFD.getText());
			sframe.sendFD.setText("");
		}
	}
	
	
}