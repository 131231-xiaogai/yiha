package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Flags.Flag;

import bean.DBBean;
import bean.UsersBean;


public class UsersDao {
	
	static Connection connection=DBBean.getConn();  //创建连接
	static PreparedStatement preparedStatement = null;     //定义表示SQL语句的变量，以参数形传入数据库操作语句来执行数据库操作
	static ResultSet resultSet=null;                    
	
	//数据库操作
public static List<UsersBean> selectAllUser(String role_id,String role_status) throws SQLException{
		
		List<UsersBean> usersBeans=new ArrayList<UsersBean>();
		
		String sql="select * from user WHERE role_id=? and role_status=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, role_id);
		preparedStatement.setString(2, role_status);
		resultSet=preparedStatement.executeQuery();
		
		if(resultSet!=null){
			while(resultSet.next()){
				UsersBean usersBean=new UsersBean();
				usersBean.setUerid(resultSet.getString("uerid"));
				usersBean.setPhone(resultSet.getString("phone"));
				usersBean.setPassword(resultSet.getString("password"));
				usersBean.setNickname(resultSet.getString("nickname"));
				usersBean.setSex(resultSet.getString("sex"));
				usersBean.setRole_status(resultSet.getString("role_status"));
				usersBean.setBalance(resultSet.getString("balance"));
				usersBean.setId_number(resultSet.getString("id_number"));
				usersBean.setRole_id(resultSet.getString("role_id"));
				usersBeans.add(usersBean);
			}
		}
		return usersBeans;
	}
public static boolean user_regiest(String reg_phonemb,String reg_bassword,String reg_roleid)throws SQLException  {
	boolean flag=false;
	String role_status =reg_roleid;
	String sql = "insert into user(phone,password,role_id,role_status) values(?,?,?,?)";
	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, reg_phonemb);
	preparedStatement.setString(2, reg_bassword);
	preparedStatement.setString(3, reg_roleid);
	preparedStatement.setString(4, role_status);
	int results =preparedStatement.executeUpdate();//更新
	if(results ==1){
		flag=true;
	}
	return flag;		
}
public static UsersBean select_user_by_id(String uerid) throws SQLException{
	
	UsersBean usersBean=new UsersBean();
	
	String sql="SELECT * FROM user WHERE uerid= ?";
	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, uerid);
	resultSet=preparedStatement.executeQuery();
	
	if(resultSet!=null){
		while(resultSet.next()){
			usersBean.setUerid(resultSet.getString("uerid"));
			usersBean.setPhone(resultSet.getString("phone"));
			usersBean.setPassword(resultSet.getString("password"));
			usersBean.setNickname(resultSet.getString("nickname"));
			usersBean.setSex(resultSet.getString("sex"));
			usersBean.setRole_status(resultSet.getString("role_status"));
			usersBean.setBalance(resultSet.getString("balance"));
			usersBean.setId_number(resultSet.getString("id_number"));
			usersBean.setRole_id(resultSet.getString("role_id"));			
			//usersBeans.add(usersBean);	
		}
	}
	return usersBean;
}

public static boolean insert_user_nickname(String uerid,String nickname)throws SQLException  {
	
	boolean flag=false;
	
	String sql = "UPDATE user SET nickname=? WHERE uerid=?";
	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, nickname);
	preparedStatement.setString(2, uerid);
	int results =preparedStatement.executeUpdate();//更新
	if(results ==1){
		flag=true;
	}
	return flag;		
}

public static boolean insert_user_sex(String uerid,String sex)throws SQLException  {
	
	boolean flag=false;
	
	String sql = "UPDATE user SET sex=? WHERE uerid=?";
	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, sex);
	preparedStatement.setString(2, uerid);
	int results =preparedStatement.executeUpdate();//更新
	if(results ==1){
		flag=true;
	}
	return flag;		
}

//Delete_user_by_userIDServlet

public static boolean deleted_user_byId(String uerid)throws SQLException {
	
	boolean flag=false;
	String sql = "DELETE FROM user WHERE uerid=?";
	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, uerid);
	int results =preparedStatement.executeUpdate();//更新
	if(results ==1){
		flag=true;
	}
	return flag;
	
	
}

public static boolean update_user_balance(String uerid,String  price)throws SQLException  {
	boolean flag=false;
	String balance=UsersDao.select_user_by_id(uerid).getBalance();
	double diff=Double.valueOf(balance)-Double.valueOf(price);
	if (diff<0) {
		flag=false;
	}else {
		String newBalanceString=Double.valueOf(balance)-Double.valueOf(price)+"";
		String sql = "UPDATE user "
				+ "SET balance=? "
				+ "WHERE uerid=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, newBalanceString);
		preparedStatement.setString(2, uerid);

		int results =preparedStatement.executeUpdate();//更新
		if(results ==1){
			flag=true;
		}
	}
	
	return flag;		
}

public static UsersBean select_user_by_shopID(String shop_id) throws SQLException{
	
	UsersBean usersBean=new UsersBean();
	
	String sql="SELECT * FROM user WHERE uerid= ?";
	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, shop_id);
	resultSet=preparedStatement.executeQuery();
	
	if(resultSet!=null){
		while(resultSet.next()){
			usersBean.setUerid(resultSet.getString("uerid"));
			usersBean.setPhone(resultSet.getString("phone"));
			usersBean.setPassword(resultSet.getString("password"));
			usersBean.setNickname(resultSet.getString("nickname"));
			usersBean.setSex(resultSet.getString("sex"));
			usersBean.setRole_status(resultSet.getString("role_status"));
			usersBean.setBalance(resultSet.getString("balance"));
			usersBean.setId_number(resultSet.getString("id_number"));
			usersBean.setRole_id(resultSet.getString("role_id"));			
			//usersBeans.add(usersBean);	
		}
	}
	return usersBean;
	}

//update_shop_balance
public static boolean update_shop_balance(String uerid,String  price)throws SQLException  {
	boolean flag=false;
		String sql = "UPDATE user "
				+ "SET balance=? "
				+ "WHERE uerid=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, price);
		preparedStatement.setString(2, uerid);

		int results =preparedStatement.executeUpdate();//更新
		if(results ==1){
			flag=true;
		}
	
	
	return flag;	
}
//
public static boolean update_user_role(String uerid,String role_id)throws SQLException  {
	boolean flag=false;
		String sql = "UPDATE user "
				+ "SET role_id=? "
				+ "WHERE uerid=? ";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, role_id);
		preparedStatement.setString(2, uerid);

		int results =preparedStatement.executeUpdate();//更新
		if(results ==1){
			flag=true;
		}
	
	
	return flag;	
}



}
