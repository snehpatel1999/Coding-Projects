public class for6pts {

    public static int countYZ(String s) {
    	if(s.length() == 0) {
    		return 0;
    	}
    	String[] str = s.split(" ");
    	int counter = 0;
    	for(int i = 0; i < str.length; i++) {
    		if(str[i].charAt(str[i].length()-1) == 'z' || str[i].charAt(str[i].length()-1) == 'y') {
    			counter++;
    		}
    	}
        return counter;   
    }
    
    public static String withoutString(String s1, String s2) {
        return s1;   
    }
    
    public static String notReplace(String s) {
        return s;   
    }
    
    public static boolean equalIsNot(String s) {
        return false;   
    }
    
    public static int sumDigits(String s) {
        return 0;   
    }
    
}