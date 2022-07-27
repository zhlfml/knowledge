package me.thomas.knowledge.designpatterns.proxy.javaproxy;

import java.lang.reflect.*;

/**
 * JDK的动态代理依靠接口实现，如果委托类并没有实现接口，则不能使用JDK代理。
 * see <a>http://www.cnblogs.com/jqyp/archive/2010/08/20/1805041.html</a>
 */
public class NonOwnerInvocationHandler implements InvocationHandler {
	PersonBean person;

	/**
	 * 绑定委托对象并返回一个代理对象。
	 *
	 * @param person 委托对象
	 * @return 代理对象
	 */
	public PersonBean bind(PersonBean person) {
        this.person = person;
		// 取得代理对象
		return (PersonBean) Proxy.newProxyInstance(person.getClass().getClassLoader(),
				person.getClass().getInterfaces(), this);
	}

	/**
	 * 通过代理对象调用方法会将请求转发到这个方法。
	 *
	 * @param proxy 代理对象
	 * @param method 被调用方法
	 * @param args 方法的参数
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws IllegalAccessException {

		try {
			if (method.getName().startsWith("get")) {
				return method.invoke(person, args);
   			} else if (method.getName().equals("setHotOrNotRating")) {
				return method.invoke(person, args);
			} else if (method.getName().startsWith("set")) {
				throw new IllegalAccessException();
			}
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
		return null;
	}
}
