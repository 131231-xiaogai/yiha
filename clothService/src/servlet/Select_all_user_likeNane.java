package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.TMessage;
import bean.UsersBean;

import com.alibaba.fastjson.JSON;

import dao.UsersDao;

public class Select_all_user_likeNane extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Select_all_user_likeNane() {
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
		
		String role_id=request.getParameter("role_id");
		String role_status=request.getParameter("role_status");
		String nickname=new String(request.getParameter("nickname").getBytes("ISO8859-1"),"UTF-8");
		System.out.println("��ѯ���û���ɫΪ"+role_id);

		TMessage<List<UsersBean>> tMessage=new TMessage(); 
		
		
		PrintWriter printWriter=response.getWriter();
		try {
			List<UsersBean> usersBeans=UsersDao.select_all_user_likeNane(role_id,role_status,nickname);
			tMessage.setCode(200);
			tMessage.setMessage("��ѯ�ɹ�");
			tMessage.setData(usersBeans);   //���Ҫ���ظ�ǰ����ʾ������
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			tMessage.setCode(-11);
			tMessage.setMessage("��ѯʧ��");
			tMessage.setData(null);
			e.printStackTrace();
		}
		
		printWriter.print(JSON.toJSONString(tMessage));
		
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
