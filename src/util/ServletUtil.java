package util;

import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;

/**
 * servlet操作
 * */
public class ServletUtil {
    /**
     * 为resp写入json
     * */
    public static void WriteJSONToResponse(HttpServletResponse resp, IWriteJSONToResponse handler) {
        try {
            JSONObject jsonObject = new JSONObject();
            handler.invoke(jsonObject);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getOutputStream().write(String.valueOf(jsonObject).getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface IWriteJSONToResponse {
        public void invoke(JSONObject jsonObject) throws JSONException;
    }
}


