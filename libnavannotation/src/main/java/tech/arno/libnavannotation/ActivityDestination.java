package tech.arno.libnavannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * <pre>
 *     author: xuxin
 *     time  : 2020/7/1
 *     desc  :
 * </pre>
 */
@Target(ElementType.TYPE)
public @interface ActivityDestination {
    String pageUrl();

    boolean needLogin() default false;

    boolean asStarter() default false;
}
