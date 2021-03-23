package cn.stu;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Random;

public class LoginServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        //私有的初始化参数
        ServletConfig s = getServletConfig();
        System.out.println("------------------");
        System.out.println("LoginServlet init invoked!");
        System.out.println("私有的初始化参数brand="+s.getInitParameter("brand"));
        //使用s.getInitParameterNames
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");//设置收到的数据的字符集，解决post方式提交时转换成字符串显示在控制台乱码问题
        String username = req.getParameter("username");
        String pwd = req.getParameter("pwd");
        boolean flag;
        if("admin".equals(username) && "admin".equals(pwd)){
            flag=true;
        }else {
            flag=false;
        }
        System.out.println("----------------");
        System.out.println("username:"+username);
        System.out.println("pwd:"+pwd);
        System.out.println("登录状态："+flag);
        resp.getWriter().print(flag);

        System.out.println("----------------");
        System.out.println("显示请求行的相关信息");
        System.out.println("请求方式:"+req.getMethod());
        System.out.println("URL："+req.getRequestURL());//返回客户端浏览器发出请求时的完整URL。
//        System.out.println(req.getRequestURI());//返回请求行中指定资源部分URI,不算URL
        System.out.println("协议名:"+req.getScheme());
        System.out.println("客户端IP："+req.getRemoteAddr());//返回发出请求的客户机的IP地址。
        System.out.println("客户端PORT: " + req.getRemotePort());
//        System.out.println(req.getLocalPort());//返回WEB服务器处理Http协议的连接器所监听的端口。
//        System.out.println(req.getLocalAddr());//返回WEB服务器的IP地址。
//        System.out.println("主机名: " + req.getLocalName());
//        System.out.println("当前项目部署名: " + req.getContextPath());


        System.out.println("----------------");
        System.out.println("显示请求头的相关信息");
        // 根据请求头名或者请求头对应的值
//        System.out.println(req.getHeader("Accept"));
        // 获得全部的请求头名
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String headername = headerNames.nextElement();
            System.out.println(headername+":"+req.getHeader(headername));
        }


        //设置响应的信息
        resp.setStatus(500);
//        resp.setStatus(200,"OK");方法被废弃，因为状态描述原则上跟着状态码，不需要再设置
        resp.setHeader("Date","2000-1-5");//设置响应头，键值对形式
        resp.setHeader("myHeader","myhead");//也可以设置没有的键

        //设置响应体
        resp.setContentType("text/html;charset=UTF-8");//TODO:不知道为什么还是会乱码
        resp.getWriter().write("<h1>君不见黄河之水天上来</h>");

        //Servlet上下文
        System.out.println("---------------");
        ServletContext s1 = req.getServletContext();
        ServletContext s2 = getServletContext();
        System.out.println("比较两个Servlet上下文对象是否是同一个："+ (s1==s2));

        System.out.println("项目的部署名："+s1.getContextPath());//获取项目的部署名
        System.out.println("将相对路径fileUpload转为觉得路径："+s1.getRealPath("fileUpload"));

        //容器的信息
        System.out.println("---------------");
        System.out.println("Servlet相关信息：");
        System.out.println("容器:"+s1.getServerInfo());
        System.out.println("Servlet主版本"+s1.getMajorVersion());
        System.out.println("Servlet次版本"+s1.getMinorVersion());

        //获取web.xml中KV
        System.out.println("---------------");
        System.out.println("key对应的值："+s1.getInitParameter("key"));
        //获取全部key
        System.out.println("全部的KV");
        Enumeration<String> ps = s1.getInitParameterNames();
        while(ps.hasMoreElements()){
            String s = ps.nextElement();
            System.out.println(s + "=" + s1.getInitParameter(s));
        }

        //全局可用属性
        System.out.println("---------------");
        s1.setAttribute("a1","value of a1");
        s1.setAttribute("a2", 6666);
        System.out.println("属性a1对应的值："+s1.getAttribute("a1"));
        //获取全部属性值
        System.out.println("全部的属性");
        Enumeration<String> as = s1.getAttributeNames();
        while(as.hasMoreElements()){
            String s = as.nextElement();
            System.out.println(s + "=" + s1.getAttribute(s));
        }


    }
}
