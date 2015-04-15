package client.gui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import client.Client;
import client.Clientcode;
import client.gui.ClientGUI;

public class ReadyListener extends MouseAdapter{
	Client client;
	ClientGUI sframe;
	public ReadyListener(Client c,ClientGUI sf){
		client=c;
		sframe=sf;
	}
	
	public void mouseClicked(MouseEvent e){
		if(sframe.gamestart.isEnabled()){
			client.send(Clientcode.READY_SIGNAL);
			sframe.gamestart.setEnabled(false);
		}
	}
}