package server;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.Exchanger;

public class Herochanllenge extends Thread{
	Gameplay gameplay;
	String Occupation;
	String who;
	List<String> result;
	Exchanger<String> exchanger=new Exchanger<String>(); 
	
	public Herochanllenge(Gameplay gameplay, String Occupation, String who){
		this.gameplay=gameplay;
		this.Occupation=Occupation;
		this.who=who;
		
	}
	
	@SuppressWarnings("unchecked")
	public void run() {
		try {
			Class<?> Type = Class.forName("gamedata." + Occupation);
			Object o=Type.getConstructor().newInstance();
			Method m1=Type.getDeclaredMethod("Clearing",int[].class,List.class,String.class);
			result=(List<String>) m1.invoke(o,gameplay.equipment,gameplay.MonsterInDungeon,who);
			for(String step:result){
				exchanger.exchange(step);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	/*
	public static void main(String arg[]){
		Herochanllenge a=new Herochanllenge();
		Gameplay b=new Gameplay();
		b.Gamestart("Warrior");
		b.drawAcard();
		b.setMonster();
		a.Herochanllenge1(b, "Warrior","abc");
		a.run();
		
	}
	*/
}

	
