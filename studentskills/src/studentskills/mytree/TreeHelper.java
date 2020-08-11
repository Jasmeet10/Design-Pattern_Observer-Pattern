package studentskills.mytree;

import studentskills.driver.Driver;
import studentskills.util.Replica;
import java.util.List;
import studentskills.driver.InputValidation;
import java.util.HashSet;
import java.util.Set;
import studentskills.util.MyLogger;
import java.util.Map;

/***
 * Tree Helper for creating tree of each node.
 */
public class TreeHelper{

    public static StudentRecord root;
    static String result = "";

    /***
     * To insert node in the tree
     * @param bNumber
     * @param firstName
     * @param lastName
     * @param gpa
     * @param major
     * @param skillSet
     */
    static void insert(int bNumber, String firstName, String lastName, double gpa, String major, Set<String> skillSet) {

        MyLogger.writeMessage("New Node is Inserted", MyLogger.DebugLevel.NODE_UPDATE);

        root = insertNode(root, bNumber,firstName,lastName,gpa,major,skillSet);
    }

    static StudentRecord insertNode(StudentRecord root, int bNumber, String firstName, String lastName, double gpa, String major, Set<String> skillSet) {


        /* If root is null, initialize and return a new node */
        if (root == null) {
            root = new StudentRecord(bNumber,firstName,lastName,gpa,major,skillSet, Replica.A);
            return root;
        }
        /**
         * updating the existing record.
         */
        if (bNumber == root.bNumber){
           root = search(bNumber,Replica.A);
           root.firstName = firstName;
           root.lastName = lastName;
           root.gpa = gpa;
           root.major = major;
           int size = 10;
            for (String skills:skillSet) {
            root.skillSet.add(skills);
            size--;
            if(size==0){
                Driver.errorMap.put(Driver.counter,"Skills cannot be more than 10 for bNumber: "+bNumber +"\n");
                Driver.counter++;
                break;
            }
        }
        }
        if (bNumber < root.bNumber)
            root.left = insertNode(root.left, bNumber,firstName,lastName,gpa,major,skillSet);
        else if (bNumber > root.bNumber)
            root.right = insertNode(root.right, bNumber,firstName,lastName,gpa,major,skillSet);
            

        return root;
    }

    /***
     * Help results to display the data on console.
     */
    public static void printStudent() {
          printStudentRecord(root);
    }

   public  static void printStudentRecord(StudentRecord root) {
        if (root != null) {
            printStudentRecord(root.left);
            System.out.println("bNumber: " + root.bNumber + " Skill Set: " +root.skillSet);
            printStudentRecord(root.right);

        }
    }

    /***
     * for serching the student record.
     * @param bNumber
     * @param replica
     * @return record
     */
    public static StudentRecord search(int bNumber, Replica replica) {
        StudentRecord sr =  searchNode( root,  bNumber, replica);
        if (sr == null){
            System.out.println("bNumber does not exist");
            System.exit(0);
        } 
      return sr;      
    }
   static StudentRecord searchNode ( StudentRecord root,  int bNumber, Replica replica){

        if (root==null || root.bNumber==bNumber)
            return root;

        if (root.bNumber > bNumber)
            return searchNode( root.left, bNumber,replica);

        return searchNode( root.right, bNumber, replica);
    }

    /***
     * initializing the new record
     * @param bNumberIn
     * @param firstNameIn
     * @param lastNameIn
     * @param gpaIn
     * @param majorIn
     * @param skillSetIn
     * @return - new created record
     * @throws Exception
     */
    public static StudentRecord initRecord(int bNumberIn, String firstNameIn, String lastNameIn, double gpaIn, String majorIn, Set<String> skillSetIn) throws Exception {

       // MyLogger.writeMessage("New Node is Inserted", MyLogger.DebugLevel.NODE_UPDATE);
        
        int index = 1;
        StudentRecord[] records = new StudentRecord[Replica.values().length];
        for(Replica replica: Replica.values()) {
            if(Replica.A == replica) {
                records[0] = new StudentRecord(bNumberIn, firstNameIn, lastNameIn, gpaIn, majorIn, skillSetIn, Replica.A);
            }
            else
                records[index++] = records[0].clone(replica);
        }
        insert(bNumberIn, firstNameIn, lastNameIn, gpaIn, majorIn, skillSetIn);
        
        index = 0;
        for(Replica replica: Replica.values())
            records[index++].initReplicaList(records);
        return records[0];
    
    }

    /***
     * Modifying the record under modify file.
     * @param treeIn
     * @param bNumberIn
     * @param valueIn
     * @param newValIn
     */
     public static void modifyRecord(int treeIn, int bNumberIn, String valueIn, String newValIn ){

        MyLogger.writeMessage("Node is being modified", MyLogger.DebugLevel.NODE_UPDATE);

        StudentRecord record = null;
          switch (treeIn) {
            case 0:
                
                record = search(bNumberIn,Replica.A);
                int size = record.skillSet.size();
               if(record.firstName.equals(valueIn))
                record.firstName = newValIn;
                else if(record.lastName.equals(valueIn))
                record.lastName = newValIn;
                else if(record.major.equals(valueIn))
                record.major = newValIn;
                else if(record.skillSet.contains(valueIn)){
                record.skillSet.add(newValIn);
                record.skillSet.remove(valueIn);}
                else if(valueIn == null && newValIn != null){
                
                    if(size == 10){
                         Driver.errorMap.put(Driver.counter,"Skills cannot be more than 10 for bNumber: " + bNumberIn +"\n");
                        Driver.counter++;
                        break;
                    }else{
                    record.skillSet.add(newValIn);
                    size++;
                    }
                }
                else{
                    System.out.println("GPA cannot be changed");
                    System.exit(0);
                }
                record.notifyReplicas();

            break;

            case 1:
                record = search(bNumberIn,Replica.B);
                 size = record.skillSet.size();
               if(record.firstName.equals(valueIn))
                record.firstName = newValIn;
                else if(record.lastName.equals(valueIn))
                record.lastName = newValIn;
                else if(record.major.equals(valueIn))
                record.major = newValIn;
                else if(record.skillSet.contains(valueIn)){
                record.skillSet.add(newValIn);
                record.skillSet.remove(valueIn);}
                else if(valueIn == null && newValIn != null){
                
                    if(size == 10){
                         Driver.errorMap.put(Driver.counter,"Skills cannot be more than 10 for bNumber: "+bNumberIn +"\n");
                        Driver.counter++;
                        break;
                    }else{
                    record.skillSet.add(newValIn);
                    size++;
                    }
                }
                else{
                    System.out.println("GPA cannot be changed");
                    System.exit(0);
                }
                record.notifyReplicas();
            break;
                
            case 2:
                record = search(bNumberIn,Replica.C);
                 size = record.skillSet.size();
               if(record.firstName.equals(valueIn))
                record.firstName = newValIn;
                else if(record.lastName.equals(valueIn))
                record.lastName = newValIn;
                else if(record.major.equals(valueIn))
                record.major = newValIn;
                else if(record.skillSet.contains(valueIn)){
                record.skillSet.add(newValIn);
                record.skillSet.remove(valueIn);}
                else if(valueIn == null && newValIn != null){
                
                    if(size == 10){
                         Driver.errorMap.put(Driver.counter,"Skills cannot be more than 10 for bNumber: "+bNumberIn +"\n");
                        Driver.counter++;
                        break;
                    }else{
                    record.skillSet.add(newValIn);
                    size++;
                    }
                }
                else{
                    System.out.println("GPA cannot be changed");
                    System.exit(0);
                }
                record.notifyReplicas();
            break;
        }
 
    }

    /***
     * help results to copy the data into the file
     * @return
     */
      public static String copyToFile(){
          result = "";
         result = copyToFileRec(root);
        
        return result;
    }   
     static String copyToFileRec(StudentRecord root){
          
        if (root != null) {
            copyToFileRec(root.left);
            result += root.toString() + " \n";
            
            copyToFileRec(root.right);
        } 
        return result;
     }
}