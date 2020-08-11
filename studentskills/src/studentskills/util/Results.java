package studentskills.util;

//import studentskills.driver.Driver;
//import java.util.ArrayList;
import  java.io.File;
import java.io.IOException;
import java.io.FileWriter;
//import java.util.Map;
import studentskills.mytree.TreeHelper;
import studentskills.driver.Driver;
import studentskills.util.Replica;
import studentskills.util.MyLogger;


public class Results implements FileDisplayInterface, StdoutDisplayInterface {
    private String output1 = "";
    private String output2 = "";
    private String output3 = "";
    public Results(String output1,String output2,String output3){
   	this.output1 = output1;
    this.output2 = output2;
    this.output3 = output3;
    } 
   /***
     * This method is the implementation of the interface of FileDisplayInterface.
     */
    public void writeToFile() {

        MyLogger.writeMessage("Nodes are written into file", MyLogger.DebugLevel.FILE_WRITE);

        try {
            for(int i= 1; i < 4;i++){
            File file = new File("output"+i);
            if (file.createNewFile()) ;
            FileWriter fileWriter = new FileWriter(file);
                String input = TreeHelper.copyToFile(); 
                fileWriter.write(input);        
            fileWriter.flush();
            fileWriter.close();
            }
        } catch (IOException e) {
            System.out.println("File cannot be created");
            System.exit(0);
        } 
    }

        /***
         * This method is the implementation of the interface of StdoutDisplayInterface.
         */
    public void writeToStdout(){

        MyLogger.writeMessage("Nodes are printed in console", MyLogger.DebugLevel.CONSOLE_WRITE);

        for(Replica replica: Replica.values()){
        System.out.println("Tree :" + replica+ "\n");
        TreeHelper.printStudent();
        System.out.println();
        }
           
    }

    

}
