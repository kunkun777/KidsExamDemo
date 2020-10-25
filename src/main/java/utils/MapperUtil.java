package utils;

import java.sql.*;
import java.util.ResourceBundle;

public class MapperUtil {

    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    static{
        String db = "resource";
        driver= ResourceBundle.getBundle(db).getString("driver");
        url=ResourceBundle.getBundle(db).getString("url");
        user=ResourceBundle.getBundle(db).getString("user");
        password=ResourceBundle.getBundle(db).getString("password");
    }

    public static Connection getCon() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }


    public static void close(Connection con, PreparedStatement ps, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean addUpdateDelete(String sql,Object[] arr){
        Connection con;
        PreparedStatement ps;
        try {
            con= MapperUtil.getCon();
            ps=con.prepareStatement(sql);
            if(arr!=null && arr.length!=0){
                for(int i=0;i<arr.length;i++){
                    ps.setObject(i+1, arr[i]);
                }
            }
            int count=ps.executeUpdate();
            return count > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
