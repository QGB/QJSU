import java.lang.reflect.Field;

import qgb.T;

public class A {
	public static void main(String[] a) throws Exception {
		
		Field c = Integer.class.getDeclaredClasses()[0]
				.getDeclaredField("cache");
		c.setAccessible(true);
		Integer[] y = (Integer[]) c.get(null);
		y[129] = y[133];
		y[139] = y[130];
		y[239] = y[128];
		/** Dif*/
		System.out.printf("%d%d%d", 1, 11, 111);
	}
}