package util;

import java.sql.*;

/**
 * 数据库操作
 * */
public class JDBCUtil {
    private static String userName = "root";
    private static String password = "123456";
    private static String url = "jdbc:mysql://localhost:3306/javaee?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static Connection conn = null;

    // 静态块：在类加载的时候进行初始化，只执行一次
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 因Connection对象只需要创建一次即可
     * jdbc获取连接
     *
     * @return Connection
     */
    public static Connection getConn() {
        try {
            if (null == conn || conn.isClosed()) {
                conn = DriverManager.getConnection(url, userName, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * jdbc资源的关闭
     *
     * @param conn
     * @param pstmt
     * @param rs
     */
    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (null != rs) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 不管try中如何执行，finally都会执行
            try {
                if (null != pstmt) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != conn) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Resource query(String sql, IUsingHandler usingHandler) {
        Resource resource = new Resource();

        resource.connection = getConn();
        try {
            resource.statement = resource.connection.prepareStatement(sql);
            usingHandler.invoke(resource);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(resource.connection, resource.statement, resource.resultSet);
        }

        return resource;
    }

    public interface IUsingHandler {
        void invoke(Resource resource) throws SQLException;
    }

    public static class Resource {
        public Connection connection;
        public PreparedStatement statement;
        public ResultSet resultSet;

        public int intResult;
        public float floatResult;
        public double doubleResult;
        public String stringResult;
    }
}
