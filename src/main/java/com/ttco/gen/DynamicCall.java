package com.ttco.gen;

import java.lang.reflect.Method;
import java.util.HashMap;

public class DynamicCall 
{
	String name,className, packageName;
	Object obj;
	
	//Constructor for the reflection code based class
	public DynamicCall(String name)
	{
		this.name = name;		
		init();
	}
	
	// in this method we will separate the class name and package name
	public void init()
	{
		try
		{
			packageName = Class.forName(name).getPackage().getName();
			className = name.replace(packageName, "");
			if(className.startsWith("."))
				className = className.substring(1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//calls the constructor and creates an object for the same.
	//constructor without parameters.
	public DynamicCall create()
	{
		try
		{
			obj = Class.forName(name).getConstructor(null).newInstance();
			return this;
		}
		catch(Exception e)
		{
			
		}
		return this;
	}
	
	//invokes method of a class without any parameters
	public DynamicCall invoke(String methodName)
	{
		try
		{
			Method method  = null;
			if(obj!=null)
			{
				method = obj.getClass().getMethod(methodName, null);
				method.invoke(obj, null);
				return this;
			}
		}
		catch(Exception e)
		{
			
		}
		return this;
	}
	
	//invokes method of a class with parameters
	public DynamicCall invoke(String methodName,Object ip)
	{
		try
		{
			Method method  = null;
			if(obj!=null)
			{
				method = obj.getClass().getMethod(methodName, ip.getClass());
				method.invoke(obj, ip);
				return this;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return this;
	}
	
}
