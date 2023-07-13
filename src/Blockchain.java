import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Blockchain {
    private List<Block> chain;
    private Map<Location, Double> recordedLevels;

    // Constructor
    public Blockchain() {
        this.chain = new ArrayList<>();
        // Create the genesis block
        Block genesisBlock = createGenesisBlock();
        this.chain.add(genesisBlock);
        this.recordedLevels = new HashMap<>();
    }

    // Create the genesis block
    private Block createGenesisBlock() {
        return new Block(
                0,
                0,
                "0",
                new ArrayList<>(),
                0,
                calculateHash(0, 0, "0", new ArrayList<>(), 0)
        );
    }

    // Calculate the hash of a block
    private String calculateHash(int index, long timestamp, String previousHash, List<RFData> data, int nonce) {
        String blockData = index + timestamp + previousHash + data.toString() + nonce;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(blockData.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Mine a new block
    public void mineBlock(List<RFData> data) {
        Block previousBlock = chain.get(chain.size() - 1);
        int index = previousBlock.getIndex() + 1;
        long timestamp = System.currentTimeMillis();
        String previousHash = previousBlock.getHash();
        int nonce = 0;
        String hash = calculateHash(index, timestamp, previousHash, data, nonce);

        // Adjust the difficulty level based on the number of data objects
        int difficulty = 2;

        // Proof of work - Find a valid hash by adjusting the nonce
        while (!hash.substring(0, difficulty).equals("0".repeat(difficulty))) {
            nonce++;
            hash = calculateHash(index, timestamp, previousHash, data, nonce);
        }

        Block newBlock = new Block(index, timestamp, previousHash, data, nonce, hash);
        chain.add(newBlock);

        for (RFData rfData : data) {
            recordedLevels.put(rfData.getLocation(), rfData.getLevel());
        }

        // If the level of data is higher than 10, call the external API
        if (data.size() > 10) {
            callExternalAPI();
        }

        broadcastBlock(newBlock);
    }

    // Broadcast the new block to other nodes (simplified demonstration)
    private void broadcastBlock(Block block) {
        // Iterate over connected nodes and send the block
        // Example: for (Node node : connectedNodes) { node.receiveBlock(block); }
        System.out.println("Broadcasting Block: " + block.getHash());
    }

    // Call the external API (simplified demonstration)
    private void callExternalAPI() {
        System.out.println("Calling External API...");
    }

    public double getSignalLevel(Location location) {
        return recordedLevels.getOrDefault(location, 0.000);
    }

    public void printBlockchain() {
        for (Block block: chain) {
            block.display();
        }
    }
}
