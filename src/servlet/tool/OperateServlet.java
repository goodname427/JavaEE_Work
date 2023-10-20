package servlet.tool;

import util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tool/operate")
public class OperateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int state = 0;
        String error = "";
        int res = 0;
        try {
            int op1 = Integer.valueOf(req.getParameter("op1"));
            int op2 = Integer.valueOf(req.getParameter("op2"));
            char operator = req.getParameter("operator").charAt(0);

            switch (operator) {
                case '+':
                    res = op1 + op2;
                    break;
                default:
                    throw new Exception("无法识别的运算符 " + operator);
            }

        } catch (Exception e) {
            state = -1;
            error = e.getMessage();
        }

        int finalState = state;
        String finalError = error;
        int finalRes = res;
        ServletUtil.WriteJSONToResponse(resp, jsonObject -> {
            if (finalState == 0)
            {
                jsonObject.put("msg", "success");
                jsonObject.put("result", finalRes);
            }else{
                jsonObject.put("msg", finalError);
            }
        });
    }
}
