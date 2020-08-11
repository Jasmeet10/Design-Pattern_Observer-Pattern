package studentskills.mytree;

import studentskills.driver.Driver;
import studentskills.util.Replica;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import studentskills.util.MyLogger;

/***
 * creating nodes for each student record.
 */
public class StudentRecord implements SubjectI,ObserverI{
    int bNumber;
    String firstName, lastName, major;
    public double gpa;
    Set<String> skillSet;
    StudentRecord left, right;

    Replica replica;
    List<StudentRecord> replicaList;

    public StudentRecord(int bNumberIn, String firstNameIn, String lastNameIn, double gpaIn, String majorIn, Set<String> skillSetIn, Replica replica){
    
    MyLogger.writeMessage("Inside Node constructor \n", MyLogger.DebugLevel.CONSTRUCTOR);

        this.bNumber = bNumberIn;
        this.firstName = firstNameIn;
        this.lastName = lastNameIn;
        this.gpa = gpaIn;
        this.major = majorIn;
        left = right = null;
        skillSet=  new HashSet<String>(skillSetIn);
        replicaList = new ArrayList<>();
        this.replica = replica;
    }

    /***
     * initializing the replicas
     * @param list of replicas
     */
    public void initReplicaList(StudentRecord[] replicas) {
        for(StudentRecord replica: replicas) {
            if(this.replica != replica.replica)
                replicaList.add(replica);
        }
    }

    /***
     * creating list of replicas via enum
     * @return - replica list
     */
    public List<StudentRecord> getReplicaList() {
        return replicaList;
    }

    /***
     * Formatting of the data
     * @return
     */
    public String toString() {
        return "bNumber: " + bNumber +
                " First Name:" + firstName +
                " Last Name: " + lastName +
                " GPA: " + gpa +
                " Major: " + major +
                " Skill Set: " + skillSet.toString();
    }

    /***
     * clone each record/node to the replicas.
     * @param replica
     * @return
     */
    public StudentRecord clone(Replica replica) {
        return new StudentRecord(bNumber, firstName, lastName, gpa, major, new HashSet<String>(skillSet), replica);
    }

    /***
     * notifying replicas after every change.
     */
    public void notifyReplicas() {
        for(StudentRecord replica: replicaList) {
            replica.firstName = this.firstName;
            replica.lastName = this.lastName;
            replica.major = this.major;
            replica.skillSet = this.skillSet;
        }
    }

}