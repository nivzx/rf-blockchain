import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();

        Location loc1 = new Location(1.0,1.0);
        Location loc2 = new Location(2.0,2.0);
        Location loc3 = new Location(3.0,3.0);

        RFData data1 = new RFData(loc1, -50.0);
        RFData data2 = new RFData(loc2, -60.0);
        RFData data3 = new RFData(loc3, -70.0);
        RFData data4 = new RFData(loc3, -12340.0);

        // Node 1 mines a block
        List<RFData> block = new ArrayList<>();
        block.add(data1);
        block.add(data2);

        List<RFData> block2 = new ArrayList<>();
        block2.add(data3);
        block2.add(data2);

        List<RFData> block3 = new ArrayList<>();
        block3.add(data4);

        blockchain.mineBlock(block);
        blockchain.mineBlock(block2);
        blockchain.mineBlock(block3);

        blockchain.printBlockchain();
    }
}