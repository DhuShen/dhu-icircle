package com.dhu.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class CircleMasterVerify {
    //切点
    @Pointcut("execution( * com.dhu.controller.*Controller.*Master(..))")
    public void pointCut() {
    }

    //环绕通知
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("这是环绕通知之前的部分");
        Object object = point.proceed();
        System.out.println("这是环绕通知之后的部分");
        return object;
    }

}
