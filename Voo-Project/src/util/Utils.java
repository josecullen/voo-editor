package util;

public class Utils {

	public static boolean isNumberParseable(String text, String type){
			try{
				if(type == "Integer")
					Integer.parseInt(text);
				else
					Double.parseDouble(text);
			}catch(NumberFormatException ex){
				return false;
			}
		
		return true;
	}
	
}
