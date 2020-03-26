package com.WangD.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//统计网站在线人数：统计session
public class OnlineCountListener implements HttpSessionListener {
    //创建session监听
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();
        System.out.println(se.getSession().getId());
        Integer onlineCount = (Integer) ctx.getAttribute("OnlineCount");
        if(onlineCount==null){
            onlineCount = 1;
        }else{
            int count = onlineCount;
            onlineCount = count + 1;
        }
        ctx.setAttribute("OnlineCount",onlineCount);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();
        Integer onlineCount = (Integer) ctx.getAttribute("OnlineCount");
        if(onlineCount==null){
            onlineCount = 0;
        }else{
            int count = onlineCount;
            onlineCount = count - 1;
        }
        ctx.setAttribute("OnlineCount",onlineCount);
    }
}

