package site.rentofficevn.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@PropertySource("classpath:application.properties")
public class ConnectionUtils {
	/*private static String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	private static String USER = "root";
	private static String PASS = "Nqv@762003";*/
	@Value("${spring.datasource.url}")
	private static String DB_URL;

<<<<<<< HEAD
	@Value("${spring.datasource.username}")
	private static String USER;

	@Value("${spring.datasource.password}")
	private static String PASS;
	public static Connection getConnection() {
=======
	public static final Connection getConnection() {
>>>>>>> 908640334f080c7e5b0b7806b76cbfa6fbea68e7
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
