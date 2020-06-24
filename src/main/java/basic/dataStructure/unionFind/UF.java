package basic.dataStructure.unionFind;

public interface UF {
    // Is id p connected to q?
    boolean isConnected(int p, int q);

    // connect p with q.
    void union(int p, int q);

    int getSize();
}
