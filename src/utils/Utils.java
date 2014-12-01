package utils;

import java.util.List;

public class Utils {
	
	public static <T> T[] passToArray(List<T> list){
		T[] returnList = null;
		
		int count = 0;
		for(T t : list){
			returnList[count++] = t;
		}
		return returnList;
	}
}
