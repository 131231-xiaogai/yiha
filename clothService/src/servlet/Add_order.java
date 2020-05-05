package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Message;

import com.alibaba.fastjson.JSON;

import dao.EventDao;
import dao.OrderDao;

public class Add_order extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Add_order() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				doPost(request, response);
	}


	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;utf-8");
		PrintWriter out = response.getWriter();
		Message me=new Message();
		
		try {
			String order_rent_validation_time=request.getParameter("order_rent_validation_time");
			String order_rent_finesh_time=request.getParameter("order_rent_finesh_time");
			String order_creat_time=request.getParameter("order_creat_time");
			String order_getgoods_time=request.getParameter("order_getgoods_time");
			String order_status=request.getParameter("order_status");
			String order_remark=request.getParameter("order_remark");
			String user_id=request.getParameter("user_id");
			String goods_id=request.getParameter("goods_id");
			String address=request.getParameter("address");
			String order_code=request.getParameter("order_code");
			String shop_id=request.getParameter("shop_id");
			String good_img=request.getParameter("good_img");
			String total_price=request.getParameter("total_price");
			String good_price=request.getParameter("good_price");
			String good_number=request.getParameter("good_number");
			String goods_yajin =request.getParameter("goods_yajin");
			
			String good_name=new String(request.getParameter("good_name").getBytes("ISO8859-1"),"UTF-8");
			
			String deliver=request.getParameter("deliver");
			if(OrderDao.add_order(order_rent_validation_time, order_rent_finesh_time
					, order_creat_time, order_getgoods_time, order_status, order_remark, user_id, goods_id, address
					, deliver, good_name, good_number, good_price, total_price, good_img, shop_id, order_code,goods_yajin)){
				me.setCode(200);
				me.setMessage("保存成功！");
				me.setData(null);
			}else{
				me.setCode(-11);//返回给前端程序代码
				me.setMessage("保存失败，请重试。");//返回给用户看
				me.setData(null);
				
			}
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		out.println(JSON.toJSONString(me));
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
