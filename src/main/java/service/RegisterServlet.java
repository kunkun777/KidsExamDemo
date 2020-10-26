package service;


import entity.User;
import mapper.UserDao;
import mapper.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userregister")
public class RegisterServlet extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user=new User();
        //获取login.jsp页面提交的账号和密码
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        String email=request.getParameter("email");

        //获取register.jsp页面提交的账号和密码设置到实体类User中
        user.setUsername(name);
        user.setPassword(password);
        user.setEmail(email);
        // user.setPhone(phone);

        //引入数据交互层
        UserDao dao=new UserDaoImpl();
        boolean flag=dao.register(user);
        if(flag){
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}