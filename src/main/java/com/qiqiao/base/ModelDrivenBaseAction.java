package com.qiqiao.base;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ModelDriven;

public abstract class ModelDrivenBaseAction<T> extends BaseAction implements ModelDriven<T> {

	private static final long serialVersionUID = 1L;
	protected T model;

	@SuppressWarnings("unchecked")
	public ModelDrivenBaseAction() {
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		Class clazz = (Class) pt.getActualTypeArguments()[0];
		try {
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}

}
