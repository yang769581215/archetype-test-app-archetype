#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import com.pkfare.air.cacheservice.client.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

/**
 * @Author:nathaniel
 * @Date: 2019-06-20
 * @Description:统一异常处理模版
 */
@Slf4j
@ControllerAdvice({"com.pkfare.xx.xx"})
public class GlobalExceptionHandler {

  /**
   * 处理spring框架级别数据验证异常
   *
   * @param e
   * @return
   */
  @ExceptionHandler({MethodArgumentNotValidException.class})
  @ResponseBody
  public Result<Void> handleSpringValidException(MethodArgumentNotValidException e) {
    FieldError fieldError = e.getBindingResult().getFieldErrors().get(0);
    return new Result<Void>();
  }

  /**
   * 处理业务异常以及系统异常
   *
   * @param e
   * @return
   */
  @ExceptionHandler(Exception.class)
  @ResponseBody
  public Result<Void> handleException(Exception e, HandlerMethod handlerMethod) {
    Object bean = handlerMethod.getBean();
    String type;
    if (AopUtils.isAopProxy(bean)) {
      type = AopUtils.getTargetClass(bean).getCanonicalName();
    } else {
      type = bean.getClass().getCanonicalName();
    }
    String method = handlerMethod.getMethod().getName();
    log.error("{}-{} 请求处理失败${symbol_escape}n{}", type, method, ExceptionUtils.getStackTrace(e));
    return new Result<Void>();
  }

}
