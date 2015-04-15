package gamedata;

import java.util.*;


public class Warrior {
	
	int[] equipment;
	List<Monster> MonsterInDungeon;
	String who;
	List<String> Clearsteps;
	
	public Equipment equips[]={new Equipment("纯爷们","3HP"),
			             new Equipment("盔甲","5HP"),
			             new Equipment("盾牌","3HP"),
			             new Equipment("圣杯","无视不死怪物（偶数）"),
			             new Equipment("火炬","无视攻击力≤3的怪物"),
			             new Equipment("斩首剑","进入地下城后，指定一种怪物无视"),
			             new Equipment("龙枪","无视龙")};
	
	public List<String> Clearing(int[] equipment,List<Monster> MonsterInDungeon,String who){
		this.equipment=equipment;
		this.MonsterInDungeon=MonsterInDungeon;
		this.who=who;
		Clearsteps=new ArrayList<String>();
		init();
		checkHolyGrail();
		checkTorch();
		checkDragonlance();
		decideSword();
		restClearing();
		return Clearsteps;
	}
	
	private void init(){
		boolean isNaked=false;
		String HP=String.valueOf(3+5*equipment[1]+3*equipment[2]);
		String entry=HP+"血纯爷们战士"+who;
		if(HP.equals("3")) 
			isNaked=true;
		boolean Iteminhand=false;
		for(int i=3;i<equipment.length;i++){
			if(equipment[i]==1){
				if(!Iteminhand){
					entry+="拿着"+equips[i].Item;
					Iteminhand=true;
				}
				else
					entry+="、"+equips[i].Item;
			}
		}
		if(isNaked)
			entry+="果奔着";
		entry+="进入了地城!";
		Clearsteps.add(entry);	
	}
	
	private void checkHolyGrail(){
		if(equipment[3]==0)
			return;
		boolean hasUndead=false;
		Iterator<Monster> iter=MonsterInDungeon.iterator();
		String step1=who+"使用圣杯轻轻一晃,";
		while(iter.hasNext()){
			Monster a=(Monster)iter.next();
			if(a.atk%2==0){
				if(!hasUndead){
				hasUndead=true;
				step1+=String.valueOf(a.atk)+a.name;
				}
				else
					step1+=" "+String.valueOf(a.atk)+a.name;
				iter.remove();
			}
		}
		if(!hasUndead)
			return;
		step1+="被轻松驱除。";
		Clearsteps.add(step1);
		
	}
	
	private void checkTorch(){
		if(equipment[4]==0)
			return;
		boolean hasPuny=false;
		Iterator<Monster> iter=MonsterInDungeon.iterator();
		String step2=who+"使用火炬轻轻一挥,";
		while(iter.hasNext()){
			Monster a=(Monster)iter.next();
			if(a.atk<=3){
				if(!hasPuny){
					hasPuny=true;
					step2+=String.valueOf(a.atk)+a.name;
				}
				else
					step2+=" "+String.valueOf(a.atk)+a.name;
				iter.remove();
			}
		}
		if(!hasPuny)
			return;
		step2+="闻风而逃。";
		Clearsteps.add(step2);
	}
	
	private void checkDragonlance(){
		if(equipment[6]==0)
			return;
		Iterator<Monster> iter=MonsterInDungeon.iterator();
		while(iter.hasNext()){
			Monster a=(Monster)iter.next();
			if(a.atk==9){		
				Clearsteps.add(who+"一枪挑死了9龙！");
				iter.remove();
				return;
			}
		}
	}
	
	private void decideSword(){
		if(equipment[5]==0||MonsterInDungeon.isEmpty())
			return;
		int atk_count[]=new int[10];
		int max_atk=0;
		int max_atk_single=0;
		for(Monster a:MonsterInDungeon){
			atk_count[a.atk]+=a.atk;
			if(atk_count[a.atk]>max_atk){
				max_atk=atk_count[a.atk];
				max_atk_single=a.atk;
			}
		}
		Iterator<Monster> iter=MonsterInDungeon.iterator();
		while(iter.hasNext()){
			Monster a=(Monster)iter.next();
			if(a.atk==max_atk_single)		
				iter.remove();	
		}
		if(max_atk/max_atk_single==2)
			Clearsteps.add(who+"使用斩首剑灭掉了所有"+String.valueOf(max_atk_single)+
					Monster.findMonster(max_atk_single)+"!");
		else
			Clearsteps.add(who+"一剑斩掉了"+String.valueOf(max_atk_single)+
					Monster.findMonster(max_atk_single)+"!");
			
	}
	
	private void restClearing(){
		int hp=3+5*equipment[1]+3*equipment[2];
		Iterator<Monster> iter=MonsterInDungeon.iterator();
		while(iter.hasNext()){
			Monster a=(Monster)iter.next();
			hp-=a.atk;
			if(hp>0)
				Clearsteps.add(RandomSentence.killSentence(who, String.valueOf(a.atk)+a.name));
			else {
				Clearsteps.add(RandomSentence.deadSentence(who, String.valueOf(a.atk)+a.name));
				Clearsteps.add(GamedataCode.FIALED_CHANLLENGE);
				return;
			}
		}
		Clearsteps.add("纯爷们"+who+"成功从地城生还！");
		Clearsteps.add(GamedataCode.SUCCESS_CHANLLENGE);
	}
	
	
	
	public static void main(String arg[]){
		Warrior a=new Warrior();
		int equipment[]={1,1,1,1,1,1,1};
		List<Monster> MonsterInDungeon=new ArrayList<Monster>();
		MonsterInDungeon.add(new Monster(1,"哥布林"));
		MonsterInDungeon.add(new Monster(1,"哥布林"));
		MonsterInDungeon.add(new Monster(2,"骷髅兵"));
		MonsterInDungeon.add(new Monster(2,"骷髅兵"));
		MonsterInDungeon.add(new Monster(3,"兽人"));
		MonsterInDungeon.add(new Monster(3,"兽人"));
		MonsterInDungeon.add(new Monster(4,"吸血鬼"));
		MonsterInDungeon.add(new Monster(4,"吸血鬼"));
		MonsterInDungeon.add(new Monster(5,"魔像"));
		MonsterInDungeon.add(new Monster(5,"魔像"));
		MonsterInDungeon.add(new Monster(6,"巫妖王"));
		MonsterInDungeon.add(new Monster(7,"恶魔"));
		MonsterInDungeon.add(new Monster(9,"龙"));
		List<String> Clearsteps=a.Clearing(equipment, MonsterInDungeon, "abc");
		for(String ab:Clearsteps)
			System.out.println(ab);	
	}
	

}
