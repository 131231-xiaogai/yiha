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
import bean.UsersBean;


public class AddressDao {
	
	static Connection connection=DBBean.getConn();  //创建连接
	static PreparedStatement preparedStatement = null;     //定义表示SQL语句的变量，以参数形传入数据库操作语句来执行数据库操作
	static ResultSet resultSet=null;                    
	
	//数据库操作

public static List<AddressBean> select_address_by_userid(String userid) throws SQLException{
	
	List<AddressBean> addressBeans=new ArrayList<AddressBean>();
	
	String sql="SELECT * FROM address WHERE userid= ?";
	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, userid);
	resultSet=preparedStatement.executeQuery();
	
	if(resultSet!=null){
		while(resultSet.next()){
			AddressBean addressBean =new AddressBean();
			addressBean.setAddress_id(resultSet.getString("address_id"));
			addressBean.setAddress_detail(resultSet.getString("address_detail"));
			addressBean.setAddress_total(resultSet.getString("address_total"));
			addressBean.setContact_name(resultSet.getString("contact_name"));
			addressBean.setContact_phone(resultSet.getString("contact_phone"));
			addressBean.setCreator_phone(resultSet.getString("creator_phone"));
			addressBean.setUserid(resultSet.getString("userid"));	
			addressBeans.add(addressBean);
		}
	}
	return addressBeans;
}
//select_address_by_addressId
public static AddressBean select_address_by_addressId(String address_id) throws SQLException{
	
	AddressBean addressBean =new AddressBean();
	
	String sql="SELECT * FROM address WHERE address_id= ?";
	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, address_id);
	resultSet=preparedStatement.executeQuery();
	
	if(resultSet!=null){
		while(resultSet.next()){
			
			addressBean.setAddress_id(resultSet.getString("address_id"));
			addressBean.setAddress_detail(resultSet.getString("address_detail"));
			addressBean.setAddress_total(resultSet.getString("address_total"));
			addressBean.setContact_name(resultSet.getString("contact_name"));
			addressBean.setContact_phone(resultSet.getString("contact_phone"));
			addressBean.setCreator_phone(resultSet.getString("creator_phone"));
			addressBean.setUserid(resultSet.getString("userid"));	
			
		}
	}
	return addressBean;
}


public static boolean update_user_address(String address_id,String contact_name,String contact_phone,
		String address_total,String address_detail )throws SQLException  {
	
	boolean flag=false;
	String sql = "UPDATE address "
			+ "SET contact_name=?,contact_phone=?,address_total=?,address_detail=? "
			+ "WHERE address_id=?";
	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, contact_name);
	preparedStatement.setString(2, contact_phone);
	preparedStatement.setString(3, address_total);
	preparedStatement.setString(4, address_detail);
	preparedStatement.setString(5, address_id);
	int results =preparedStatement.executeUpdate();//更新
	if(results ==1){
		flag=true;
	}
	return flag;		
}

public static boolean insert_user_address(String contact_name,String contact_phone,
		String address_total,String address_detail,String userid,String creator_phone )throws SQLException  {
	
	boolean flag=false;
	String sql = " insert into address(contact_name,contact_phone,address_total,address_detail,userid,creator_phone) values(?,?,?,?,?,?)";

	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, contact_name);
	preparedStatement.setString(2, contact_phone);
	preparedStatement.setString(3, address_total);
	preparedStatement.setString(4, address_detail);
	preparedStatement.setString(5, userid);
	preparedStatement.setString(6, creator_phone);
	int results =preparedStatement.executeUpdate();//更新
	if(results ==1){
		flag=true;
	}
	return flag;		
}

public static boolean delete_user_address(String address_id)throws SQLException {
	
	boolean flag=false;
	String sql = "DELETE FROM address WHERE address_id=?";
	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, address_id);
	int results =preparedStatement.executeUpdate();//更新
	if(results ==1){
		flag=true;
	}
	return flag;	
	
}

}
