import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;


public class ProcessFriends {
//	HashMap<String,Integer> friendList;
			
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String,Integer> friendList = new HashMap<String,Integer>();
		Integer noOfFriends = null;
		
		BufferedReader br = null;
		PrintWriter writer = null;
        try {
        	System.out.println("Start");
    		System.out.println(new Date());
    		
//            br = new BufferedReader(new FileReader("IO_Files/testfile.txt"));
            br = new BufferedReader(new FileReader("IO_Files/com-orkut.ungraph.txt"));
            String line;
            while ((line = br.readLine()) != null) {
            	for (String retval: line.split("\t")){
            		noOfFriends = friendList.get(retval);
            		friendList.put(retval,noOfFriends!=null?noOfFriends+1:1);
            	}
            }
            System.out.println("After Reading");
    		System.out.println(new Date());
//            System.out.println(friendList);
            br.close();
            br = null;
            
        	br = new BufferedReader(new FileReader("IO_Files/queries"));
        	String s;
        	writer = new PrintWriter("IO_Files/output.txt", "UTF-8");
        	
        	while((s = br.readLine()) != null) {
    			noOfFriends = friendList.get(s);
        		writer.println(s + " : " + (noOfFriends!=null?noOfFriends:0));
        		noOfFriends = 0;
        	}
        	
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("End");
    		System.out.println(new Date());
        }
	}

}
