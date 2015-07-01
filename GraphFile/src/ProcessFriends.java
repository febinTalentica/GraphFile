import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;


public class ProcessFriends {
//  HashMap<String,Integer> friendList;
    private static final long MEGABYTE = 1024L * 1024L;
     
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        long startTime = System.currentTimeMillis();
        
        HashMap<Integer,Integer> friendList = new HashMap<Integer,Integer>();
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
                    noOfFriends = friendList.get(Integer.valueOf(retval));
                    friendList.put(Integer.valueOf(retval),noOfFriends!=null?noOfFriends+1:1);
                }
            }
            
//            System.out.println(friendList);
            br.close();
            br = null;
            
            br = new BufferedReader(new FileReader("IO_Files/queries"));
            String s;
            writer = new PrintWriter("IO_Files/output.txt", "UTF-8");
            
            while((s = br.readLine()) != null) {
                noOfFriends = friendList.get(Integer.valueOf(s));
                writer.println(s + " : " + (noOfFriends!=null?noOfFriends:0));
                noOfFriends = 0;
            }
            
            System.out.println("After Reading");
            System.out.println(new Date());
            
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println(elapsedTime);
        
            Runtime runtime = Runtime.getRuntime();
            // Run the garbage collector
            runtime.gc();
            // Calculate the used memory
            long memory = runtime.totalMemory() - runtime.freeMemory();
            System.out.println("Used memory is bytes: " + memory);
            System.out.println("Used memory is megabytes: "
                + bytesToMegabytes(memory));
            
            
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
    
    public static long bytesToMegabytes(long bytes) {
       return bytes / MEGABYTE;
     }

}
