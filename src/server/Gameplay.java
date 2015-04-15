package server;

import gamedata.*;

import java.lang.reflect.Field;
import java.util.*;

public class Gameplay {
	
	Monster MonsterArr[]={new Monster(1,"哥布林"),new Monster(1,"哥布林"),new Monster(2,"骷髅兵"),
			new Monster(2,"骷髅兵"),new Monster(3,"兽人"),new Monster(3,"兽人"), new Monster(4,"吸血鬼"), 
			new Monster(4,"吸血鬼"),new Monster(5,"魔像"),new Monster(5,"魔像"), new Monster(6,"巫妖王"), 
			new Monster(7,"恶魔"),new Monster(9,"龙")};
	
	public List<Monster> MonsterInDungeon;
	
	public int equipment[]=new int[7];
	
	Equipment[] equips;
	
	int count;
	
	public void Gamestart(String Occupation){
		try {
			Class<?> Type = Class.forName("gamedata." + Occupation);
			Object o=Type.getConstructor().newInstance();
			Field f = Type.getField("equips");
			equips=(Equipment[])(f.get(o));	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shuffle(MonsterArr);
		count=0;
		MonsterInDungeon=new ArrayList<Monster>();
		for(int i=0;i<7;i++){
			equipment[i]=1;
		}
	}
	
	public int checkcard(){
		return 13-count;
	}
	
	public int drawAcard(){
		if(count<13){
			count++;
			return MonsterArr[count-1].atk;
		}
		else return -1;
	}
	
	public void setMonster(){
		MonsterInDungeon.add(MonsterArr[count-1]);
	}
	
	public String removeEquipment(int index){
		int pointer=1;
		while(equipment[pointer]==0)
			pointer++;
		for(int count=0;count<index;count++){
			pointer++;
			while(equipment[pointer]==0)
				pointer++;
		}
		equipment[pointer]=0;
		return equips[pointer].Item;
	}
	
	public String checkequipment(){
		String equiplist="";
		for(int i=1;i<equipment.length;i++){
			if(equipment[i]==1)
				equiplist+=String.valueOf(i);
		}
		
		return equiplist;
	}
	
	public void shuffle(Monster MonsterArr[]){
		Random rd = new Random();
		for(int i=0;i<MonsterArr.length;i++){
			int j=rd.nextInt(MonsterArr.length);
			Monster temp=MonsterArr[i];
			MonsterArr[i]=MonsterArr[j];
			MonsterArr[j]=temp;
		}
	}

}
