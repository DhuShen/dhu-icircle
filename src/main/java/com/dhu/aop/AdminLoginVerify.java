package com.dhu.aop;

import com.dhu.domain.Admin;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import javax.servlet.http.HttpSession;

@Aspect
public class AdminLoginVerify {
    //切点
    @Pointcut("execution( * com.dhu.controller.*Controller.*Admin(..))")
    public void pointCut() {
    }

    //环绕通知
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        HttpSession session = null;
        Object[] args=point.getArgs();
        for (int i = 0; i < args.length; i++) {
            if(args[i].getClass().equals(HttpSession.class))
            {
                session= (HttpSession) args[i];
                break;
            }
        }
        Admin admin= (Admin) session.getAttribute("admin");
        System.out.println(admin.getAdminName());
        System.out.println("这是环绕通知之前的部分");
        Object object = point.proceed();
        System.out.println("这是环绕通知之后的部分");
        return object;
    }

}
