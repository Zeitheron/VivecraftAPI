package org.zeith.viveapi.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

public class Reflectors
{
	public static Optional<Class<?>> getClass(String name)
	{
		try
		{
			return Optional.of(Class.forName(name));
		} catch(Throwable err)
		{
			return Optional.empty();
		}
	}

	public static Optional<Method> getDeclaredMethod(Optional<Class<?>> clazz, String method, Class<?>... params)
	{
		return clazz.map(c ->
		{
			try
			{
				Method m = c.getDeclaredMethod(method, params);
				m.setAccessible(true);
				return m;
			} catch(NoSuchMethodException | SecurityException e)
			{
				return null;
			}
		});
	}

	public static Optional<Method> getMethod(Optional<Class<?>> clazz, String method, Class<?>... params)
	{
		return clazz.map(c ->
		{
			try
			{
				Method m = c.getMethod(method, params);
				m.setAccessible(true);
				return m;
			} catch(NoSuchMethodException | SecurityException e)
			{
				return null;
			}
		});
	}

	public static Optional<Field> getDeclaredField(Optional<Class<?>> clazz, String field)
	{
		return clazz.map(c ->
		{
			try
			{
				Field m = c.getDeclaredField(field);
				m.setAccessible(true);
				return m;
			} catch(SecurityException | NoSuchFieldException e)
			{
				return null;
			}
		});
	}

	public static Optional<Field> getField(Optional<Class<?>> clazz, String field)
	{
		return clazz.map(c ->
		{
			try
			{
				Field m = c.getField(field);
				m.setAccessible(true);
				return m;
			} catch(SecurityException | NoSuchFieldException e)
			{
				return null;
			}
		});
	}

	public static <T> Optional<T> getValue(Optional<Field> field, Object instance)
	{
		return field.map(f ->
		{
			try
			{
				return (T) f.get(instance);
			} catch(IllegalAccessException e)
			{
				return null;
			}
		});
	}
}