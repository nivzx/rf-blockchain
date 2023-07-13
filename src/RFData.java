public class RFData {
    private final Location location;
    private final double level;

    public RFData(Location location, double level) {
        this.location = location;
        this.level = level;
    }

    public Location getLocation() {
        return this.location;
    }

    public double getLevel() {
        return this.level;
    }

    public boolean isLocationMatched(double latitude, double longitude) {
        return (this.location.latitude == latitude && this.location.longitude == longitude);
    }

    public void printData() {
        System.out.println("[");
        System.out.println("\tLocation: " + this.location.latitude + ", " + this.location.longitude);
        System.out.println("\tLevel: " + this.getLevel());
        System.out.println("]");
    }
}

