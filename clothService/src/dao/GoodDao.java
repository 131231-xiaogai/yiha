package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.DBBean;
import bean.GoodBean;
import bean.UsersBean;

public class GoodDao {
	static Connection connection=DBBean.getConn();  //创建连接
	static PreparedStatement preparedStatement;     //定义表示SQL语句的变量，以参数形传入数据库操作语句来执行数据库操作
	static ResultSet resultSet;                     //定义表示查询结果的变量。
	
	public static List<GoodBean> selectAllGood() throws SQLException{
		
		List<GoodBean> goodBeans=new ArrayList<GoodBean>();
		
		String sql="select * from goods";
		preparedStatement=connection.prepareStatement(sql);
		resultSet=preparedStatement.executeQuery();
		
		if(resultSet!=null){
			while(resultSet.next()){
				GoodBean goodBean=new GoodBean();
				goodBean.setGoods_id(resultSet.getString("goods_id"));
				goodBean.setGoods_name(resultSet.getString("goods_name"));
				goodBean.setGoods_price(resultSet.getString("goods_price"));
				goodBean.setGoods_status(resultSet.getString("goods_status"));
				goodBean.setGoods_yajin(resultSet.getString("goods_yajin"));
				goodBean.setClothing_length(resultSet.getString("Clothing_length"));
				goodBean.setSleeve_length(resultSet.getString("Sleeve_length"));
				goodBean.setShoulder_width(resultSet.getString("Shoulder_width"));
				goodBean.setLeg_width(resultSet.getString("Leg_width"));
				goodBean.setTrousers_length(resultSet.getString("trousers_length"));
				goodBean.setMeterial_id(resultSet.getString("meterial_id"));
				goodBean.setSize(resultSet.getString("size"));
				goodBean.setType_id(resultSet.getString("type_id"));
				goodBean.setActivility_id(resultSet.getString("activility_id"));
				goodBean.setShop_id(resultSet.getString("shop_id"));
				goodBean.setGood_img(resultSet.getString("good_img"));
				goodBean.setShop_name(resultSet.getString("shop_name"));
				goodBean.setType_activity_id(resultSet.getString("type_activity_id"));
				goodBean.setGoods_number(resultSet.getString("goods_number"));
				goodBeans.add(goodBean);
				
			}
		}
		return goodBeans;
	}

	public static boolean addgood(
			String image,
			String goods_name,
			String goods_price,
			String goods_yajin,
			String size,
			String shop_id,
			String shop_name,
			String type_id,
			String type_activity_id
			)throws SQLException {
		Boolean flag = false;
		String sql = "INSERT INTO goods "
				+ "(good_img,"
				+ "goods_name,"
				+ "goods_price,"
				+ "goods_yajin,"
				+ "size,"
				+ "shop_id,"
				+ "shop_name,"
				+ "type_id,"
				+ "type_activity_id"
				+ ") VALUES (?,?,?,?,?,?,?,?,?)";
		//
		preparedStatement=connection.prepareStatement(sql);
		
		preparedStatement.setString(1, image);
		preparedStatement.setString(2, goods_name);
		preparedStatement.setString(3, goods_price);
		preparedStatement.setString(4, goods_yajin);
		preparedStatement.setString(5, type_id);
		preparedStatement.setString(6, shop_id);
		preparedStatement.setString(7, shop_name);
		preparedStatement.setString(8, type_id);
		preparedStatement.setString(9, type_activity_id);
		
		int row = preparedStatement.executeUpdate();
		if (row>0) {
			flag = true;
		}
		return flag;	
	}

	
	public static GoodBean select_good_by_id(String goods_id) throws SQLException {
		GoodBean goodBean = null;//
		String sql = "select * from goods where goods_id=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, goods_id);
		resultSet=preparedStatement.executeQuery();
		if (resultSet != null) {
			if (resultSet.next()) {
				goodBean =new GoodBean();
				goodBean.setGoods_name(resultSet.getString("goods_name"));
				goodBean.setGood_img(resultSet.getString("good_img"));
				goodBean.setGoods_price(resultSet.getString("goods_price"));
				goodBean.setGoods_status(resultSet.getString("goods_status"));
				goodBean.setGoods_yajin(resultSet.getString("goods_yajin"));
				goodBean.setActivility_id(resultSet.getString("activility_id"));
				goodBean.setClothing_length(resultSet.getString("Clothing_length"));
				goodBean.setLeg_width(resultSet.getString("Leg_width"));
				goodBean.setMeterial_id(resultSet.getString("meterial_id"));
				goodBean.setSleeve_length(resultSet.getString("Sleeve_length"));
				goodBean.setShoulder_width(resultSet.getString("Shoulder_width"));
				goodBean.setTrousers_length(resultSet.getString("trousers_length"));
				goodBean.setSize(resultSet.getString("size"));
				goodBean.setType_id(resultSet.getString("type_id"));
				goodBean.setShop_id(resultSet.getString("shop_id"));	
				goodBean.setShop_name(resultSet.getString("shop_name"));
				goodBean.setType_activity_id(resultSet.getString("type_activity_id"));
				goodBean.setGoods_number(resultSet.getString("goods_number"));
			}			
		}	
		return goodBean;	
	}
	//select_good_by_shopid
	
	public static List<GoodBean> select_good_by_shopid(String shop_id) throws SQLException {
		List<GoodBean> goodBeans=new ArrayList<GoodBean>();
		
		String sql="SELECT * FROM goods WHERE shop_id =?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, shop_id);
		resultSet=preparedStatement.executeQuery();
		

		if(resultSet!=null){
			while(resultSet.next()){
				GoodBean goodBean=new GoodBean();
				goodBean.setGoods_id(resultSet.getString("goods_id"));
				goodBean.setGoods_name(resultSet.getString("goods_name"));
				goodBean.setGoods_price(resultSet.getString("goods_price"));
				goodBean.setGoods_status(resultSet.getString("goods_status"));
				goodBean.setGoods_yajin(resultSet.getString("goods_yajin"));
				goodBean.setClothing_length(resultSet.getString("Clothing_length"));
				goodBean.setSleeve_length(resultSet.getString("Sleeve_length"));
				goodBean.setShoulder_width(resultSet.getString("Shoulder_width"));
				goodBean.setLeg_width(resultSet.getString("Leg_width"));
				goodBean.setTrousers_length(resultSet.getString("trousers_length"));
				goodBean.setMeterial_id(resultSet.getString("meterial_id"));
				goodBean.setSize(resultSet.getString("size"));
				goodBean.setActivility_id(resultSet.getString("activility_id"));
				//goodBean.setShop_id(resultSet.getString("shop_id"));
				goodBean.setGood_img(resultSet.getString("good_img"));
				goodBean.setType_activity_id(resultSet.getString("type_activity_id"));
				goodBean.setShop_name(resultSet.getString("shop_name"));
				goodBean.setGoods_number(resultSet.getString("goods_number"));
				goodBeans.add(goodBean);	
			}
		}
		return goodBeans;
	
	}
	
	public static List<GoodBean> select_good_by_typeid(String type_id) throws SQLException {
		List<GoodBean> goodBeans=new ArrayList<GoodBean>();
		
		String sql="SELECT * FROM goods WHERE type_id =?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, type_id);
		resultSet=preparedStatement.executeQuery();
	
		if(resultSet!=null){
			while(resultSet.next()){
				GoodBean goodBean=new GoodBean();
				goodBean.setGoods_id(resultSet.getString("goods_id"));
				goodBean.setGoods_name(resultSet.getString("goods_name"));
				goodBean.setGoods_price(resultSet.getString("goods_price"));
				goodBean.setGoods_status(resultSet.getString("goods_status"));
				goodBean.setGoods_yajin(resultSet.getString("goods_yajin"));
				goodBean.setClothing_length(resultSet.getString("Clothing_length"));
				goodBean.setSleeve_length(resultSet.getString("Sleeve_length"));
				goodBean.setShoulder_width(resultSet.getString("Shoulder_width"));
				goodBean.setLeg_width(resultSet.getString("Leg_width"));
				goodBean.setTrousers_length(resultSet.getString("trousers_length"));
				goodBean.setMeterial_id(resultSet.getString("meterial_id"));
				goodBean.setSize(resultSet.getString("size"));
				goodBean.setActivility_id(resultSet.getString("activility_id"));
				goodBean.setShop_id(resultSet.getString("shop_id"));
				goodBean.setGood_img(resultSet.getString("good_img"));
				goodBean.setType_activity_id(resultSet.getString("type_activity_id"));
				goodBean.setShop_name(resultSet.getString("shop_name"));
				goodBean.setGoods_number(resultSet.getString("goods_number"));
				goodBeans.add(goodBean);	
			}
		}
		return goodBeans;
	}
	
	public static List<GoodBean> select_good_by_type_activityid(String type_activity_id) throws SQLException {
		List<GoodBean> goodBeans=new ArrayList<GoodBean>();
		
		String sql="SELECT * FROM goods WHERE type_activity_id =?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, type_activity_id);
		resultSet=preparedStatement.executeQuery();
	
		if(resultSet!=null){
			while(resultSet.next()){
				GoodBean goodBean=new GoodBean();
				goodBean.setGoods_id(resultSet.getString("goods_id"));
				goodBean.setGoods_name(resultSet.getString("goods_name"));
				goodBean.setGoods_price(resultSet.getString("goods_price"));
				goodBean.setGoods_status(resultSet.getString("goods_status"));
				goodBean.setGoods_yajin(resultSet.getString("goods_yajin"));
				goodBean.setClothing_length(resultSet.getString("Clothing_length"));
				goodBean.setSleeve_length(resultSet.getString("Sleeve_length"));
				goodBean.setShoulder_width(resultSet.getString("Shoulder_width"));
				goodBean.setLeg_width(resultSet.getString("Leg_width"));
				goodBean.setTrousers_length(resultSet.getString("trousers_length"));
				goodBean.setMeterial_id(resultSet.getString("meterial_id"));
				goodBean.setSize(resultSet.getString("size"));
				goodBean.setActivility_id(resultSet.getString("activility_id"));
				goodBean.setShop_id(resultSet.getString("shop_id"));
				goodBean.setGood_img(resultSet.getString("good_img"));
				goodBean.setType_id(resultSet.getString("type_id"));
				goodBean.setShop_name(resultSet.getString("shop_name"));
				goodBean.setGoods_number(resultSet.getString("goods_number"));
				goodBeans.add(goodBean);	
			}
		}
		return goodBeans;
	}
	
	public static boolean deleted_goods_by_goodsid(String goods_id)throws SQLException {
		
		boolean flag=false;
		String sql = "DELETE FROM goods WHERE goods_id=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, goods_id);
		int results =preparedStatement.executeUpdate();//更新
		if(results ==1){
			flag=true;
		}
		return flag;	
		
	}
	

	public static boolean update_good_by_goodId(String goods_id,String goods_name,String goods_price,
			String goods_yajin,String goods_number,String type_id )throws SQLException  {
		
		boolean flag=false;
		String sql = "UPDATE goods "
				+ "SET goods_name=?,goods_price=?,goods_yajin=?,goods_number=?,type_id=? "
				+ "WHERE goods_id=?";
		
		preparedStatement=connection.prepareStatement(sql);
		
		preparedStatement.setString(1, goods_name);
		preparedStatement.setString(2, goods_price);
		preparedStatement.setString(3, goods_yajin);
		preparedStatement.setString(4, goods_number);
		preparedStatement.setString(5, type_id);
		preparedStatement.setString(6, goods_id);
		
		int results =preparedStatement.executeUpdate();//更新
		if(results ==1){
			flag=true;
		}
		return flag;		
	}
	
}
