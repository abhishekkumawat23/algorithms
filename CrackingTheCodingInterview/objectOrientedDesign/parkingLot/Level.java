package objectOrientedDesign.parkingLot;

import java.util.ArrayList;
import java.util.List;

public class Level {
	private int floor;
	private List<ParkingSpot> parkingSpots = new ArrayList<ParkingSpot>();

	public List<ParkingSpot> findSpotsOfSize(int spotsNeeded) {
		// Check continuous
		List<ParkingSpot> parkingSpots = new ArrayList<ParkingSpot>();
		// Check if parking spots of size spots needed is present.
		return parkingSpots;
	}
	
	public List<ParkingSpot> availableSpots(){
		// From parkingSpots, return the one which is free right now.
		return parkingSpots;
	}

}
