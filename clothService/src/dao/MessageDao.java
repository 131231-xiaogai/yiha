package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.AddressBean;
import bean.DBBean;
import bean.GoodBean;
import bean.MssageBean;
import bean.UsersBean;


public class MessageDao {
	
	static Connection connection=DBBean.getConn();  //创建连接
	static PreparedStatement preparedStatement = null;     //定义表示SQL语句的变量，以参数形传入数据库操作语句来执行数据库操作
	static ResultSet resultSet=null;                    
	
	//数据库操作
	
	
	
//用户查询消息
public static List<MssageBean> select_message(String user_id) throws SQLException{
	
	List<MssageBean> mssageBeans=new ArrayList<MssageBean>();
	
	String sql="SELECT * FROM message WHERE userid= ?";
	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, user_id);
	resultSet=preparedStatement.executeQuery();
	
	if(resultSet!=null){
		while(resultSet.next()){
			MssageBean mssageBean =new MssageBean();
			mssageBean.setMessage_id(resultSet.getString("message_id"));
			mssageBean.setMessage_context(resultSet.getString("message_context"));
			mssageBean.setMessage_publish_time(resultSet.getString("message_publish_time"));
			mssageBean.setMessage_status(resultSet.getString("message_status"));
			mssageBean.setMessage_title(resultSet.getString("message_title"));
			mssageBean.setUser_id(resultSet.getString("user_id"));
			mssageBean.setUser_id(resultSet.getString("shop_id"));
			mssageBeans.add(mssageBean);
		}
	}
	return mssageBeans;
}

//商家查询消息
public static List<MssageBean> select_message_byshopid(String shop_id) throws SQLException{
	
	List<MssageBean> mssageBeans=new ArrayList<MssageBean>();
	
	String sql="SELECT * FROM message WHERE shop_id= ?";
	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, shop_id);
	resultSet=preparedStatement.executeQuery();
	
	if(resultSet!=null){
		while(resultSet.next()){
			MssageBean mssageBean =new MssageBean();
			mssageBean.setMessage_id(resultSet.getString("message_id"));
			mssageBean.setMessage_context(resultSet.getString("message_context"));
			mssageBean.setMessage_publish_time(resultSet.getString("message_publish_time"));
			mssageBean.setMessage_status(resultSet.getString("message_status"));
			mssageBean.setMessage_title(resultSet.getString("message_title"));
			mssageBean.setUser_id(resultSet.getString("user_id"));
			mssageBean.setUser_id(resultSet.getString("shop_id"));
			mssageBeans.add(mssageBean);
		}
	}
	return mssageBeans;
}


public static boolean insert_message(String message_title,
		String message_context,String message_publish_time,String message_status,String user_id,String shop_id )throws SQLException  {
	
	boolean flag=false;
	String sql = " insert into message"
			+ "(message_title,message_context,message_publish_time,message_status,user_id,shop_id)"
			+ " values(?,?,?,?,?,?)";

	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, message_title);
	preparedStatement.setString(2, message_context);
	preparedStatement.setString(3, message_publish_time);
	preparedStatement.setString(4, message_status);
	preparedStatement.setString(5, user_id);
	preparedStatement.setString(6,shop_id);
	int results =preparedStatement.executeUpdate();//更新
	if(results ==1){
		flag=true;
	}
	return flag;		
}



}
