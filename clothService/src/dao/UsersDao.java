package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.DBBean;
import bean.UsersBean;


public class UsersDao {
	
	static Connection connection=DBBean.getConn();  //创建连接
	static PreparedStatement preparedStatement = null;     //定义表示SQL语句的变量，以参数形传入数据库操作语句来执行数据库操作
	static ResultSet resultSet=null;                    
	
	//数据库操作
public static List<UsersBean> selectAllUser(String role_id) throws SQLException{
		
		List<UsersBean> usersBeans=new ArrayList<UsersBean>();
		
		String sql="select * from user WHERE role_id=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, role_id);
		resultSet=preparedStatement.executeQuery();
		
		if(resultSet!=null){
			while(resultSet.next()){
				UsersBean usersBean=new UsersBean();
				usersBean.setUerid(resultSet.getString("uerid"));
				usersBean.setPhone(resultSet.getString("phone"));
				usersBean.setPassword(resultSet.getString("password"));
				usersBean.setNickname(resultSet.getString("nickname"));
				usersBean.setSex(resultSet.getString("sex"));
				usersBean.setImage(resultSet.getString("image"));
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
	
	String sql = "insert into user(phone,password,role_id) values(?,?,?)";
	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, reg_phonemb);
	preparedStatement.setString(2, reg_bassword);
	preparedStatement.setString(3, reg_roleid);
	int results =preparedStatement.executeUpdate();//更新
	if(results ==1){
		flag=true;
	}
	return flag;		
}
public static UsersBean select_user_by_id() throws SQLException{
	
	UsersBean usersBean=new UsersBean();
	
	String sql="SELECT * FROM user WHERE uerid= 1";
	preparedStatement=connection.prepareStatement(sql);
	resultSet=preparedStatement.executeQuery();
	
	if(resultSet!=null){
		while(resultSet.next()){
			usersBean.setUerid(resultSet.getString("uerid"));
			usersBean.setPhone(resultSet.getString("phone"));
			usersBean.setPassword(resultSet.getString("password"));
			usersBean.setNickname(resultSet.getString("nickname"));
			usersBean.setSex(resultSet.getString("sex"));
			usersBean.setImage(resultSet.getString("image"));
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
}
