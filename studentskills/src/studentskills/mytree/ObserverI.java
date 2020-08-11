package studentskills.mytree;

import studentskills.util.Replica;

/***
 * Observer interface ---> Initializing the replicas , clone all the replicas.
 */
public interface ObserverI{

    public void initReplicaList(StudentRecord[] replicas);
    public StudentRecord clone(Replica replica);
}