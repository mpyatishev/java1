package dbService.dao;

import dbService.dataSets.UsersDataSet;
import dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersDAO {
	private Executor executor;

	public UsersDAO(Connection connection) {
		this.executor = new Executor(connection);
	}

	public UsersDataSet get(long id) throws SQLException {
		return executor.execQuery("select * from users where id=" + id,
				result -> {
					result.next();
					return new UsersDataSet(result.getLong("id"), result.getString("name"));
				});
	}

	public UsersDataSet get(String name, String password) throws SQLException {
		return executor.execQuery("select * from users where name='" + name + "' AND password='" + password + "'",
				result -> {
					result.next();
					return new UsersDataSet(result.getLong("id"), result.getString("name"));
				});
	}

	public long getUserId(String name) throws SQLException {
		return executor.execQuery("select * from users where name='" + name + '\'',
				result -> {
					result.next();
					return result.getLong(1);
				});
	}

	public void insertUser(String name, String password) throws SQLException {
		executor.execUpdate("insert into users (name, password) values ('" + name +"', '" + password + "')");
	}

	public void createTable() throws SQLException {
		executor.execUpdate(
				"create table if not exists users (id bigint auto_increment, name varchar(256), password varchar(256), primary key (id))");
	}

	public void dropTable() throws SQLException {
		executor.execUpdate("drop table users");
	}
}
