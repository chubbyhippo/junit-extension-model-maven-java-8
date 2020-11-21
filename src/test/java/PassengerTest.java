import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({ ExecutionContextExtension.class,
		DatabaseOperationsExtension.class,
		DataAccessObjectParameterResolver.class })
public class PassengerTest {

	private PassengerDao passengerDao;

	public PassengerTest(PassengerDao passengerDao) {
		this.passengerDao = passengerDao;
	}

	@Test
	void testPassenger() {
		Passenger passenger = new Passenger("123-456-789", "John Smith");
		assertEquals("Passenger John Smith with identifier: 123-456-789",
				passenger.toString());
	}

	@Test
	void testInsertPassenger() {
		Passenger passenger = new Passenger("123-456-789", "John Smith");
		passengerDao.insert(passenger);
		assertEquals("John Smith",
				passengerDao.getById("123-456-789").getName());
	}

	@Test
	void testUpdatePassenger() {
		Passenger passenger = new Passenger("123-456-789", "John Smith");
		passengerDao.insert(passenger);
		passengerDao.update("123-456-789", "Michael Smith");
		assertEquals("Michael Smith",
				passengerDao.getById("123-456-789").getName());
	}

	@Test
	void testDeletePassenger() {
		Passenger passenger = new Passenger("123-456-789", "John Smith");
		passengerDao.insert(passenger);
		passengerDao.delete(passenger);
		assertNull(passengerDao.getById("123-456-789"));
	}

}