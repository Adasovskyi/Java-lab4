import java.util.ArrayList;
import java.util.List;

public class Road {
    public List<Vehicle<? extends Human>> carsInRoad = new ArrayList<>();

    public int getCountOfHumans() {
        int totalHumans = 0;
        for (Vehicle<? extends Human> vehicle : carsInRoad) {
            totalHumans += vehicle.getOccupiedSeats();
        }
        return totalHumans;
    }

    public void addCarToRoad(Vehicle<? extends Human> vehicle) {
        carsInRoad.add(vehicle);
    }
}
