package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entity.User;
import mapper.UserDao;
import mapper.impl.UserDaoImpl;


@WebServlet("/userlogin")
public class UserLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDao userDao=new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = new User();
        //获取login.jsp页面提交的账号和密码
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        //测试数据
        System.out.println(name + " " + password);
        user.setUsername(name);
        user.setPassword(password);

        //获取login.jsp页面提交的账号和密码设置到实体类User中
        if (userDao.login(user)!=null) {
            request.getRequestDispatcher("/success.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }


}