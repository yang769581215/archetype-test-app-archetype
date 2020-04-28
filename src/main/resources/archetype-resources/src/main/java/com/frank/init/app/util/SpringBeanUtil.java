#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package  com.frank.init.app.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * bean工具类
 * @author kizzywong
 */
@Component
public class SpringBeanUtil implements ApplicationContextAware {

  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext)
      throws BeansException {
    SpringBeanUtil.applicationContext = applicationContext;
  }

  public static <T> T getBean(Class<T> clazz) {
    return (T) applicationContext.getBean(clazz);
  }

  public static <T> T getBean(String name) throws BeansException {

    return (T)applicationContext.getBean(name);
  }
}