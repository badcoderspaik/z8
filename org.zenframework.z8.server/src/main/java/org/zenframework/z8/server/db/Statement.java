package org.zenframework.z8.server.db;

import java.sql.SQLException;

public class Statement extends BasicStatement {
	public Statement(Connection connection) {
		super(connection);
	}

	@Override
	public void prepare(String sql) throws SQLException {
		this.sql = sql;
		this.statement = connection().prepareStatement(sql);
	}

	public static int executeUpdate(Connection connection, String sql) throws SQLException {
		Statement statement = new Statement(connection);
		statement.prepare(sql);
		try {
			return statement.executeUpdate();
		} catch(SQLException e) {
			SqlExceptionConverter.rethrow(statement.vendor(), e);
		}

		return 0;
	}
}
