package com.ray.zerocode.aop;

import com.ray.zerocode.annotation.AuthCheck;
import com.ray.zerocode.exception.BusinessException;
import com.ray.zerocode.exception.ErrorCode;
import com.ray.zerocode.innerservice.InnerUserService;
import com.ray.zerocode.model.entity.User;
import com.ray.zerocode.model.enums.UserRoleEnum;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 权限拦截器
 */
@Aspect
@Component
public class AuthInterceptor {

    @Resource
    @Lazy
    private InnerUserService userService;

    //定义切点

    /**
     * 拦截指定方法
     * @param joinPoint 切入点
     * @param authCheck 注解
     * @return
     * @throws Throwable
     */
    @Around("@annotation(authCheck)")
    public  Object doIntercept(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        //获取当前登录用户
        User loginUser = InnerUserService.getLoginUser(request);
        UserRoleEnum mustUserRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
        //不需要权限直接放行
        if(mustUserRoleEnum == null) {
            return joinPoint.proceed();
        }
        //以下代码必须有权限才通过
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(loginUser.getUserRole());
        //没有权限直接拒绝
        if(userRoleEnum == null) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        //要求为管理员权限，但登录用户没有
        if(userRoleEnum != UserRoleEnum.ADMIN && mustUserRoleEnum == UserRoleEnum.ADMIN) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        //符合要求
        return joinPoint.proceed();
    }
}
