import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		ArrayList<Car> cars = new ArrayList<Car>();
		RaceDay raceDay = new RaceDay();
		raceDay.initRaceCars(cars);

		int time = 0;
		raceDay.printCars(0, cars);
		while (raceDay.checkWinner(cars) == null) {
			time += 2;
			raceDay.assessRaceCars(cars);
			raceDay.printCars(time, cars);
			Thread.sleep(Constants.DISPLAY_THRESHOLD_TIME);
		}

		Collections.sort(cars, new ColorComparator());
		System.out.println("Race complete! Won by : " + cars.get(0).getTeamNumber());
		for (Car car : cars) {
			System.out.println("\nTEAM NUMBER " + car.getTeamNumber());
			System.out.print("DISTANCE LEFT " + (int) (Constants.TOTAL_DISTANCE - car.getCurrentDistanceCovered()));
			System.out.println(" NITROUS USED " + " " + car.isNitroUsed());
		}
	}

	static class ColorComparator implements Comparator<Car> {
		public int compare(Car c1, Car c2) {
			return (int) (c2.getCurrentDistanceCovered() - c1.getCurrentDistanceCovered());
		}
	}
}