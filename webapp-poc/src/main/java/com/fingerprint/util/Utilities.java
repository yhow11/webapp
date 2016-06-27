package com.fingerprint.util;

import java.util.ArrayList;

public class Utilities {

	public static <E> ArrayList<E> makeCollection(Iterable<E> iter) {
		ArrayList<E> list = new ArrayList<E>();
	    for (E item : iter) {
	        list.add(item);
	    }
	    return list;
	}
}
