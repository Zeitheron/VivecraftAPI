package org.zeith.viveapi.api;

import net.minecraftforge.fml.common.eventhandler.EventBus;

public class VivecraftAPI
{
	public static EventBus VR_BUS = new EventBus();

	private static VivecraftAPI INSTANCE = null;

	public static VivecraftAPI getAPI()
	{
		return INSTANCE;
	}
}