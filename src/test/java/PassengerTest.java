import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class PassengerTest {

	@Test
	void testPassenger() throws IOException {
		Passenger passenger = new Passenger("123-456-789", "John Smith");
		assertEquals("Passenger John Smith with identifier: 123-456-789",
				passenger.toString());
	}

}