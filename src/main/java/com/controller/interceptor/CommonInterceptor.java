package com.controller.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Tellyes_worker on 2017/12/7/0007.
 */
public class CommonInterceptor implements HandlerInterceptor{

    private List<String> excludedUrls;

    public List<String> getExcludedUrls(){
        return excludedUrls;
    }

    public void setExcludedUrls(List<String> excludedUrls){
        this.excludedUrls = excludedUrls;
    }


    /**
     * 在业务处理器处理请求之前被调用，如果返回false
     * 从当前得拦截器往回执行所有拦截器的afterCompletion(),
     * 再退出拦截器链，如果返回true执行下一个拦截器
     * 直到所有的拦截器都执行完毕，再执行被拦截的controller
     * 然后进入拦截器链
     * 从最后一个拦截器往回执行所有的post Handler（）
     * 接着再从最后一个拦截器往回执行所有的afterCompletion()
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)throws Exception{
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");

        System.out.println("=-p==-=--==----拦截器---------");
        return true;
    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)throws  Exception{

    }

    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex)throws Exception{

    }

}
