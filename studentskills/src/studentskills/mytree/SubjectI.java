package studentskills.mytree;

import java.util.List;

/***
 * Subject interface ---> list of all replicas , notifying all the replicas.
 */
public interface SubjectI{

public List<StudentRecord> getReplicaList();
public void notifyReplicas();
}