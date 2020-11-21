import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class DatabaseOperationsExtension implements BeforeAllCallback,
		AfterAllCallback, BeforeEachCallback, AfterEachCallback {

	private Connection connection;
	private Savepoint savepoint;

	@Override
	public void beforeAll(ExtensionContext context) {
		connection = ConnectionManager.openConnection();
		TablesManager.dropTable(connection);
		TablesManager.createTable(connection);
	}

	@Override
	public void afterAll(ExtensionContext context) {
		ConnectionManager.closeConnection();
	}

	@Override
	public void beforeEach(ExtensionContext context) throws SQLException {
		connection.setAutoCommit(false);
		savepoint = connection.setSavepoint("savepoint");
	}

	@Override
	public void afterEach(ExtensionContext context) throws SQLException {
		connection.rollback(savepoint);
	}

}