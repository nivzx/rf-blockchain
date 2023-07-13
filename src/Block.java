import java.util.List;

public class Block {
    private final int index;
    private final long timestamp;
    private final String previousHash;
    private final List<RFData> data;
    private final int nonce;
    private final String hash;

    // Constructor
    public Block(int index, long timestamp, String previousHash, List<RFData> data, int nonce, String hash) {
        this.index = index;
        this.timestamp = timestamp;
        this.previousHash = previousHash;
        this.data = data;
        this.nonce = nonce;
        this.hash = hash;
    }

    // Getters
    public int getIndex() {
        return index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public List<RFData> getData() {
        return data;
    }

    public int getNonce() {
        return nonce;
    }

    public String getHash() {
        return hash;
    }

    public void display() {
        System.out.println("********************");
        System.out.println("Index: " + this.getIndex());
        System.out.println("Time: " + this.getTimestamp());
        System.out.println("Prev Hash: " + this.getPreviousHash());
        System.out.println("Nonce: " + this.getNonce());
        System.out.println("Hash: " + this.getHash());

        for (RFData d: this.data) {
            d.printData();
        }

        System.out.println("********************");
    }
}
