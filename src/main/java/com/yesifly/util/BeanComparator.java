/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yesifly.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * 
 * @author brtaylor
 */
public class BeanComparator {

	/**
	 * Deep compares two beans to determine if their contents are equal.
	 * 
	 * @param bean1
	 * @param bean2
	 * @return True if the beans are equal, otherwise false.
	 */
	public static <T> boolean compare(T bean1, T bean2) {
		BeanInfo classInfo;
		
		// Return true if the beans are the same object.
		if (bean1 == bean2)  {
			return true;
		}
		
		// Return false if one of the beans (but not both) is null.
		if ((bean1 == null) ^ (bean2 == null))
			return false;

		try {
			classInfo = Introspector.getBeanInfo(bean1.getClass());
			PropertyDescriptor[] propDescriptors = classInfo
					.getPropertyDescriptors();
			
			

			for (PropertyDescriptor d : propDescriptors) {
				Method m = d.getReadMethod();
				Object valu1 = m.invoke(bean1);
				Object valu2 = m.invoke(bean2);
				
				if ((valu1 == null) && (valu2 != null))
					return false;
				
				if (valu1== null && valu2 == null)
					return true;
				if (valu1.equals(valu2) == false)  {
					return false;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
}
