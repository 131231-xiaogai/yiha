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
				orderBean.setShop_id(resultSet.getString("shop_id"));
				orderBean.setOrder_code(resultSet.getString("order_code"));
				orderBean.setGoods_yajin(resultSet.getString("goods_yajin"));
				orderBean.setEvaluate_status(resultSet.getString("evaluate_status"));
				orderBeans.add(orderBean);
			}
		}
		return orderBeans;
	}

	//select_order_by_ShopidAndOrderStstus
	
	public static List<OrderBean> select_order_by_ShopidAndOrderStstus(String shop_id,String order_status) throws SQLException{
		List<OrderBean> orderBeans=new ArrayList<OrderBean>();
		String sql="SELECT * FROM `order` WHERE shop_id= ? AND order_status= ?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, shop_id);
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
				orderBean.setShop_id(resultSet.getString("shop_id"));
				orderBean.setOrder_code(resultSet.getString("order_code"));
				orderBean.setGoods_yajin(resultSet.getString("goods_yajin"));
				orderBean.setEvaluate_status(resultSet.getString("evaluate_status"));
				orderBeans.add(orderBean);
			}
		}
		return orderBeans;
	}
	//updateOrder_status
	
	public static boolean updateOrder_status(String order_id,String order_status )throws SQLException  {
		
		boolean flag=false;
		String sql = "UPDATE `order` SET order_status=? WHERE order_id=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, order_status);
		preparedStatement.setString(2, order_id);
		int results =preparedStatement.executeUpdate();//更新
		if(results ==1){
			flag=true;
		}
		return flag;		
	}
	
	//add_order //private String order_code;
	public static boolean add_order(
			String order_rent_validation_time,
			String order_rent_finesh_time,
			String order_creat_time,
			String order_getgoods_time,
			String order_status,
			String order_remark,
			String user_id,
			String goods_id,
			String address,
			String deliver,
			String good_name,
			String good_number,
			String good_price,
			String total_price,
			String good_img,
			String shop_id,
			String order_code,
			String goods_yajin,
			String evaluate_status)throws SQLException  {
		
		boolean flag=false;
		
		String sql = " insert into `order`"
				+ "(order_rent_validation_time,"
				+ "order_rent_finesh_time,"
				+ "order_creat_time,"
				+ "order_getgoods_time,"
				+ "order_status,"
				+ "order_remark,"
				+ "user_id,"
				+ "goods_id,"
				+ "address,"
				+ "deliver,"
				+ "good_name,"
				+ "good_number,"
				+ "good_price,"
				+ "total_price,"
				+ "good_img,"
				+ "shop_id,"
				+"order_code,"
				+ "goods_yajin,"
				+ "evaluate_status) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		//
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, order_rent_validation_time);
		preparedStatement.setString(2, order_rent_finesh_time);
		preparedStatement.setString(3, order_creat_time);
		preparedStatement.setString(4, order_getgoods_time);
		preparedStatement.setString(5, order_status);
		preparedStatement.setString(6, order_remark);
		preparedStatement.setString(7, user_id);
		preparedStatement.setString(8, goods_id);
		preparedStatement.setString(9, address);
		preparedStatement.setString(10, deliver);
		preparedStatement.setString(11, good_name);
		preparedStatement.setString(12, good_number);
		preparedStatement.setString(13, good_price);
		preparedStatement.setString(14, total_price);
		preparedStatement.setString(15, good_img);
		preparedStatement.setString(16, shop_id);
		preparedStatement.setString(17, order_code);
		preparedStatement.setString(18, goods_yajin);
		preparedStatement.setString(19, evaluate_status);
		int results =preparedStatement.executeUpdate();//更新
		if(results ==1){
			flag=true;
		}
		return flag;		
	}

	//selece_order_totalprice
	public static List<OrderBean> selece_order_totalprice(String shop_id) throws SQLException{
		List<OrderBean> orderBeans=new ArrayList<OrderBean>();
		String sql="SELECT * FROM `order` WHERE shop_id= ?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, shop_id);
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
				orderBean.setShop_id(resultSet.getString("shop_id"));
				orderBean.setOrder_code(resultSet.getString("order_code"));
				orderBean.setGoods_yajin(resultSet.getString("goods_yajin"));
				orderBean.setEvaluate_status(resultSet.getString("evaluate_status"));
				orderBeans.add(orderBean);
			}
		}
		return orderBeans;
	}
	//select_order_by_UseridAndOrderStstus_evaluate_status
	public static List<OrderBean> select_order_by_UseridAndOrderStstus_evaluate_status(String user_id,String order_status,String evaluate_status) throws SQLException{
		List<OrderBean> orderBeans=new ArrayList<OrderBean>();
		String sql="SELECT * FROM `order` WHERE user_id= ? AND order_status= ? and evaluate_status=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, user_id);
		preparedStatement.setString(2, order_status);
		preparedStatement.setString(3, evaluate_status);
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
				orderBean.setShop_id(resultSet.getString("shop_id"));
				orderBean.setOrder_code(resultSet.getString("order_code"));
				orderBean.setGoods_yajin(resultSet.getString("goods_yajin"));
				orderBean.setEvaluate_status(resultSet.getString("evaluate_status"));
				orderBeans.add(orderBean);
			}
		}
		return orderBeans;
	}
	
	//updata_Order_evaluateStatus
public static boolean updata_Order_evaluateStatus(String order_id,String evaluate_status )throws SQLException  {
		
		boolean flag=false;
		String sql = "UPDATE `order` SET evaluate_status=? WHERE order_id=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, evaluate_status);
		preparedStatement.setString(2, order_id);
		int results =preparedStatement.executeUpdate();//更新
		if(results ==1){
			flag=true;
		}
		return flag;		
	}
public static List<OrderBean> select_allOrder_byUserid(String user_id) throws SQLException{
	List<OrderBean> orderBeans=new ArrayList<OrderBean>();
	String sql="SELECT * FROM `order` WHERE user_id= ? ";
	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, user_id);
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
			orderBean.setShop_id(resultSet.getString("shop_id"));
			orderBean.setOrder_code(resultSet.getString("order_code"));
			orderBean.setGoods_yajin(resultSet.getString("goods_yajin"));
			orderBean.setEvaluate_status(resultSet.getString("evaluate_status"));
			orderBeans.add(orderBean);
		}
	}
	return orderBeans;
}

public static List<OrderBean> select_allOrder_byShopId(String shop_id) throws SQLException{
	List<OrderBean> orderBeans=new ArrayList<OrderBean>();
	String sql="SELECT * FROM `order` WHERE shop_id= ? ";
	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, shop_id);
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
			orderBean.setShop_id(resultSet.getString("shop_id"));
			orderBean.setOrder_code(resultSet.getString("order_code"));
			orderBean.setGoods_yajin(resultSet.getString("goods_yajin"));
			orderBean.setEvaluate_status(resultSet.getString("evaluate_status"));
			orderBeans.add(orderBean);
		}
	}
	return orderBeans;
}
	//select_shopOrder_byMonth
public static List<OrderBean> select_shopOrder_byMonth(String shop_id,String order_creat_time) throws SQLException{
	List<OrderBean> orderBeans=new ArrayList<OrderBean>();
	String sql="SELECT * FROM `order` WHERE  shop_id =? AND order_creat_time LIKE ? ";

	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, shop_id);
	preparedStatement.setString(2, "%"+order_creat_time+"%");
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
			orderBean.setShop_id(resultSet.getString("shop_id"));
			orderBean.setOrder_code(resultSet.getString("order_code"));
			orderBean.setGoods_yajin(resultSet.getString("goods_yajin"));
			orderBean.setEvaluate_status(resultSet.getString("evaluate_status"));
			orderBeans.add(orderBean);
		}
	}
	return orderBeans;
}
//select_order_byShopidAndOrderStstus_likeName
public static List<OrderBean> select_order_byShopidAndOrderStstus_likeName(String shop_id,String order_status,String good_name) throws SQLException{
	List<OrderBean> orderBeans=new ArrayList<OrderBean>();
	String sql="SELECT * FROM `order` WHERE shop_id= ? AND order_status= ? AND good_name LIKE ?";
	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, shop_id);
	preparedStatement.setString(2, order_status);
	preparedStatement.setString(3, "%"+good_name+"%");
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
			orderBean.setShop_id(resultSet.getString("shop_id"));
			orderBean.setOrder_code(resultSet.getString("order_code"));
			orderBean.setGoods_yajin(resultSet.getString("goods_yajin"));
			orderBean.setEvaluate_status(resultSet.getString("evaluate_status"));
			orderBeans.add(orderBean);
		}
	}
	return orderBeans;
}
//select_order_byUseridAndOrderStstus_likeName
public static List<OrderBean> select_order_byUseridAndOrderStstus_likeName(String user_id,String order_status,String good_name) throws SQLException{
	List<OrderBean> orderBeans=new ArrayList<OrderBean>();
	String sql="SELECT * FROM `order` WHERE user_id= ? AND order_status= ? AND good_name LIKE ?";
	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, user_id);
	preparedStatement.setString(2, order_status);
	preparedStatement.setString(3, "%"+good_name+"%");
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
			orderBean.setShop_id(resultSet.getString("shop_id"));
			orderBean.setOrder_code(resultSet.getString("order_code"));
			orderBean.setGoods_yajin(resultSet.getString("goods_yajin"));
			orderBean.setEvaluate_status(resultSet.getString("evaluate_status"));
			orderBeans.add(orderBean);
		}
	}
	return orderBeans;
}
//select_allOrder_byShopIdandLikename
public static List<OrderBean> select_allOrder_byShopIdandLikename(String shop_id,String good_name) throws SQLException{
	List<OrderBean> orderBeans=new ArrayList<OrderBean>();
	String sql="SELECT * FROM `order` WHERE shop_id= ? AND good_name LIKE ?";
	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, shop_id);
	preparedStatement.setString(2, "%"+good_name+"%");
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
			orderBean.setShop_id(resultSet.getString("shop_id"));
			orderBean.setOrder_code(resultSet.getString("order_code"));
			orderBean.setGoods_yajin(resultSet.getString("goods_yajin"));
			orderBean.setEvaluate_status(resultSet.getString("evaluate_status"));
			orderBeans.add(orderBean);
		}
	}
	return orderBeans;
}
//select_order_byUseridAndlikeName
public static List<OrderBean> select_order_byUseridAndlikeName(String user_id,String good_name) throws SQLException{
	List<OrderBean> orderBeans=new ArrayList<OrderBean>();
	String sql="SELECT * FROM `order` WHERE user_id= ? AND good_name LIKE ?";
	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, user_id);
	preparedStatement.setString(2, "%"+good_name+"%");
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
			orderBean.setShop_id(resultSet.getString("shop_id"));
			orderBean.setOrder_code(resultSet.getString("order_code"));
			orderBean.setGoods_yajin(resultSet.getString("goods_yajin"));
			orderBean.setEvaluate_status(resultSet.getString("evaluate_status"));
			orderBeans.add(orderBean);
		}
	}
	return orderBeans;
}

}
