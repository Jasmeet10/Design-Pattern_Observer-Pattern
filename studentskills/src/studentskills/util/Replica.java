package studentskills.util;

/***
 * Three tree are A, B and C
 where A is replicated by B and C
 where B is replicated by C and A
 where C is replicated by A and B
 */
public enum Replica {
    A(0), B(1), C(2);

    public final int value;

    Replica(int value){
        this.value = value;
    }

}

