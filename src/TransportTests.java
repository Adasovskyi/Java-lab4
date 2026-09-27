import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransportTests {

    @Test
    public void testSuccessfulBoardingAndCapacity() {
        Taxi taxi = new Taxi(3);
        FireEngine fireEngine = new FireEngine(4);
        PoliceCar policeCar = new PoliceCar(2);

        RegularPassenger normalPerson = new RegularPassenger("Ivan");
        Firefighter firefighter = new Firefighter("Petro");
        Policeman policeman = new Policeman("Oleksiy");

        // При правильному додаванні повинно не бути помилок
        assertDoesNotThrow(() -> {
            taxi.board(normalPerson);
            taxi.board(firefighter); // Таксі може перевозити будь-яких пасажирів
            fireEngine.board(firefighter); // Пожежна машина - тільки пожежників
            policeCar.board(policeman); // Поліцейська машина - тільки поліцейських
        });

        assertEquals(2, taxi.getOccupiedSeats());
        assertEquals(1, fireEngine.getOccupiedSeats());
        assertEquals(1, policeCar.getOccupiedSeats());

    }

    @Test
    public void testVehicleFullException() {
        Bus bus = new Bus(2);

        assertDoesNotThrow(() -> {
            bus.board(new RegularPassenger("Passenger 1"));
            bus.board(new Policeman("Policeman 1"));
        });

        // Виключна ситуація (всі місця зайнято)
        Exception exception = assertThrows(VehicleFullException.class,
                () -> bus.board(new Firefighter("Firefighter 1"))
        );

        assertEquals("Vehicle is full", exception.getMessage());
    }

    @Test
    public void testPassengerNotFoundException() {
        Bus bus = new Bus(2);
        RegularPassenger unknownPassenger = new RegularPassenger("Unknown Passenger");

        // Виключна ситуація (вказаний пасажир "не сидить" у транспортному засобі)
        Exception exception = assertThrows(PassengerNotFoundException.class,
                () -> bus.alight(unknownPassenger)
        );

        assertEquals("Passenger is not in the vehicle", exception.getMessage());
    }

    @Test
    public void testRoadCounting() throws VehicleFullException {
        Road road = new Road();
        Taxi taxi = new Taxi(2);
        FireEngine fireEngine = new FireEngine(2);

        taxi.board(new RegularPassenger("Alice"));
        taxi.board(new Policeman("Bob"));
        fireEngine.board(new Firefighter("Charlie"));

        road.addCarToRoad(taxi);
        road.addCarToRoad(fireEngine);

        assertEquals(3, road.getCountOfHumans());
    }
}
