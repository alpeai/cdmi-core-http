package pw.cdmi.core.http.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {
	private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);
	
	@RequestMapping(value="/error", method = RequestMethod.GET)
    public String handle(HttpServletRequest request) {
		logger.info(">>>开始执行请求:" +"[" + request.getMethod()+ "]" + request.getRequestURI());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", request.getAttribute("javax.servlet.error.status_code"));
        map.put("reason", request.getAttribute("javax.servlet.error.message"));

        return "home";
    }
	
	@ExceptionHandler(AWSClientException.class)
	public ModelAndView handleClientError(RuntimeException error, HttpServletRequest request) {
		logger.debug("捕获Client异常,访问路径是：" + request.getRequestURI());
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("error");
	    mav.addObject("param", "Client error");
	    return mav;
	}
	
	@ExceptionHandler(AWSServiceException.class)
	public ModelAndView handleServerError(RuntimeException error, HttpServletRequest request) {
		logger.debug("捕获Server异常,访问路径是：" + request.getRequestURI());
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("error");
	    mav.addObject("param", "Server error");
	    return mav;
	} 
}
