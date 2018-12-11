package com.chris.bulleyeadmin.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD, ElementType.TYPE })
public @interface Operalog {
	/**
	 * 业务名称
	 * 
	 * @return
	 */
	String value();

	/**
	 * 对应的json格式参数
	 * @return
	 */
	String jsonParam() default "";

	/**
	 * 其它格式参数
	 * @return
	 */
	String param() default "";
}
