package objectOrientedDesign.parkingLot;

import java.util.List;

public class ParkingLot {

	private Level[] levels;
	
	public List<ParkingSpot> findParkingSpots(Vehicle vehicle) {
		// Find parking spot for vehicle.
		for (Level level: levels) {
			List<ParkingSpot> parkingSpots = level.findSpotsOfSize(vehicle.spotsNeeded);
			if (parkingSpots.size() > 1) {
				return parkingSpots;
			}
		}
		return null;
	}
}
