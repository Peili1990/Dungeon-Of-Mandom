package client;


import java.io.*;
import java.net.*;

import client.gui.*;
import client.gui.listener.*;

public class Client {
	
	public NameInputGUI NIsf;
	public ClientGUI sf;
	public FirstAnnounceGUI FAsf;
	public SecondAnnounceGUI SAsf;
	public RemoveItemGUI RIsf;
	public String who="";
	private Socket csocket;
	private String server="127.0.0.1";
	private int port=1024;
	private DataInputStream in;
	private DataOutputStream out;
	public static void main(String arg[]){	
		new Client();
	}
	
	public Client(){
		init(this);
		try{
			csocket=new Socket(server,port);
			if(csocket.isConnected()) System.out.println("SUCCESS!");
			in=new DataInputStream(new BufferedInputStream(csocket.getInputStream()));
			out=new DataOutputStream(new BufferedOutputStream(csocket.getOutputStream()));
			while(true){
				String msg=in.readUTF();
				int code=in.readInt();
				if(code==Servercode.INQUIRY_FIRST_STEP){
					FAsf.setLocate(sf);
					FAsf.setVisible(true);
				}
				else if(code==Servercode.INQUIRY_OCCUPATION){
					SelectOccupationGUI SOsf=new SelectOccupationGUI();
					SOsf.ok.addMouseListener(new SelectOccupationListener(this,SOsf));
					SOsf.setLocate(sf);
					SOsf.setVisible(true);
				}
				else if(code/10==Servercode.MONSTER_ORDER){
					SAsf=new SecondAnnounceGUI(code%10);
					SAsf.set.addMouseListener(new SelfDefineListener2(this,
							SAsf, Clientcode.SET_MONSTER));
					SAsf.remove.addMouseListener(new SelfDefineListener2(this,
							SAsf, Clientcode.INQUIRY_EQUIPMENT));
					SAsf.setLocate(sf);
					SAsf.setVisible(true);		
				}
				else if(code==Servercode.NO_MORE_EQUIPMENT){
					MsgBox msgbox=new MsgBox("纯爷们已果奔，没法再扔装备！");
					msgbox.setLocate(sf);
					msgbox.setVisible(true);
				}
				else if(code==Servercode.NO_MORE_MONSTER){
					MsgBox msgbox=new MsgBox("认怂吧！怪物已经抽光了！");
					msgbox.setLocate(sf);
					msgbox.setVisible(true);
				}
				else if(msg.substring(0,5).equals(Servercode.EQUIPMENT_INF)){
					RIsf=new RemoveItemGUI("Warrior",msg.substring(5));
					RIsf.removeEquipment.addMouseListener(new RemoveItemListener(this,RIsf));
					RIsf.setLocate(sf);
					RIsf.setVisible(true);
				}
				else if(msg.equals(Servercode.GAME_OVER)){
					sf.gamestart.setEnabled(true);
				}
				else {
					sf.showTA.append("--"+msg+"\n");
					sf.bar.setValue(sf.bar.getMaximum());		
				}
			}
		}
		catch(Exception e){
     		e.printStackTrace();
			this.close();
			System.exit(0);
		}
		
	}
	
	public void init(Client c){
		NIsf=new NameInputGUI();
		NIsf.ok.addMouseListener(new NameInputListener(c,NIsf));
		sf=new ClientGUI("Dungeon of Mandom");
		sf.sendFD.addKeyListener(new ActListener(c,sf));
		sf.addWindowListener(new ExitListener(c));
		sf.gamestart.addMouseListener(new ReadyListener(c,sf));
		sf.sendmsg.addMouseListener(new MsgSendListener(c,sf));
		FAsf=new FirstAnnounceGUI();
		FAsf.accept.addMouseListener(new SelfDefineListener(c,FAsf,Clientcode.ACCEPT_SIGNAL));
		FAsf.run.addMouseListener(new SelfDefineListener(c,FAsf,Clientcode.RUN_SIGNAL));
			
	}
	public void send(String msg){
		try{
			out.writeUTF(msg);
			out.flush();
		}
		catch(IOException e){
			
		}
	}
	
	public void close(){
		try{
			out.close();
			in.close();
			csocket.close();
		}
		catch(IOException ex){
			
		}
	}
}
