import java.util.ArrayList;

public class Main {

	public static int TOTAL_CARS = 5;
	public static int ASSESSMENT_INTERVAL = 1;
	public static int TOTAL_DISTANCE = 10000;

	public static void main(String[] args) throws InterruptedException {
		ArrayList<Car> cars = new ArrayList<Car>();

		initRaceCars(cars);

		int time = 0;
		printCars(0, cars);
		while (checkWinner(cars) == null) {
			time += 2;
			assessRaceCars(cars);
			printCars(time, cars);
			Thread.sleep(50);
		}
		System.out.println("Race complete! Won by : " + checkWinner(cars).teamNumber);
		for (Car car : cars) {
			System.out.println("\nTEAM NUMBER " + car.teamNumber);
			System.out.print("DISTANCE LEFT " + (int) (TOTAL_DISTANCE - car.currentDistanceCovered));
			System.out.println(" NITROUS USED " + " " + car.nitroUsed);
		}
	}

	public static void initRaceCars(ArrayList<Car> cars) {
		for (int i = 0; i < TOTAL_CARS; i++) {
			cars.add(new Car(TOTAL_CARS, i + 1));
		}
		System.out.println("And today's competitors' stats : ");
		for (Car car : cars) {
			System.out.println("Team = " + car.teamNumber + " | Top Speed = " + car.topSpeed + " | Accn = " + car.acceleration
					+ " | Head Start = " + car.currentDistanceCovered);
		}
	}

	public static void assessRaceCars(ArrayList<Car> cars) {
		calculateDistances(cars);
		calculateSpeeds(cars);
	}

	public static void calculateDistances(ArrayList<Car> cars) {
		for (Car car : cars) {
			/*
			 * s = ut + (1/2) at^2
			 */
			car.currentDistanceCovered += car.currentSpeed * ASSESSMENT_INTERVAL
					+ (car.acceleration * ASSESSMENT_INTERVAL * ASSESSMENT_INTERVAL) / 2;
		}
	}

	public static void calculateSpeeds(ArrayList<Car> cars) {
		for (Car car : cars) {
			if (car.currentSpeed <= car.topSpeed) {
				double newSpeed = (car.currentSpeed + car.acceleration * ASSESSMENT_INTERVAL);
				if (newSpeed <= car.topSpeed)
					car.currentSpeed = newSpeed;
				else
					car.currentSpeed = car.topSpeed;
			}
		}

		/*
		 * HANDLING FACTOR Check if there is any car in the proximity of 10
		 * meters. If yes, decrease the currentSpeed by 0.8. But that has to be
		 * done after calculating speed. Checking in calculateSpeeds, as this is
		 * being called after calculateDistances
		 */

		for (int i = 0; i < cars.size() - 1; i++) {
			// if (Math.abs(cars.get(i).currentDistanceCovered - cars.get(i +
			// 1).currentDistanceCovered) <= 10) {
			// cars.get(i).currentSpeed = cars.get(i).currentSpeed *
			// cars.get(i).hF;
			// System.out.println("PROXIMITY WARNING! Slowing down speed of " +
			// cars.get(i).teamNumber);
			// }

			for (int j = i + 1; j < cars.size(); j++) {
				if (Math.abs(cars.get(i).currentDistanceCovered - cars.get(j).currentDistanceCovered) <= 10) {
					cars.get(i).reduceSpeed = true;
					cars.get(j).reduceSpeed = true;
				}
			}
		}
		for (Car car : cars) {
			if (car.reduceSpeed) {
				car.currentSpeed = car.currentSpeed * car.hF;
				car.reduceSpeed = false;
				System.out.println("REDUCED SPEED! " + car.teamNumber);
			}
		}

		/*
		 * NITROUS check which team is last, and use their Nitro
		 */

		Car lastCar = cars.get(0);
		for (Car car : cars) {
			if (car.currentDistanceCovered < lastCar.currentDistanceCovered) {
				lastCar = car;
			}
		}
		System.out.println("LAST CAR IS = " + lastCar.teamNumber);
		if (!lastCar.nitroUsed) {
			System.out.println("" + lastCar.teamNumber + " USED NITRO!");
			lastCar.nitroUsed = true;
			/*
			 * set lastCars speed to twice its current speed if that less than
			 * its topSpeed, else set its speed to topSpeed
			 */
			lastCar.currentSpeed = (lastCar.currentSpeed * 4 <= lastCar.topSpeed) ? lastCar.currentSpeed * 2 : lastCar.topSpeed;
		}
	}

	public static Car checkWinner(ArrayList<Car> cars) {
		for (Car car : cars) {
			if (car.currentDistanceCovered >= TOTAL_DISTANCE) {
				return car;
			}
		}
		return null;
	}

	public static void printCars(int time, ArrayList<Car> cars) {
		System.out.println("At " + time + " : teamNumber | currentSpeed | currentDistanceCovered");
		int DISPLAY_THRESHOLD = 100;
		for (Car car : cars) {
			for (int i = 0; i < TOTAL_DISTANCE / DISPLAY_THRESHOLD; i++) {
				/*
				 * Dividing total distance into 100 ' = ' denoting the racetrack
				 * ' x ' will denote the car at that position/ part of track
				 */
				if (i == (int) (car.currentDistanceCovered / DISPLAY_THRESHOLD))
					System.out.print(car.teamNumber);
				else
					System.out.print("=");
			}
			System.out.println();
		}
		for (int i = 0; i < 20; ++i)
			System.out.println();
	}
}