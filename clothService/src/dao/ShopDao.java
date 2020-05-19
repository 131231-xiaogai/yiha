package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.DBBean;
import bean.GoodBean;
import bean.OrderBean;
import bean.Shooping_carBean;
import bean.ShopBean;
import bean.UsersBean;

public class ShopDao {
	static Connection connection=DBBean.getConn();  //创建连接
	static PreparedStatement preparedStatement = null;     //定义表示SQL语句的变量，以参数形传入数据库操作语句来执行数据库操作
	static ResultSet resultSet=null; 
	
	
	
	public static ShopBean select_shop_by_userid(String user_id)throws SQLException {
			ShopBean shopBean = null;
			String sql="SELECT * FROM shop WHERE user_id =?";
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, user_id);
			resultSet=preparedStatement.executeQuery();
			if(resultSet!=null){
				while(resultSet.next()){
					shopBean =new ShopBean();
					shopBean.setShop_id(resultSet.getString("shop_id"));
					shopBean.setShop_name(resultSet.getString("shop_name"));
					shopBean.setShop_dresss(resultSet.getString("shop_dresss"));
					shopBean.setShop_score(resultSet.getString("shop_score"));
					shopBean.setShop_phone(resultSet.getString("shop_phone"));
					shopBean.setShop_regist_time(resultSet.getString("shop_regist_time"));
					shopBean.setUser_id(resultSet.getString("user_id"));	
				}
			}
			return shopBean;
		}
	
	public static boolean regiest_shop(String shop_name,String shop_dresss,String shop_phone,
			String shop_regist_time,String user_id)throws SQLException {
		boolean flag=false;
		String sql = "insert into shop(shop_name,shop_dresss,shop_phone,shop_regist_time,user_id) values(?,?,?,?,?)";
		
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, shop_name);
		preparedStatement.setString(2, shop_dresss);
		preparedStatement.setString(3, shop_phone);
		preparedStatement.setString(4, shop_regist_time);
		preparedStatement.setString(5, user_id);
		int results =preparedStatement.executeUpdate();//更新
		if(results ==1){
			flag=true;
		}
		return flag;	
	}
	//select_user_byShopId
	public static ShopBean select_user_by_shopID(String shop_id) throws SQLException{
		
		ShopBean shopBean=new ShopBean();
		
		String sql="SELECT * FROM shop WHERE shop_id= ?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, shop_id);
		resultSet=preparedStatement.executeQuery();
		
		if(resultSet!=null){
			while(resultSet.next()){
				shopBean.setShop_id(resultSet.getString("shop_id"));
				shopBean.setShop_name(resultSet.getString("shop_name"));
				shopBean.setShop_dresss(resultSet.getString("shop_dresss"));
				shopBean.setShop_score(resultSet.getString("shop_score"));
				shopBean.setShop_phone(resultSet.getString("shop_phone"));
				shopBean.setShop_regist_time(resultSet.getString("shop_regist_time"));
				shopBean.setUser_id(resultSet.getString("user_id"));		
				//usersBeans.add(usersBean);	
			}
		}
		return shopBean;
	}
	//select_shop_likeaddress
public static List<ShopBean> select_shop_likeaddress(String shop_dresss) throws SQLException{
		
	List<ShopBean> shopBeans=new ArrayList<ShopBean>();
		
		String sql="SELECT * FROM shop WHERE shop_dresss LIKE ?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, "%"+shop_dresss+"%");
		resultSet=preparedStatement.executeQuery();
		
		if(resultSet!=null){
			while(resultSet.next()){
				ShopBean shopBean =new ShopBean();
				shopBean.setShop_id(resultSet.getString("shop_id"));
				shopBean.setShop_name(resultSet.getString("shop_name"));
				shopBean.setShop_dresss(resultSet.getString("shop_dresss"));
				shopBean.setShop_score(resultSet.getString("shop_score"));
				shopBean.setShop_phone(resultSet.getString("shop_phone"));
				shopBean.setShop_regist_time(resultSet.getString("shop_regist_time"));
				shopBean.setUser_id(resultSet.getString("user_id"));		
				shopBeans.add(shopBean);	
			}
		}
		return shopBeans;
	}
	
		
	
}
