package mapper.impl;

import entity.User;
import mapper.UserDao;
import utils.MapperUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDaoImpl implements UserDao {

    @Override
    public User login(User user) {
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        try {
            con= MapperUtil.getCon();
            String sql="select * from user where username= ? and password= ? ";
            ps=con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            rs=ps.executeQuery();
            User users;
            if(rs.next()){
                users=new User();
                users.setId(rs.getString("id"));
                users.setUsername(rs.getString("username"));
                users.setPassword(rs.getString("password"));
                users.setEmail(rs.getString("email"));
                return users;
            }else{
                return null;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean register(User user) {
        String sql = "insert into user(id,username,password,address,email) values(?,?,?,?,?) ";
        List<Object> list = new ArrayList<>();
        user.setId(UUID.randomUUID().toString());
        list.add(user.getId());
        list.add(user.getUsername());
        list.add(user.getPassword());
        list.add(user.getAddress());
        list.add(user.getEmail());
//        list.add(user.getSex());

        return MapperUtil.addUpdateDelete(sql, list.toArray());
    }
}
