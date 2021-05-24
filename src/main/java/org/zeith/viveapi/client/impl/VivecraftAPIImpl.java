package org.zeith.viveapi.client.impl;

import org.zeith.viveapi.api.IHaptics;
import org.zeith.viveapi.api.VivecraftAPI;

public class VivecraftAPIImpl
		extends VivecraftAPI
{
	public final IHaptics haptics = new HapticsImpl();

	@Override
	public IHaptics getHaptics()
	{
		return haptics;
	}
}