import java.util.ArrayList;

public class RaceDay {

	public void initRaceCars(ArrayList<Car> cars) {
		for (int i = 0; i < Constants.TOTAL_CARS; i++) {
			cars.add(new Car(Constants.TOTAL_CARS, i + 1));
		}
		System.out.println("And today's competitors' stats : \n\n");
		for (Car car : cars) {
			System.out.println("Team = " + car.getTeamNumber() + " | Top Speed = " + car.getTopSpeed() + " | Accn = "
					+ car.getAcceleration() + " | Head Start = " + car.getCurrentDistanceCovered());
		}
	}

	public void assessRaceCars(ArrayList<Car> cars) {
		calculateDistances(cars);
		calculateSpeeds(cars);
	}

	public void calculateDistances(ArrayList<Car> cars) {
		for (Car car : cars) {
			/*
			 * s = ut + (1/2) at^2
			 */
			car.setCurrentDistanceCovered(car.getCurrentDistanceCovered()
					+ (car.getCurrentSpeed() * Constants.ASSESSMENT_INTERVAL + (car.getAcceleration()
							* Constants.ASSESSMENT_INTERVAL * Constants.ASSESSMENT_INTERVAL) / 2));
		}
	}

	public void calculateSpeeds(ArrayList<Car> cars) {
		for (Car car : cars) {
			if (car.getCurrentSpeed() <= car.getTopSpeed()) {
				double newSpeed = (car.getCurrentSpeed() + car.getAcceleration() * Constants.ASSESSMENT_INTERVAL);
				if (newSpeed <= car.getAcceleration())
					car.setCurrentSpeed(newSpeed);
				else
					car.setCurrentSpeed(car.getTopSpeed());
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
				if (Math.abs(cars.get(i).getCurrentDistanceCovered() - cars.get(j).getCurrentDistanceCovered()) <= 10) {
					cars.get(i).setReduceSpeed(true);
					cars.get(j).setReduceSpeed(true);
				}
			}
		}
		for (Car car : cars) {
			if (car.isReduceSpeed()) {
				car.setCurrentSpeed(car.getCurrentSpeed() * car.getHandlingFactor());
				car.setReduceSpeed(false);
				System.out.println("REDUCED SPEED! " + car.getTeamNumber());
			}
		}

		/*
		 * NITROUS check which team is last, and use their Nitro
		 */

		Car lastCar = cars.get(0);
		for (Car car : cars) {
			if (car.getCurrentDistanceCovered() < lastCar.getCurrentDistanceCovered()) {
				lastCar = car;
			}
		}
		System.out.println("LAST CAR IS = " + lastCar.getTeamNumber());
		if (!lastCar.isNitroUsed()) {
			System.out.println("" + lastCar.getTeamNumber() + " USED NITRO!");
			lastCar.setNitroUsed(true);
			/*
			 * set lastCars speed to twice its current speed if that less than
			 * its topSpeed, else set its speed to topSpeed
			 */
			lastCar.setCurrentSpeed((lastCar.getCurrentSpeed() * 4 <= lastCar.getTopSpeed()) ? lastCar.getCurrentSpeed() * 2
					: lastCar.getTopSpeed());
		}
	}

	public Car checkWinner(ArrayList<Car> cars) {
		for (Car car : cars) {
			if (car.getCurrentDistanceCovered() >= Constants.TOTAL_DISTANCE) {
				return car;
			}
		}
		return null;
	}

	public void printCars(int time, ArrayList<Car> cars) {
		System.out.println("At " + time + " : teamNumber | currentSpeed | currentDistanceCovered");
		for (Car car : cars) {
			for (int i = 0; i < Constants.TOTAL_DISTANCE / Constants.DISPLAY_THRESHOLD_LENGTH; i++) {
				/*
				 * Dividing total distance into 100 ' = ' denoting the racetrack
				 * ' x ' will denote the car at that position/ part of track
				 */
				if (i == (int) (car.getCurrentDistanceCovered() / Constants.DISPLAY_THRESHOLD_LENGTH))
					System.out.print(car.getTeamNumber());
				else
					System.out.print("=");
			}
			System.out.println();
		}
		for (int i = 0; i < 20; ++i)
			System.out.println();
	}
}