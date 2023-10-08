package servlet.user;

import bean.User;
import dao.UserDao;
import util.ServletUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 封装信息
        User user = new User(
                req.getParameter("username"),
                req.getParameter("password")
        );

        // 注册操作
        int res = UserDao.register(user);

        // 写入json
        ServletUtil.WriteJSONToResponse(resp, jsonObject -> {
            if (res > 0) {
                // 注册成功,跳转登录界面
                jsonObject.put("msg", "success");
            } else {
                // 注册失败,继续注册界面
                jsonObject.put("msg", "error");
            }
        });
    }
}
