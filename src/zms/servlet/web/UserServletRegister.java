package zms.servlet.web;

import zms.serviceImpl.UserServiceImpl;
import zms.util.AJaxResult;
import zms.util.Encoding;
import zms.util.message.GetCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/web/userServletRegister")
public class UserServletRegister extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        Encoding.setEncoding(request,response);
        //生成的验证码与前台传入的验证码进行比较
        String code=request.getParameter("code");
        String getrandom= GetCode.param;
        //获取参数进行入库操作
        String regphone=request.getParameter("regphone");
        String regpassword=request.getParameter("regpassword");
        UserServiceImpl userService=new UserServiceImpl();
        Boolean flag = userService.phoneRegister(regphone, regpassword, code, getrandom);
        AJaxResult getajax = userService.getajax();//返回结果
        if(flag){
            //注册成功
            String contextPath = request.getContextPath()+"/include/registerSuccessPage.jsp";
            response.sendRedirect(contextPath);
        }else {
            request.setAttribute("msg",getajax);
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
