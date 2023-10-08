package servlet.user;

import bean.User;
import dao.UserDao;
import util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(
                req.getParameter("username"),
                req.getParameter("password")
        );

        int uid = UserDao.login(user);

        ServletUtil.WriteJSONToResponse(resp, jsonObject -> {
            if (uid > 0) {
                // 登录成功
                jsonObject.put("msg", "success");
                jsonObject.put("uid", uid);
            } else {
                // 登录失败
                jsonObject.put("msg", "账号或密码错误");
            }
        });
    }
}
