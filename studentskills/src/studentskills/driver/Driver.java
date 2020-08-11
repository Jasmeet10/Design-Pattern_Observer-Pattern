package studentskills.driver;

import java.nio.file.InvalidPathException;
import studentskills.util.FileProcessor;
import java.io.FileNotFoundException;
import java.io.IOException;
import studentskills.util.FileDisplayInterface;
import studentskills.util.StdoutDisplayInterface;
import studentskills.util.Results;
import studentskills.util.MyLogger;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;


/**
 * @author Jasmeet Kaur Dua
 *
 */
public class Driver {
    public static HashMap<Integer,String> errorMap = new HashMap<Integer, String>();
    public static int counter = 0;
   /*  public String output1;
    public String output2;
    public String output3; */

     
    private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 7;

    public static void main(String[] args){

        String input = args[0];
        String output1 = args[1];
        String output2 = args[2];
        String output3 = args[3];
        String modify = args[4];
        String logger = args[5];
        String error = args[6];
        String Returnline;

        
        
            if ((output1.isEmpty()) || (output2.isEmpty()) || (output3.isEmpty())) {
                System.out.println("output file name is empty, Check README for the value usage!");
                System.exit(0);
            }
            if (modify.isEmpty()) {
                System.out.println("modify file name is empty, Check README for the value usage!");
                System.exit(0);
            }
            if (error.isEmpty()) {
                System.out.println("error file name is empty, Check README for the value usage!");
                System.exit(0);
            }
            if (logger.isEmpty()) {
                System.out.println("inputx is reqired for debug, Check README for the value usage!");
                System.exit(0);
            }
            if (input.isEmpty()) {
                System.out.println("input file name is empty, Check README for the value usage!");
                System.exit(0);
            }

           if ((args.length != 7) || (args[0].equals("${input}")) || (args[1].equals("${out1}")) || (args[2].equals("${out2}")) || (args[3].equals("${out3}")) || (args[4].equals("${modify}"))|| (args[5].equals("${debug}")) || (args[6].equals("${error}"))) {
               System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
                System.exit(0);
            }
            int debugValue = Integer.parseInt(logger);
            MyLogger.setDebugValue(debugValue);
            if(debugValue < 0 || debugValue > 5) {
                MyLogger.Message("WARNING!! The assigned debug value is out of range. Check README for the value usage!");
                System.exit(0);
            }

           
            File file = new File(args[6]);
            File file1 = new File(args[0]);
            file.delete();
            
        try {

            InputValidation validation = new InputValidation();

            FileProcessor fileprocessor = new FileProcessor(input);
            FileProcessor fileprocessor1 = new FileProcessor(modify);

            if(input.equals(args[0])) {
                 
                while ((Returnline = fileprocessor.poll()) != null) {
                    
                    try {
                        validation.inputValidation(Returnline);
                    }
                     catch (Exception e){
                        errorMap.put(counter,"There is an empty line in the input file \n");
                        counter++;
                    } 
                    
                }
                /***
                checking if the file is empty or not
                 */
                 if(file1.length()== 0){
                    System.out.println("Input File is empty");
                    System.exit(0);
                }
            }
            /***
            * checking for the modify file.
            */
            if(modify.equals(args[4])){
                while ((Returnline = fileprocessor1.poll()) != null) {                   
                    try {
                    
                        validation.modifyValidation(Returnline);
                    }
                     catch (Exception e){
                        errorMap.put(counter,"There is an empty line in the modify file \n");
                        counter++;
                    } 
                }
                
            }

            if (file.createNewFile()) ;
            FileWriter fileWriter = new FileWriter(file,true);
            for (Integer keys : errorMap.keySet())
                {
                fileWriter.write(errorMap.get(keys));
                }
            fileWriter.flush();
            fileWriter.close();
             
            
        }
        
        catch (FileNotFoundException e) {
            System.out.println("Missing Input File");
            System.exit(0);
        }
        catch (IOException e) {
            System.out.println("IO Exception");
            System.exit(0);
        }
        catch (SecurityException e){
            System.out.println("You do not have the read permissions to the input file");
            System.exit(0);
        }
        catch (InvalidPathException e){
            System.out.println("Invalid path");
            System.exit(0);
        }

        //System.out.println("Hello World! Lets get started with the assignment");
        /***
         * writing the results and displaying it.
         */
        FileDisplayInterface results = new Results(output1,output2,output3);
        StdoutDisplayInterface results1 = new Results(output1,output2,output3);
        
        if(debugValue == 5 )
            results.writeToFile();
        else if(debugValue == 4)
            results1.writeToStdout();
        else if((debugValue ==1) || (debugValue == 2) || (debugValue == 3)){
            results.writeToFile();
            results1.writeToStdout();
        }

    }
}


