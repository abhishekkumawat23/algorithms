package objectOrientedDesign.parkingLot;

import java.util.ArrayList;

// Only vehicle doesn't make sense. So making it abstract.
// Motorcycle makes sense but vehicle doesn't make sense. Vehicle has to be something.
public abstract class Vehicle {

	protected VehicleSize size;
	protected int spotsNeeded = 0;
	private String licensePlate;
	private ArrayList<ParkingSpot> parkingSpots = new ArrayList<ParkingSpot>();
	
	public void park() throws Exception {
		// park vehicle in parking spot.
		if (parkingSpots.size() < spotsNeeded) {
			throw new Exception();
		}
		// Set available as false for parkingSpots
		for (ParkingSpot spot: parkingSpots) {
			spot.markAvailable(false);
		}
	}
	
	public void clearParking() {
		// Clear parking spot.
		for (ParkingSpot spot: parkingSpots) {
			spot.markAvailable(true);
		}
		parkingSpots = new ArrayList<ParkingSpot>();
	}
	
	
}
