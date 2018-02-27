package com.fmi110.commons;

import com.fmi110.utils.WebUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fmi110
 * @Description: 全局异常统一处理类
 * @Date 2018/2/27 11:08
 */
@Component
public class ExceptionResolver implements HandlerExceptionResolver{

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {

        /**
         * 在这里可以定制自己的异常处理策略
         */
//        if (ex instanceof MyException) {
//            return new ModelAndView("error-my",map);
//        } else {
//            return new ModelAndView("error",map);
//        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",500);
        map.put("message","系统内部异常...");
        map.put("data", "这里填充需要返回的数据的对象");

        // 判断是否时ajax 请求
        boolean isAjax = WebUtils.isAjax((HandlerMethod) handler);
        if (isAjax) {
            // 视图内容将会转为 json 返回
            ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
            mv.addObject(map);
            return mv;
        }else{
            // 普通请求返回页面资源
            return new ModelAndView("error.jsp",map);
        }
    }
}
