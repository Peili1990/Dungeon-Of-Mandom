package server;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Exchanger;

import gamedata.GamedataCode;

public class Gameprocess extends Thread{
	Vector<ServerHander> playerlist,escapees;
	static Vector<ServerHander> clientlist;
	Exchanger<String> exchanger;
	
	public Gameprocess(Vector<ServerHander> clientlist,Vector<ServerHander> playerlist){
		this.playerlist=new Vector<ServerHander>(playerlist);
		Gameprocess.clientlist=clientlist;
		escapees=new Vector<ServerHander>();
	}
	
	public void run() {		
		Collections.shuffle(playerlist);
		Gameplay gameplay = new Gameplay();
		String gameoccupation=getOccupation();
		sendAllClient("一群纯爷们"+gameoccupation+"在酒馆喝酒。");
		gameplay.Gamestart(gameoccupation);
		while (playerlist.size() > 1) {
			ServerHander curplayer = playerlist.elementAt(0);
			exchanger=curplayer.exchanger;
			if (gameplay.checkcard() == 0) {
				send(curplayer, "", Servercode.NO_MORE_MONSTER);
				sendAllClient(curplayer.who + "意识到地城里充满了怪物，自觉跑路了");
				escapees.addElement(curplayer);
				playerlist.removeElement(curplayer);
				continue;
			} else {
				send(curplayer, "", Servercode.INQUIRY_FIRST_STEP);
				while (true) {
					String code="";
					try {
						code = exchanger.exchange("");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (code.equals(Clientcode.ACCEPT_SIGNAL)) {
						sendAllClient(curplayer.who + " said: 最近的地城弱爆了！");
						int card_atk = gameplay.drawAcard();
						send(curplayer, "", Servercode.MONSTER_ORDER * 10
								+ card_atk);
					} else if (code.equals(Clientcode.RUN_SIGNAL)) {
						sendAllClient(curplayer.who + " said: 我突然想起我家有事...");
						playerlist.removeElement(curplayer);
						escapees.addElement(curplayer);
						break;
					} else if (code.equals(Clientcode.SET_MONSTER)) {
						gameplay.setMonster();
						sendAllClient(curplayer.who + " said: 我还能打十个！");
						playerlist.removeElement(curplayer);
						playerlist.addElement(curplayer);
						break;
					} else if (code.length() >= 5
							&& code.substring(0, 5).equals(Clientcode.REMOVE_EQUIPMENT)) {
						int index = Integer.parseInt(code.substring(5));
						String ItemName=gameplay.removeEquipment(index);
						sendAllClient(curplayer.who + " said: 我没有"
								+ ItemName + "一样能打！");
						playerlist.removeElement(curplayer);
						playerlist.addElement(curplayer);
						break;
					} else if (code.equals(Clientcode.INQUIRY_EQUIPMENT)){
						String equipmentlist=gameplay.checkequipment();
						if(equipmentlist.equals("")){
							gameplay.setMonster();
							send(curplayer,"",Servercode.NO_MORE_EQUIPMENT);
							sendAllClient(curplayer.who + " said: 我还能打十个！");
							playerlist.removeElement(curplayer);
							playerlist.addElement(curplayer);
							break;
						}
						else send(curplayer,
								Servercode.EQUIPMENT_INF + equipmentlist, 0);
					} else continue;
				}
			}
		}
		ServerHander chanllenger=playerlist.elementAt(0);
		Herochanllenge herochanllenge=new Herochanllenge(gameplay,gameoccupation,chanllenger.who);
		herochanllenge.start();
		exchanger=herochanllenge.exchanger;
		String step;
		while (true) {
			try {
				step = exchanger.exchange("");
				if(step.equals(GamedataCode.SUCCESS_CHANLLENGE)||step.equals(GamedataCode.FIALED_CHANLLENGE))
					break;
				else 
					sendAllClient(step);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sendAllClient(Servercode.GAME_OVER);

	}
	protected static void sendAllClient(String msg){
			Enumeration<ServerHander> allclients=clientlist.elements();
			while(allclients.hasMoreElements()){
				ServerHander serh=(ServerHander)allclients.nextElement();
				send(serh,msg,0);
			}
	}
	
	public static void send(ServerHander target,String msg,int code){
		try{
			target.out.writeUTF(msg);
			target.out.writeInt(code);
			target.out.flush();
		}
		catch(IOException e){
			target.interrupt();
		}
	}
	
	public String getOccupation(){
		ServerHander decider=clientlist.elementAt(0);
		send(decider,"",Servercode.INQUITY_OCCUPATION);
		sendAllClient("由"+decider.who+"来决定职业");
		exchanger=decider.exchanger;
		try{
			String code=exchanger.exchange("");
			switch(Integer.parseInt(code.substring(5))){
				case 1: return "Warrior";
				default: return "";
			}
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
}
