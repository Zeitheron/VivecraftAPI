package org.zeith.viveapi.client.impl;

import org.zeith.viveapi.api.IHaptics;
import org.zeith.viveapi.api.IVRPlayer;
import org.zeith.viveapi.api.VivecraftAPI;

public class VivecraftAPIImpl
		extends VivecraftAPI
{
	public final IHaptics haptics = new HapticsImpl();
	public final IVRPlayer vrPlayer = new VRPlayerImpl();

	@Override
	public IHaptics getHaptics()
	{
		return haptics;
	}

	@Override
	public IVRPlayer getVRPlayer()
	{
		return vrPlayer;
	}
}