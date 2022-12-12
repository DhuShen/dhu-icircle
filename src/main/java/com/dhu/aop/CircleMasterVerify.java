package com.dhu.aop;

import com.dhu.domain.Post;
import com.dhu.domain.User;
import com.dhu.exception.MyException;
import com.dhu.service.CircleService;
import com.dhu.service.PostService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Aspect
public class CircleMasterVerify {
    @Autowired
    CircleService circleService;
    @Autowired
    PostService postService;

    //切点
    @Pointcut("execution( * com.dhu.controller.*Controller.*Master(..))")
    public void pointCut() {
    }

    //环绕通知
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        String clazz = point.getTarget().getClass().getName();
        // 获取目标对象上正在执行的方法名
        String methodName = point.getSignature().getName();
        System.out.println("前置通知:" + clazz + "类的" + methodName + "方法开始了...");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) throw new MyException("没有用户权限");
        //获取参数值
        Object[] args = point.getArgs();
        //获取参数名字
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] parameterNames = methodSignature.getParameterNames();
        int postId, circleId;
        for (int i = 0; i < parameterNames.length; i++) {
            if (parameterNames[i].equals("postId")) {
                postId = (Integer) args[i];
                Post post = postService.selectById(postId);
                int postCircleId = post.getPost_CircleId();
                if (!circleService.isCircleMaster(user.getUserId(), postCircleId))
                    throw new MyException("没有圈主权限！");
                break;
            } else if (parameterNames[i].equals("circleId")) {
                circleId = (Integer) args[i];
                if (!circleService.isCircleMaster(user.getUserId(), circleId))
                    throw new MyException("没有圈主权限！");
                break;
            }
        }
        return point.proceed();
    }
}
