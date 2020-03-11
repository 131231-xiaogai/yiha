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
	
	static Connection connection=DBBean.getConn();  //��������
	static PreparedStatement preparedStatement;     //�����ʾSQL���ı������Բ����δ������ݿ���������ִ�����ݿ����
	static ResultSet resultSet;                     //�����ʾ��ѯ����ı�����
	
	//���ݿ����
public static List<UsersBean> selectAllUser() throws SQLException{
		
		List<UsersBean> usersBeans=new ArrayList<UsersBean>();
		
		String sql="select * from user";
		preparedStatement=connection.prepareStatement(sql);
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

}
