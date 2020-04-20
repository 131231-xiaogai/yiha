package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.AddressBean;
import bean.DBBean;
import bean.EventBean;
import bean.GoodBean;

public class EventDao {
	
	static Connection connection=DBBean.getConn();  //创建连接
	static PreparedStatement preparedStatement;     //定义表示SQL语句的变量，以参数形传入数据库操作语句来执行数据库操作
	static ResultSet resultSet;                     //定义表示查询结果的变量。
	
	
	
	
	public static List<EventBean> select_event_byUserID(String user_id)throws SQLException {
		
		List<EventBean> eventBeans=new ArrayList<EventBean>();
		
		String sql = "SELECT * FROM event WHERE user_id= ?";
		
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, user_id);
		resultSet=preparedStatement.executeQuery();
		if (resultSet!=null) {
			while(resultSet.next()) {
				EventBean eventBean =new EventBean();
				eventBean.setEvent_id(resultSet.getString("event_id"));
				eventBean.setUser_id(resultSet.getString("user_id"));
				eventBean.setEvevt_title(resultSet.getString("evevt_title"));
				eventBean.setEvent_content(resultSet.getString("event_content"));
				eventBean.setEvent_start_time(resultSet.getString("event_start_time"));
				eventBean.setEvent_finish_time(resultSet.getString("event_finish_time"));
				eventBean.setEvent_date(resultSet.getString("event_date"));
				eventBeans.add(eventBean);
			}			
		}	
		return eventBeans;	
	}
	
	
	
	//update_event_byEventID
	public static boolean update_event_byEventID(
			String event_id,
			String evevt_title,
			String event_content,
			String event_start_time,
			String event_finish_time )throws SQLException  {
		
		boolean flag=false;
		String sql = "UPDATE event "
				+ "SET evevt_title=?,event_content=?,event_start_time=?,event_finish_time=? "
				+ "WHERE event_id=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, evevt_title);
		preparedStatement.setString(2, event_content);
		preparedStatement.setString(3, event_start_time);
		preparedStatement.setString(4, event_finish_time);
		preparedStatement.setString(5, event_id);
		
		int results =preparedStatement.executeUpdate();//更新
		if(results ==1){
			flag=true;
		}
		return flag;		
	}

	
	
	//deletede_event_byEventID
	
	public static boolean deletede_event_byEventID(String event_id)throws SQLException {
		
		boolean flag=false;
		String sql = "DELETE FROM event WHERE event_id=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, event_id);
		int results =preparedStatement.executeUpdate();//更新
		if(results ==1){
			flag=true;
		}
		return flag;	
		
	}
	
	//add_event_byEventID
	public static boolean add_event(
			String user_id,
			String event_date,
			String evevt_title,
			String event_content,
			String event_start_time,
			String event_finish_time )throws SQLException  {
		
		boolean flag=false;
		String sql = " insert into event"
				+ "(user_id,event_date,evevt_title,event_content,event_start_time,event_finish_time) "
				+ "values(?,?,?,?,?,?)";

		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, user_id);
		preparedStatement.setString(2, event_date);
		preparedStatement.setString(3, evevt_title);
		preparedStatement.setString(4, event_content);
		preparedStatement.setString(5, event_start_time);
		preparedStatement.setString(6, event_finish_time);
		int results =preparedStatement.executeUpdate();//更新
		if(results ==1){
			flag=true;
		}
		return flag;		
	}
	

}
