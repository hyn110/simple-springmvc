package com.fmi110.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyExceptionResolver implements HandlerExceptionResolver {

    /**
     *
     * @param request
     * @param response
     * @param handler 可以从该对象获取是哪个controller方法抛出的异常
     * @param ex
     * @return
     */
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ex",ex);
        map.put("handler",handler);

        /**
         * 在这里可以定制自己的异常处理策略
         */
//        if (ex instanceof MyException) {
//            return new ModelAndView("error-my",map);
//        } else {
//            return new ModelAndView("error",map);
//        }
        return new ModelAndView("error.jsp",map);
    }


}
