package gamedata;

import java.util.*;

public class RandomSentence {
	
	static String ReservekillSentence[]={"who与Monster战了个痛。",
		 								 "who轻松手撕Monster。",
										 "who拳脚相加，把Monster打哭了。",
										 "who无压力打死了Monster。"};
	
	static String ReserveDeadSentence[]={"who遇到了Monster，被一脚踩扁了。",
        								 "与Monster一战后，我们再也没见过纯爷们who。",
         								 };
	
	public static String killSentence(String who,String Monster){
		Random rd = new Random();
		String result=ReservekillSentence[rd.nextInt(ReservekillSentence.length)];
		result=result.replace("who", who);
		result=result.replace("Monster",Monster);
		return result;
	}
	
	public static String deadSentence(String who,String Monster){
		Random rd = new Random();
		String result=ReserveDeadSentence[rd.nextInt(ReserveDeadSentence.length)];
		result=result.replace("who", who);
		result=result.replace("Monster",Monster);
		return result;
	}
	
	

}
