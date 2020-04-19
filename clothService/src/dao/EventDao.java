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
	
	static Connection connection=DBBean.getConn();  //��������
	static PreparedStatement preparedStatement;     //�����ʾSQL���ı������Բ����δ������ݿ���������ִ�����ݿ����
	static ResultSet resultSet;                     //�����ʾ��ѯ����ı�����
	
	
	public static boolean addevent(String user_id,String evevt_title,String event_content,
			String event_date,String event_start_time,String event_finish_time)throws SQLException {
		Boolean flag = false;
		String sql = "INSERT INTO event (user_id,evevt_title,event_content,event_date,event_start_time,event_finish_time) VALUES (?,?,?,?,?,?,?)";
		//
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, user_id);
		preparedStatement.setString(2, evevt_title);
		preparedStatement.setString(3, event_content);
		preparedStatement.setString(4, event_date);
		preparedStatement.setString(5, event_start_time);
		preparedStatement.setString(6, event_finish_time);
		
		int row = preparedStatement.executeUpdate();
		if (row>0) {
			flag = true;
		}
		return flag;	
	}
	
	public static List<EventBean> select_event_byUserID(String user_id)throws SQLException {
		List<EventBean> eventBeans=new ArrayList<EventBean>();
		String sql = "select * from event where user_id=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, user_id);
		resultSet=preparedStatement.executeQuery();
		if (resultSet != null) {
			if (resultSet.next()) {
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


	

}
