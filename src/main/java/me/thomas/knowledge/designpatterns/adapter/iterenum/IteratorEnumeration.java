package me.thomas.knowledge.designpatterns.adapter.iterenum;

import java.util.Enumeration;
import java.util.Iterator;

public class IteratorEnumeration implements Enumeration {
	Iterator iterator;
 
	public IteratorEnumeration(Iterator iterator) {
		this.iterator = iterator;
	}
 
	public boolean hasMoreElements() {
		return iterator.hasNext();
	}
 
	public Object nextElement() {
		return iterator.next();
	}
}
