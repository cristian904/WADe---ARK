package utils;

public class Utils {
	
	public static String prepareForLikeStatement(String s){
		if(s!=null) s = "%" + s + "%";
		else s = "%%";
		
		return s.toUpperCase();
	}

}
