package objectOrientedDesign.parkingLot;

public class Car extends Vehicle {

	public Car() {
		super();
		size = VehicleSize.Car;
		spotsNeeded = 3;
	}
}
