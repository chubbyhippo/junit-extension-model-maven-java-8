import java.util.logging.Logger;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class LogPassengerExistsExceptionExtension
		implements TestExecutionExceptionHandler {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public void handleTestExecutionException(ExtensionContext context,
			Throwable throwable) throws Throwable {
		if (throwable instanceof PassengerExistsException) {
			logger.severe("Passenger exists:" + throwable.getMessage());
			return;
		}
		throw throwable;
	}
}