public class for2pts {
	
    public static String makeOutWord(String s1, String s2) {
    	if(s1.length() != 4 || s2.length() == 0) {
    		return "";
    	}
        return s1.substring(0, 2) + s2 + s1.substring(2);   
    }
    
    public static String seeColor(String s) {
    	if(s.length() < 3) {
    		return "";
    	}
    	if(s.substring(0,3).equals("red")) {
    		return "red";
    	}
    	if( s.substring(0,4).equals("blue")) {
    		return "blue";
    	}
        return "";   
    }
    
    public static String extraEnd(String s) {
    	if(s.length() < 2) {
    		return "";
    	}
        return s.substring(s.length()-2, s.length()) + s.substring(s.length()-2, s.length()) + s.substring(s.length()-2, s.length());   
    }
    
    public static boolean hasBad(String s) {
    	if(s.length() == 0) {
    		return false;
    	}
    	if(s.substring(0,3).equals("bad") || s.substring(1,4).equals("bad")) {
    		return true;
    	}
        return false;   
    }
    
    public static String conCat(String s1, String s2) {
    	if(s1.length() == 0 && s2.length() == 0) {
    		return "";
    	}
    	if(s1.length() == 0) {
    		return s2;
    	}
    	if(s2.length() == 0) {
    		return s1;
    	}
    	if(s1.charAt(s1.length()-1) == (s2.charAt(0))) {
    		return s1 + s2.substring(1);
    	}
        return s1 + s2;   
    }
    
    public static boolean frontAgain(String s) {
    	if(s.length() < 2) {
    		return false;
    	}
    	if(s.substring(0,2).equals(s.substring(s.length()-2,s.length()))) {
    		return true;
    	}
        return false;   
    }
    
    public static String without2(String s) {
    	if(s.length() == 0) {
    		return "";
    	}
    	if(s.substring(0,2).equals(s.substring(s.length()-2, s.length()))) {
    		return s.substring(2);
    	}
        return s;   
    }
    
    public static String comboString(String s1, String s2) {
    	if(s1.length() == 0 && s2.length() == 0) {
    		return "";
    	}
    	if(s1.length() < s2.length()) {
    		return s1 + s2 + s1;
    	}
    	if(s1.length() > s2.length()) {
    		return s2 + s1 + s2;
    	}
        return "";   
    }
    
    public static String minCat(String s1, String s2) {
    	if(s1.length() == 0 && s2.length() == 0) {
    		return "";
    	}
    	if(s1.length() == s2.length()) {
    		return s1 + s2;
    	}
    	if(s1.length() < s2.length()) {
    		return s1 + s2.substring(s2.length()-s1.length());
    	}
    	if(s1.length() > s2.length()) {
    		return s1.substring(s1.length()-s2.length()) + s2;
    	}
        return "";   
    }
    
    public static String deFront(String s) {
    	if(s.length() < 2) {
    		return "";
    	}
    	if(s.charAt(0) == 'a' && s.charAt(1) == 'b') {
    		return s;
    	}
    	if(s.charAt(0) == 'a') {
    		return 'a' + s.substring(2);
    	}
    	if(s.charAt(1) == 'b') {
    		return 'b' + s.substring(2);
    	}
        return s.substring(2);   
    }

    /******************************************************************/
    
    public static boolean commonEnd(int[] A, int[] B) {
    	if(A.length < 1 && B.length < 1) {
    		return false;
    	}
    	if(A[0] == B[0] || A[A.length-1] == B[B.length-1]) {
    		return true;
    	}
        return false;   
    }
    
    public static int[] reverse3(int[] A) {
    	if(A.length != 3) {
    		return null;
    	}
    	int temp;
    		temp = A[0];
    		A[0] = A[A.length - (1)];
    		A[A.length - (1)] = temp;
        return A;   
    }

    public static int maxTriple(int[] A) {
        return 0;   
    }

    public static boolean has23(int[] A) {
        return false;   
    }
    
    public static int[] swapEnds(int[] A) {
        return A;   
    }

    public static int[] front11(int[] A, int[] B) {
        return A;   
    }

    /******************************************************************/

    public static boolean squirrelPlay(int n, boolean b) {
        return false;   
    }
    
    public static boolean caughtSpeeding(int n, boolean b) {
        return false;   
    }
    
    public static int maxMod5(int n1, int n2) {
        return 0;   
    }
    
    public static boolean inOrder(int n1, int n2, int n3, boolean b) {
        return false;   
    }
    
    public static boolean specialEleven(int n) {
        return false;   
    }
    
    public static boolean answerCell(boolean b1, boolean b2, boolean b3) {
        return false;   
    }
    
} 