package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBBean {
	public static Connection conn = null;

	public static Connection getConn() {

		try {

			// /为应用程序加载驱动

			Class.forName("com.mysql.jdbc.Driver");

			// 使用JDBC_URL来标识特定的数据库

			// String driver_url =
			// "jdbc:mysql://172.20.10.3:3306/paotui?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";

			String driver_url = "jdbc:mysql://localhost:3306/cloth_db?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";

			// 接收数据库的URL,数据库用户名，用户口令，即连接数据库

			conn = DriverManager.getConnection(driver_url, "root", "");

			if (conn != null) {

				System.out.println("连接成功");

			} else {

				System.out.println("连接失败");

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return conn;

	}

	public static void closeAll(Connection conn, PreparedStatement ps) {

		System.out.println("数据库断开连接");

		if (conn != null) {

			try {

				conn.close();

			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

		if (ps != null) {

			try {

				ps.close();

			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

	}

	/**
	 * 
	 * 关闭数据库
	 */

	public static void closeAll(Connection conn, PreparedStatement ps,
			ResultSet rs) {

		if (conn != null) {

			try {

				conn.close();

			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

		if (ps != null) {

			try {

				ps.close();

			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

		if (rs != null) {

			try {

				rs.close();

			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

	}
}
