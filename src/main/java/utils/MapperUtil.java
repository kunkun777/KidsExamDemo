package utils;

import java.sql.*;
import java.util.ResourceBundle;

public class MapperUtil {

    private static String driver;//数据库驱动
    private static String url;//数据库路径，找到对应的数据库
    private static String user;//数据库账号
    private static String password;//数据库密码

    static{
        String db = "resource";
        driver= ResourceBundle.getBundle(db).getString("driver");
        url=ResourceBundle.getBundle(db).getString("url");
        user=ResourceBundle.getBundle(db).getString("user");
        password=ResourceBundle.getBundle(db).getString("password");
    }

    public static Connection getCon() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        System.out.println("测试加载数据库成功");
        Connection con= DriverManager.getConnection(url, user, password);
        System.out.println("测试数据库链接成功");
        return con;
    }


    public static void close(Connection con, PreparedStatement ps, ResultSet rs){
        if(rs!=null){//关闭资源，避免出现异常
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /***
     * 同意增删改的方法
     * @param sql
     * @param arr
     * @return
     */
    public static boolean addUpdateDelete(String sql,Object[] arr){
        Connection con=null;
        PreparedStatement ps=null;
        try {
            con= MapperUtil.getCon();//第一步 ：连接数据库的操作
            ps=con.prepareStatement(sql);//第二步：预编译
            //第三步：设置值
            if(arr!=null && arr.length!=0){
                for(int i=0;i<arr.length;i++){
                    ps.setObject(i+1, arr[i]);
                }
            }
            int count=ps.executeUpdate();//第四步：执行sql语句
            if(count>0){
                return true;
            }else{
                return false;
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            MapperUtil.getCon();
            System.out.println("测试数据库链接成功");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


}
