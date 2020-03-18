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
	
	static Connection connection=DBBean.getConn();  //��������
	static PreparedStatement preparedStatement = null;     //�����ʾSQL���ı������Բ����δ������ݿ���������ִ�����ݿ����
	static ResultSet resultSet=null;                    
	
	//���ݿ����
/*public static List<UsersBean> selectAllUser() throws SQLException{
		
		List<UsersBean> usersBeans=new ArrayList<UsersBean>();
		
		String sql="select * from user";
		preparedStatement=connection.prepareStatement(sql);
		resultSet=preparedStatement.executeQuery();
		
		if(resultSet!=null){
			while(resultSet.next()){
				UsersBean usersBean=new UsersBean();
				usersBean.setUerid(resultSet.getString("uerid"));
				usersBean.setPhone(resultSet.getString("phone"));
				usersBean.setPassword(resultSet.getString("password"));
				usersBean.setNickname(resultSet.getString("nickname"));
				usersBean.setSex(resultSet.getString("sex"));
				usersBean.setImage(resultSet.getString("image"));
				usersBean.setBalance(resultSet.getString("balance"));
				usersBean.setId_number(resultSet.getString("id_number"));
				usersBean.setRole_id(resultSet.getString("role_id"));
				usersBeans.add(usersBean);
			}
		}
		return usersBeans;
	}*/
/*public static boolean user_regiest(String reg_phonemb,String reg_bassword,String reg_roleid)throws SQLException  {
	boolean flag=false;
	
	String sql = "insert into user(phone,password,role_id) values(?,?,?)";
	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, reg_phonemb);
	preparedStatement.setString(2, reg_bassword);
	preparedStatement.setString(3, reg_roleid);
	int results =preparedStatement.executeUpdate();//����
	if(results ==1){
		flag=true;
	}
	return flag;		
}*/

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

/*public static boolean insert_user_nickname(String uerid,String nickname)throws SQLException  {
	
	boolean flag=false;
	
	String sql = "UPDATE user SET nickname=? WHERE uerid=?";
	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, nickname);
	preparedStatement.setString(2, uerid);
	int results =preparedStatement.executeUpdate();//����
	if(results ==1){
		flag=true;
	}
	return flag;		
}*/

/*public static boolean insert_user_sex(String uerid,String sex)throws SQLException  {
	
	boolean flag=false;
	
	String sql = "UPDATE user SET sex=? WHERE uerid=?";
	preparedStatement=connection.prepareStatement(sql);
	preparedStatement.setString(1, sex);
	preparedStatement.setString(2, uerid);
	int results =preparedStatement.executeUpdate();//����
	if(results ==1){
		flag=true;
	}
	return flag;		
}*/
}