package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.DBBean;

public class Shoop_carDao {
	
	static Connection connection=DBBean.getConn();  //��������
	static PreparedStatement preparedStatement = null;     //�����ʾSQL���ı������Բ����δ������ݿ���������ִ�����ݿ����
	static ResultSet resultSet=null; 

	
	public static boolean add_to_shopcar(String user_id,String good_number,String goods_id,String add_time)throws SQLException  {
		boolean flag=false;
		
		String sql = "insert into shooping_car(user_id,good_number,goods_id,add_time) values(?,?,?,?)";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, user_id );
		preparedStatement.setString(2, good_number);
		preparedStatement.setString(3, goods_id);
		preparedStatement.setString(4, add_time);
		int results =preparedStatement.executeUpdate();//����
		if(results ==1){
			flag=true;
		}
		return flag;	
	}
	
	

}
