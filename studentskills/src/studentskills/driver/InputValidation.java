package studentskills.driver;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.io.IOException;
import studentskills.mytree.StudentRecord;
import studentskills.mytree.TreeHelper;

/***
 * Validating all the input files
 */
public class InputValidation {

    /***
     * validating the input file.
     * @param inputIn - each line
     * @throws Exception
     */
    public void inputValidation(String inputIn) throws Exception{
        int bNumber;
        String firstname;
        String lastName;
        Double GPA;
        String Major;
        Set<String> skillSet = new HashSet<>();

         int length = inputIn.substring(0,inputIn.indexOf(":")).length();
        String [] arr = inputIn.split(",",inputIn.length());
        bNumber= Integer.parseInt(arr[0].substring(0,length));
        firstname = arr[0].substring(5,arr[0].length());
        lastName = arr[1];
        GPA = Double.parseDouble(arr[2]) ;
        Major = arr[3];
        for (int i = 4; i<= arr.length-1;i++){
            skillSet.add(arr[i]);
        }
        if((length!= 4) || (bNumber<0)){
            System.out.println("bNumber is not correct");
            System.exit(0);
        }
        StudentRecord record = TreeHelper.initRecord(bNumber,firstname,lastName,GPA,Major,skillSet);
        
    }

    /***
     * Validating the modification file.
     * @param inputIn - input line
     */
    public void modifyValidation(String inputIn){
        int tree;
        int bNumber;
        String value = null;
        String newVal = null;

        String [] arr = inputIn.split(",",inputIn.length());
        

        tree= Integer.parseInt(arr[0]);
        bNumber= Integer.parseInt(arr[1]);
        if(inputIn.contains(":")) {
            for (int i = 2; i < arr.length; i++) {
                value = arr[i].substring(0, arr[i].indexOf(":"));
                newVal = arr[i].substring(arr[i].indexOf(":") + 1, arr[i].length());
                if(newVal.equals("")){
                
                    Driver.errorMap.put(Driver.counter,"There is no new value to modify for bNumber: " + bNumber + " against the value: "+ value +"\n");
                    Driver.counter++;
                    
                }else{
                    TreeHelper.modifyRecord(tree,bNumber,value,newVal);
                }
                
            }
        }else{
            for(int i=2;i < arr.length; i++){
               newVal = arr[i];
                TreeHelper.modifyRecord(tree,bNumber,value,newVal);
            }
        }
        
    }

}