package org.zeith.viveapi.util;

public class FloatShenanigans
{
	public static boolean equal(float a, float b)
	{
		return Math.abs(a - b) < 0.00000001F;
	}
}