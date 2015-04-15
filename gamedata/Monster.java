package gamedata;

public class Monster {
	public int atk;
	String name;
	
	public Monster(int atk,String name){
		this.atk=atk;
		this.name=name;
	}
	
	static Monster MonsterArr[]={new Monster(1,"哥布林"),new Monster(1,"哥布林"),new Monster(2,"骷髅兵"),
			new Monster(2,"骷髅兵"),new Monster(3,"兽人"),new Monster(3,"兽人"), new Monster(4,"吸血鬼"), 
			new Monster(4,"吸血鬼"),new Monster(5,"魔像"),new Monster(5,"魔像"), new Monster(6,"巫妖王"), 
			new Monster(7,"恶魔"),new Monster(9,"龙")};
	
	public static String findMonster(int atk){
		for(int i=0;i<MonsterArr.length;i++)
			if(atk==MonsterArr[i].atk)
				return MonsterArr[i].name;
		return "";
	}

}
