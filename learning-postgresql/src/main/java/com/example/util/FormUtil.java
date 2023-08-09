package com.example.util;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

@SuppressWarnings({ "deprecation", "unchecked" })
public class FormUtil {
	
	public static <T> T toModel(Class<T> clazz, HttpServletRequest request) {
		T object = null;
		try {
			object = clazz.newInstance();
			BeanUtils.populate(object, request.getParameterMap());
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			System.out.print(e.getMessage());
		}
		return object;
	}
}
