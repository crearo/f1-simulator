public class Car {

	public int teamNumber;
	public double topSpeed;
	public int acceleration;
	public boolean nitroUsed;
	public double hF;

	public double currentDistanceCovered;
	public double currentSpeed;
	public boolean reduceSpeed;

	public Car(int TOTAL_CARS, int i) {
		this.teamNumber = i;
		this.topSpeed = 5 * (150 + (20 * i)) / 18;
		this.acceleration = (2 * TOTAL_CARS - i);
		this.nitroUsed = false;
		this.hF = 0.8;
		this.currentDistanceCovered = (TOTAL_CARS - i) * 200;
		this.currentSpeed = 0;
		this.reduceSpeed = false;
	}
}