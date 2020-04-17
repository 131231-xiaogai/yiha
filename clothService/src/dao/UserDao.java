package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.DBBean;
import bean.UsersBean;

public class UserDao {
	/*
	 * 登录方法和通过手机号查找用户方法
	 * */

	static Connection connection = DBBean.getConn();

	static ResultSet resultSet = null;

	static PreparedStatement preparedStatement = null;

	public static boolean userLogin(String phone, String password) throws SQLException {
		boolean flag=false; //
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
	
	public static UsersBean select_user_by_phone(String phone) throws SQLException {
		UsersBean usersBean = null;//
		String sql = "select * from user where phone=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, phone);
		resultSet=preparedStatement.executeQuery();
		if (resultSet != null) {
			if (resultSet.next()) {
				usersBean =new UsersBean();
				usersBean.setPhone(resultSet.getString("phone"));
				usersBean.setBalance(resultSet.getString("balance"));
				usersBean.setId_number(resultSet.getString("id_number"));
				usersBean.setImage(resultSet.getString("image"));
				usersBean.setNickname(resultSet.getString("nickname"));
				usersBean.setSex(resultSet.getString("sex"));
				usersBean.setUerid(resultSet.getString("uerid"));
				usersBean.setRole_id(resultSet.getString("role_id"));
			}			
		}
			
		return usersBean;	
	}
}
