package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.DBBean;
import bean.GoodBean;

public class GoodDao {
	static Connection connection=DBBean.getConn();
	static PreparedStatement preparedStatement;
	static ResultSet resultSet;
	
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
				goodBean.setSize_id(resultSet.getString("size_id"));
				goodBean.setType_id(resultSet.getString("type_id"));
				goodBean.setActivility_id(resultSet.getString("activility_id"));
				goodBean.setShop_id(resultSet.getString("shop_id"));
				
				goodBeans.add(goodBean);
			
			}
		}
		return goodBeans;
	}


}
