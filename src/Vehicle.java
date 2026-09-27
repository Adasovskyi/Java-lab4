import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle<T extends Human> {
    private final int maxSeats;
    private final List<T> passengers;

    public Vehicle(int maxSeats) {
        this.maxSeats = maxSeats;
        this.passengers = new ArrayList<>();
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public int getOccupiedSeats() {
        return passengers.size();
    }

    public void board(T passenger) throws VehicleFullException {
        if (passengers.size() >= maxSeats) {
            throw new VehicleFullException("Vehicle is full");
        }
        passengers.add(passenger);
    }

    public void alight(T passenger) throws PassengerNotFoundException {
        if (!passengers.remove(passenger)) {
            throw new PassengerNotFoundException("Passenger is not in the vehicle");
        }
    }
}
