package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.DBBean;

public class UserDao {

	static Connection connection = DBBean.getConn();

	static ResultSet resultSet = null;

	static PreparedStatement preparedStatement = null;

	public static boolean userLogin(String phone, String password) throws SQLException {
		boolean flag=false;
		String sql="select * from user where phone=? and password=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, phone);
		preparedStatement.setString(2, password);
		resultSet=preparedStatement.executeQuery();
		if (resultSet!=null) {
			if (resultSet.next()) {
				flag=true;
			}
		}
		return flag;
	}
}
