package com.umarashfaq.tyrion.web.converters;

public interface Persistable<T> {
	public T getId();
	public void setId(T id);
}
