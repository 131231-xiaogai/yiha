package servlet.feedback;

import bean.FeedBackBean;
import bean.Message;
import com.alibaba.fastjson.JSON;
import dao.FeedBackDao;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by coder
 * 提交反馈
 */
@WebServlet("/PublishFeedBackServlet")
public class PublishFeedBackServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        Message message = new Message();
        FeedBackBean feedBackBean = new FeedBackBean();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = null;
        try {
            items = (List<FileItem>) upload.parseRequest(request);
        } catch (FileUploadException e1) {
            e1.printStackTrace();
        }
        Iterator<FileItem> it = items.iterator();
        if (items != null) {
            while (it.hasNext()) {
                FileItem tempitemFileItem = it.next();
                String itemNameString = tempitemFileItem.getFieldName();
                if (tempitemFileItem.isFormField()) {
                    String content = tempitemFileItem.getString("utf-8");
                    if ("feedback_title".equals(itemNameString)) {
                        feedBackBean.setFeedback_title(content);
                    } else if ("feedback_content".equals(itemNameString)) {
                        feedBackBean.setFeedback_content(content);
                    } else if ("from_user_id".equals(itemNameString)) {
                        feedBackBean.setFrom_user_id(content);
                    } else if ("to_user_id".equals(itemNameString)) {
                        feedBackBean.setTo_user_id(content);
                    } else if ("to_feedback_id".equals(itemNameString)) {
                        feedBackBean.setTo_feedback_id(content);
                    }
                } else {
                    File tempFile = new File(request.getSession().getServletContext().getRealPath("/") + "image" + File.separator
                            + new File(System.currentTimeMillis() + "_" + tempitemFileItem.getName()).getName());
                    if (!tempFile.getParentFile().exists()) tempFile.getParentFile().mkdirs();
                    try {
                        tempitemFileItem.write(tempFile);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    feedBackBean.setFeedback_img(tempFile.getName());
                }

            }
        }
        try {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            feedBackBean.setFeedback_time(simpleDateFormat.format(date));
            FeedBackDao feedBackDao = new FeedBackDao();
            boolean addFlag = feedBackDao.add(feedBackBean.getFeedback_title(), feedBackBean.getFeedback_content(), feedBackBean.getFeedback_img(),
                    feedBackBean.getFeedback_time(), feedBackBean.getFrom_user_id(), feedBackBean.getTo_user_id(), feedBackBean.getTo_feedback_id());
            if (addFlag) {
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
