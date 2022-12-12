package com.dhu.aop;

import com.dhu.domain.Admin;
import com.dhu.exception.MyException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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
        String clazz = point.getTarget().getClass().getName();
        // 获取目标对象上正在执行的方法名
        String methodName = point.getSignature().getName();
        System.out.println("前置通知:" + clazz + "类的" + methodName + "方法开始了...");
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session =request.getSession();
        Admin admin= (Admin) session.getAttribute("admin");
        if(admin==null) throw new MyException("没有管理员权限");
        return point.proceed();
    }
}
