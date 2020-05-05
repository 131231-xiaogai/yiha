package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.ConsumerBean;
import bean.DBBean;
import bean.UsersBean;

public class ConsumerDao {
	
	static Connection connection=DBBean.getConn();  //创建连接
	static PreparedStatement preparedStatement = null;     //定义表示SQL语句的变量，以参数形传入数据库操作语句来执行数据库操作
	static ResultSet resultSet=null;  
	
	//Insert_user_bodyData
	public static boolean insert_user_bodyData(
			String user_id,
			String weight,
			String height,
			String bust,
			String the_waist,
			String hipline,
			String shoulder_width,
			String clothing_length,
			String trousers_length
			)throws SQLException  {
		boolean flag=false;
		String sql = " insert into consumer("
				+ "user_id,"
				+ "weight,"
				+ "height,"
				+ "bust,"
				+ "the_waist,"
				+ "hipline,"
				+ "shoulder_width,"
				+ "clothing_length,"
				+ "trousers_length"
				+ ") "
				+ "values(?,?,?,?,?,?,?,?,?)";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, user_id);
		preparedStatement.setString(2, weight);
		preparedStatement.setString(3, height);
		preparedStatement.setString(4, bust);
		preparedStatement.setString(5, the_waist);
		preparedStatement.setString(6, hipline);
		preparedStatement.setString(7, shoulder_width);
		preparedStatement.setString(8, clothing_length);
		preparedStatement.setString(9, trousers_length);
		int results =preparedStatement.executeUpdate();//更新
		if(results ==1){
			flag=true;
		}
		return flag;		
	}
	
	//Select_Bodydata_byuserId
	public static ConsumerBean select_Bodydata_byuserId(String user_id) throws SQLException{
		
		ConsumerBean consumerBean=new ConsumerBean();
		
		String sql="SELECT * FROM consumer WHERE user_id= ?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, user_id);
		resultSet=preparedStatement.executeQuery();
		
		if(resultSet!=null){
			while(resultSet.next()){
				consumerBean.setUser_id(resultSet.getString("user_id"));
				consumerBean.setWeight(resultSet.getString("weight"));
				consumerBean.setHeight(resultSet.getString("height"));
				consumerBean.setBust(resultSet.getString("bust"));
				consumerBean.setThe_waist(resultSet.getString("the_waist"));
				consumerBean.setHipline(resultSet.getString("hipline"));
				consumerBean.setShoulder_width(resultSet.getString("shoulder_width"));
				consumerBean.setClothing_length(resultSet.getString("clothing_length"));
				consumerBean.setTrousers_length(resultSet.getString("trousers_length"));			
			}
		}
		return consumerBean;
	}
	
	public static boolean update_user_bodyData(
			String user_id,
			String weight,
			String height,
			String bust,
			String the_waist,
			String hipline,
			String shoulder_width,
			String clothing_length,
			String trousers_length
			)throws SQLException  {
		
		boolean flag=false;
		
		String sql = "UPDATE consumer SET weight=?,height=?,bust=?,the_waist=?,hipline=?,shoulder_width=?,clothing_length=?,trousers_length=? WHERE user_id=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, weight);
		preparedStatement.setString(2, height);
		preparedStatement.setString(3, bust);
		preparedStatement.setString(4, the_waist);
		preparedStatement.setString(5, hipline);
		preparedStatement.setString(6, shoulder_width);
		preparedStatement.setString(7, clothing_length);
		preparedStatement.setString(8, trousers_length);
		preparedStatement.setString(9, user_id);
		int results =preparedStatement.executeUpdate();//更新
		if(results ==1){
			flag=true;
		}
		return flag;		
	}

}
