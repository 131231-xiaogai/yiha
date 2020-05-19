package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.AddressBean;
import bean.DBBean;
import bean.EvaluateBean;

public class EvaluateDao {
	static Connection connection=DBBean.getConn();  //创建连接
	static PreparedStatement preparedStatement = null;     //定义表示SQL语句的变量，以参数形传入数据库操作语句来执行数据库操作
	static ResultSet resultSet=null;
	
	//add_order_p_content
	public static boolean add_order_p_content(
			String p_content,
			String order_id,
			String good_id,
			String user_id,
			String shop_id
			)throws SQLException  {
		
		boolean flag=false;
		String sql = " insert into evaluate("
				+ "p_content,"
				+ "order_id,"
				+ "good_id,"
				+ "user_id,"
				+ "shop_id"
				+ ") values(?,?,?,?,?)";

		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, p_content);
		preparedStatement.setString(2, order_id);
		preparedStatement.setString(3, good_id);
		preparedStatement.setString(4, user_id);
		preparedStatement.setString(5, shop_id);
		int results =preparedStatement.executeUpdate();//更新
		if(results ==1){
			flag=true;
		}
		return flag;		
	}
	//selece_shop_evaluate
	
	public static List<EvaluateBean> selece_shop_evaluate(String shop_id) throws SQLException{
		
		List<EvaluateBean> evaluateBeans=new ArrayList<EvaluateBean>();
		
		String sql="SELECT * FROM evaluate WHERE shop_id= ?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, shop_id);
		resultSet=preparedStatement.executeQuery();
		
		if(resultSet!=null){
			while(resultSet.next()){
				EvaluateBean evaluateBean =new EvaluateBean();
				evaluateBean.setId(resultSet.getString("id"));
				evaluateBean.setP_content(resultSet.getString("p_content"));
				evaluateBean.setOrder_id(resultSet.getString("order_id"));
				evaluateBean.setGood_id(resultSet.getString("good_id"));
				evaluateBean.setUser_id(resultSet.getString("user_id"));
				evaluateBean.setShop_id(resultSet.getString("shop_id"));
				
				evaluateBeans.add(evaluateBean);
			}
		}
		return evaluateBeans;
	}
	
	//select_evaluate_by_orderid
	public static EvaluateBean select_evaluate_by_orderid(String order_id) throws SQLException{
		
		EvaluateBean evaluateBean =new EvaluateBean();
		
		String sql="SELECT * FROM evaluate WHERE order_id= ?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, order_id);
		resultSet=preparedStatement.executeQuery();
		
		if(resultSet!=null){
			while(resultSet.next()){
				evaluateBean.setId(resultSet.getString("id"));
				evaluateBean.setP_content(resultSet.getString("p_content"));
				evaluateBean.setOrder_id(resultSet.getString("order_id"));
				evaluateBean.setGood_id(resultSet.getString("good_id"));
				evaluateBean.setUser_id(resultSet.getString("user_id"));
				evaluateBean.setShop_id(resultSet.getString("shop_id"));		
			}
		}
		return evaluateBean;
	}


}
