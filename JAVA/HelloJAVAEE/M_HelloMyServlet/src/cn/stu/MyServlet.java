package cn.stu;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class MyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int num = new Random().nextInt();
        String s = num%2==0?"Hello Servlet!":"Hello My Servlet!";
        PrintWriter writer = resp.getWriter();//获取resp的打印流实例
        writer.write(s);//打印s
    }
}
