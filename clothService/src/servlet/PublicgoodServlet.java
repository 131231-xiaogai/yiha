package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSON;

import dao.GoodDao;
import bean.GoodBean;
import bean.Message;

public class PublicgoodServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PublicgoodServlet() {
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

		response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        Message message = new Message();
        GoodBean goodBean = new GoodBean();
        DiskFileItemFactory factory = new DiskFileItemFactory();//工厂类
        ServletFileUpload upload = new ServletFileUpload(factory);//servlet上传文件的工具类
        List<FileItem> items = null;
        try {
            items = (List<FileItem>) upload.parseRequest(request);
        } catch (FileUploadException e1) {
            e1.printStackTrace();
        }
        Iterator<FileItem> it = items.iterator();//迭代器==》for自动循环
        if (items != null) {
            while (it.hasNext()) {
                FileItem tempitemFileItem = it.next();
                String itemNameString = tempitemFileItem.getFieldName();
                if (tempitemFileItem.isFormField()) {//是否是文本字段
                    String content = tempitemFileItem.getString("utf-8");
                    if ("good_title".equals(itemNameString)) {
                    	goodBean.setGoods_name(content);
                    } 
                } else {
                    File tempFile = new File(request.getSession().getServletContext().getRealPath("/") + "images" + File.separator
                            + new File(System.currentTimeMillis() + "_" + tempitemFileItem.getName()).getName());
                    System.out.println(tempFile);
                    if (!tempFile.getParentFile().exists()) tempFile.getParentFile().mkdirs();
                    try {
                        tempitemFileItem.write(tempFile);//保存文件/图片

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    goodBean.setGood_img(tempFile.getName());
                }

            }
        }
        try {
//            Date date = new Date();
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            feedBackBean.setFeedback_time(simpleDateFormat.format(date));
            GoodDao goodDao = new GoodDao();
            boolean addFlag = goodDao.addgood(goodBean.getGood_img());
            if (addFlag) {
            	message.setCode(200);
                message.setData(null);
                message.setMessage("添加成功");
                out.print(JSON.toJSONString(message));
            } else {
                message.setCode(-11);
                message.setData("null");
                message.setMessage("添加失败");
                out.print(JSON.toJSONString(message));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }

	
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
doGet(request, response);

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
