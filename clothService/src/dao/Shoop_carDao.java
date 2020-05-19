package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.DBBean;
import bean.GoodBean;
import bean.Shooping_carBean;

public class Shoop_carDao {
	
	static Connection connection=DBBean.getConn();  //创建连接
	static PreparedStatement preparedStatement = null;     //定义表示SQL语句的变量，以参数形传入数据库操作语句来执行数据库操作
	static ResultSet resultSet=null; 

	
	public static boolean add_to_shopcar(String user_id,String good_number,String goods_id,
			String shop_id,String good_name,String good_price,String good_img,String shop_name,
			String add_time,String cancle_time,String shop_car_status,String goods_yajin,String good_size)throws SQLException  {
		boolean flag=false;
		
		String sql = "insert into shooping_car(user_id,good_number,goods_id,shop_id,good_name,"
				+ "good_price,good_img,shop_name,add_time,cancle_time,shop_car_status,goods_yajin,good_size) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, user_id );
		preparedStatement.setString(2, good_number);
		preparedStatement.setString(3, goods_id);
		preparedStatement.setString(4, shop_id);
		preparedStatement.setString(5, good_name );
		preparedStatement.setString(6, good_price);
		preparedStatement.setString(7, good_img);
		preparedStatement.setString(8, shop_name);
		preparedStatement.setString(9, add_time );
		preparedStatement.setString(10, cancle_time);
		preparedStatement.setString(11, shop_car_status);
		preparedStatement.setString(12, goods_yajin);
		preparedStatement.setString(13, good_size);
		int results =preparedStatement.executeUpdate();//更新 good_size
		if(results ==1){
			flag=true;
		}
		return flag;	
	}
	//加购物车状态条件
	public static List<Shooping_carBean> select_shopcar_by_userid(String user_id,String shop_car_status)throws SQLException {
		
		List<Shooping_carBean> shooping_carBeans=new ArrayList<Shooping_carBean>();
		
		String sql="SELECT * FROM shooping_car WHERE user_id =? and shop_car_status=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, user_id);
		preparedStatement.setString(2, shop_car_status);
		resultSet=preparedStatement.executeQuery();
		
		if(resultSet!=null){
			while(resultSet.next()){
				Shooping_carBean shooping_carBean=new Shooping_carBean();
				shooping_carBean.setGood_img(resultSet.getString("good_img"));
				shooping_carBean.setGood_name(resultSet.getString("good_name"));
				shooping_carBean.setGood_price(resultSet.getString("good_price"));
				shooping_carBean.setGood_number(resultSet.getString("good_number"));
				shooping_carBean.setGoods_id(resultSet.getString("goods_id"));
				shooping_carBean.setShop_id(resultSet.getString("shop_id"));
				shooping_carBean.setShop_name(resultSet.getString("shop_name"));
				shooping_carBean.setId(resultSet.getString("id"));
				shooping_carBean.setUser_id(resultSet.getString("user_id"));
				shooping_carBean.setShop_car_status(resultSet.getString("shop_car_status"));
				shooping_carBean.setGoods_yajin(resultSet.getString("goods_yajin"));
				shooping_carBean.setGood_size(resultSet.getString("good_size"));
				shooping_carBeans.add(shooping_carBean);	
			}
		}
		return shooping_carBeans;
		
	}
	
	public static boolean updateShopCardCount(String id, String good_number)
			throws SQLException {
		boolean flag = false;
		String sql = "update shooping_car set good_number=? where id =?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, good_number);
		preparedStatement.setString(2, id);
		int result = preparedStatement.executeUpdate();
		if (result > 0) {
			flag = true;
		}

		return flag;

	}
	
	
	//update_shopcarStatus
	
	public static boolean update_shopcarStatus(String id,String  shop_car_status)throws SQLException  {
		boolean flag=false;
		String sql = "UPDATE shooping_car "
				+ "SET shop_car_status=?"
				+ "WHERE id=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, shop_car_status);
		preparedStatement.setString(2, id);
		int results =preparedStatement.executeUpdate();//更新
		if(results ==1){
			flag=true;
		}
		return flag;	
		}
		
	
}
