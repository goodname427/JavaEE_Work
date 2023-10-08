package dao;

import bean.User;
import util.JDBCUtil;

public class UserDao {
    /**
     * 注册
     *
     * @return 注册成功时返回结果>0, 否则返回0
     */
    public static int register(User user) {
        return JDBCUtil.query("INSERT INTO User(username, password) VALUES(?, ?)", resource -> {
            resource.statement.setString(1, user.username);
            resource.statement.setString(2, user.password);
            resource.intResult = resource.statement.executeUpdate();
        }).intResult;
    }

    /**
     * 登录
     *
     * @return 登录成功时返回用户id，否则返回0
     */
    public static int login(User user) {
        return JDBCUtil.query("SELECT uid FROM User WHERE username = ? AND password = ?", resource -> {
            resource.statement.setString(1, user.username);
            resource.statement.setString(2, user.password);
            resource.resultSet = resource.statement.executeQuery();
            resource.intResult = 0;
            if (resource.resultSet.next())
                resource.intResult = resource.resultSet.getInt(1);
        }).intResult;
    }
}
