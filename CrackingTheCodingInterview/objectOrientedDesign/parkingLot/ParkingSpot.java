package objectOrientedDesign.parkingLot;

public class ParkingSpot {
	private Vehicle vehicle;
	private int spotSize;
	private int level;
	private int spotNumber;

	private boolean isAvailable;

	public void markAvailable(boolean b) {
		isAvailable = true;
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}
	
	public int getSpotNumber() {
		return spotNumber;
	}

}
