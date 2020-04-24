package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.AddressBean;
import bean.DBBean;
import bean.OrderBean;

public class OrderDao {
	
	static Connection connection=DBBean.getConn();  //创建连接
	static PreparedStatement preparedStatement = null;     //定义表示SQL语句的变量，以参数形传入数据库操作语句来执行数据库操作
	static ResultSet resultSet=null;         
	
	public static List<OrderBean> select_order_by_UseridAndOrderStstus(String user_id,String order_status) throws SQLException{
		
		List<OrderBean> orderBeans=new ArrayList<OrderBean>();
		
		String sql="SELECT * FROM `order` WHERE user_id= ? AND order_status= ?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, user_id);
		preparedStatement.setString(2, order_status);
		resultSet=preparedStatement.executeQuery();
		
		if(resultSet!=null){
			while(resultSet.next()){
				OrderBean orderBean =new OrderBean();
				orderBean.setOrder_id(resultSet.getString("order_id"));
				orderBean.setOrder_rent_validation_time(resultSet.getString("order_rent_validation_time"));
				orderBean.setOrder_rent_finesh_time(resultSet.getString("order_rent_finesh_time"));
				orderBean.setOrder_creat_time(resultSet.getString("order_creat_time"));
				orderBean.setOrder_getgoods_time(resultSet.getString("order_getgoods_time"));
				orderBean.setOrder_status(resultSet.getString("order_status"));
				orderBean.setOrder_remark(resultSet.getString("order_remark"));
				orderBean.setUser_id(resultSet.getString("user_id"));
				orderBean.setGoods_id(resultSet.getString("goods_id"));
				orderBean.setAddress(resultSet.getString("address"));
				orderBean.setDeliver(resultSet.getString("deliver"));
				orderBean.setGood_name(resultSet.getString("good_name"));
				orderBean.setGood_number(resultSet.getString("good_number"));
				orderBean.setGood_price(resultSet.getString("good_price"));
				orderBean.setTotal_price(resultSet.getString("total_price"));
				orderBean.setGood_img(resultSet.getString("good_img"));
				
				
				orderBeans.add(orderBean);
			}
		}
		return orderBeans;
	}


}
