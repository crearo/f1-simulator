public class Car {

	private int teamNumber;
	private double topSpeed;
	private int acceleration;
	private boolean nitroUsed;
	private double handlingFactor;
	private double currentDistanceCovered;
	private double currentSpeed;
	private boolean reduceSpeed;

	public int getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(int teamNumber) {
		this.teamNumber = teamNumber;
	}

	public double getTopSpeed() {
		return topSpeed;
	}

	public void setTopSpeed(double topSpeed) {
		this.topSpeed = topSpeed;
	}

	public int getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(int acceleration) {
		this.acceleration = acceleration;
	}

	public boolean isNitroUsed() {
		return nitroUsed;
	}

	public void setNitroUsed(boolean nitroUsed) {
		this.nitroUsed = nitroUsed;
	}

	public double getHandlingFactor() {
		return handlingFactor;
	}

	public void setHandlingFactor(double hF) {
		this.handlingFactor = hF;
	}

	public double getCurrentDistanceCovered() {
		return currentDistanceCovered;
	}

	public void setCurrentDistanceCovered(double currentDistanceCovered) {
		this.currentDistanceCovered = currentDistanceCovered;
	}

	public double getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(double currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	public boolean isReduceSpeed() {
		return reduceSpeed;
	}

	public void setReduceSpeed(boolean reduceSpeed) {
		this.reduceSpeed = reduceSpeed;
	}

	public Car(int TOTAL_CARS, int i) {
		this.teamNumber = i;
		this.topSpeed = 5 * (Constants.BASE_SPEED + (Constants.THRESHOLD_SPEED * i)) / 18;
		this.acceleration = (Constants.BASE_ACCELERATION - Constants.THRESHOLD_ACCN * i);
		this.nitroUsed = false;
		this.handlingFactor = Constants.HANDLING_FACTOR;
		this.currentDistanceCovered = ((TOTAL_CARS - i) * Constants.INIT_START_DISTANCE);
		this.currentSpeed = 0;
		this.reduceSpeed = false;
	}
}