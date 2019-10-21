public class ExecuteWithArrays {
	
    public static int ChildrenRonde(int[] C, int s) {
        // YOUR CODE GOES HERE
    	int sizeArr = C.length;
    	int counter = 0;
    	int last = 0;
    	
    	for(int i = 0; i < C.length; i++) {
    		C[i] = 1;
    	}
    	
    	while(sizeArr > 1) {
    		//last = 0;
    		
    		for(int i = 0; i < C.length; i++) {
    			
    			if(C[i] == 0) {
    				continue;
    			}
    			
    			counter++;
    			
        		if(counter == s) {
        			
        			C[i] = 0;
        			counter = 0;
        			sizeArr--;

        	    	for(int z = 0; z < C.length; z++) {
        	            	System.out.print(C[z] + "|");
        	    	}
        	    	
        	    	System.out.println();
        	    	
        		}
    		}
    		
    	}
    	

    	for(int i = 0; i < C.length; i++) {
    		
    		if(C[i] == 1) {
    			last = i+1;
    		}
            System.out.print(C[i] + "|");
    	}
    	
    	System.out.println();
    	return last;
    }
    
    public static void main(String[] args) {
        int size = Integer.valueOf(/*args[0]*/ 7);
        int step = Integer.valueOf(/*args[1]*/ 3);
        int[] children = new int[size];
        int last = ChildrenRonde(children, step);
        System.out.println(last);
    }
}