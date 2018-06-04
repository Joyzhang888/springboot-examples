package com.hong.aspect;

import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;

/**
 * 日志切面类.
 * Created by hong on 2017/5/19.
 */

/**
 * 可以使用@Order注解指定切面的优先级，值越小优先级越高
 **/
@Order(2)
/**
 * 注解将一个java类定义为切面类
 **/
@Aspect
@Component
public class WebLogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 根据需要在切入点不同位置的切入内容:
     *     使用 @Before 在切入点开始处切入内容 ( 前置通知 )
     *     使用 @After 在切入点结尾处切入内容 ( 后置通知 )
     *     使用 @AfterReturning 在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理） (  配置后置返回通知 )
     *     使用 @Around 在切入点前后切入内容，并自己控制何时执行切入点自身的内容 (  环绕通知 )
     *     使用 @AfterThrowing 用来处理当切入内容部分抛出异常之后的处理逻辑 (  异常通知 )
     */


    /**
     * 定义个方法，注解上@Pointcut 及其规则表达式，定义成一个切入点(如下例中某个package下的所有函数，也可以是一个注解等。);
     * Controller层切点
     */
    @Pointcut("execution(public * com.hong.web..*.*(..))")
    public void webLog() {
    }


    /**
     * 前置通知 用于拦截Controller层记录的参数
     *
     * @param joinPoint
     * @Before 接收的参数是前面定义的切入点方法
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("==========执行 weblog() 切入点 前置通知===============");

        // 接收到请求，记录请求内容
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getLocalAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }

    /**
     * 后置通知
     */
    @After("webLog()")
    public void after(JoinPoint joinPoint) {
        logger.info("==========执行 weblog() 切入点 后置通知===============");
        logger.info("PARAM : " + Arrays.toString(joinPoint.getArgs()));
    }


    /**
     * 环绕通知 用来监控目标执行前后情况（例如：用来监控切入点执行时间）.
     * 注: 在使用@Around 时,需要有返回值,不然同时使用 @AfterReturning 获取不到返回参数 .
     * 环绕通知需要携带ProceedingJoinPoint类型的参数
     *
     * @param joinPoint
     */
//    @Around("webLog()")
//    public Object around(ProceedingJoinPoint joinPoint) {
//        logger.info("[==========开始 weblog() 切入点 环绕通知===============]");
//
//        long start = System.currentTimeMillis();
//        Object ret = null;
//        try {
//            // 环绕通知 ProceedingJoinPoint 执行proceed方法的作用是让目标方法执行
//            // 环绕通知=前置+目标方法执行+后置通知，proceed方法就是用于启动目标方法执行的
//            // 也就是说，环绕通知可以用来代替同时使用前置、后置通知的情况
//            ret = joinPoint.proceed();
//            long end = System.currentTimeMillis();
//
//            // 其作用是因为Debug,Info和Trace一般会打印比较详细的信息，而且打印的次数较多，如果我们不加log.isDebugEnabled()等
//            // 进行预先判断，当系统loglevel设置高于Debug或Info或Trace时，虽然系统不会打应出这些级别的日志，但是每次预先拼接log打印中的参数字符串，影响系统的性能。
//            if (logger.isInfoEnabled()) {
//                logger.info("监控到========= " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "执行时间：" + (end - start) + "ms!");
//            }
//            logger.info("[==========结束 weblog() 切入点 环绕通知===============]");
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//
//        return ret;
//    }


    /**
     * 后置返回通知 用来监控返回值
     */
    @AfterReturning(pointcut = "webLog()", returning = "ret")
    public void afterReturn(JoinPoint joinPoint, Object ret) {
        logger.info("==========开始 weblog() 切入点 环绕通知===============");
        logger.info("==========开始 weblog() 切入点 环绕通知===============");
        logger.info("=========监控到 " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "返回的值：");
        logger.info(ret.toString());
    }


    /**
     * 异常通知 用于拦截异常,记录日志
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(pointcut = "webLog()", throwing = "ex")
    public void AfterThrowing(JoinPoint joinPoint, Throwable ex) {
        logger.info("==========开始 weblog() 切入点 异常通知===============");
        logger.info("=========监控到 " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("发生异常: " + ex.getMessage());
        logger.info("请求参数: ");
        Stream.of(joinPoint.getArgs()).forEach(arg -> logger.info(arg.toString()));
        logger.info("异常时间: " + new Date());
    }

}
