package org.zeith.viveapi.api;

import net.minecraftforge.fml.common.eventhandler.EventBus;

public class VivecraftAPI
{
	/**
	 * Event bus used to distribute this mod's events to other mods.
	 */
	public static final EventBus VR_BUS = new EventBus();

	/**
	 * Gets the haptics engine to trigger pulses.
	 */
	public IHaptics getHaptics()
	{
		return DummyHaptics.INSTANCE;
	}

	/**
	 * Gets the current VR player
	 */
	public IVRPlayer getVRPlayer()
	{
		return DummyVRPlayer.INSTANCE;
	}

	/**
	 * Gets the current API instance used to proxy with Vivecraft, and as a result, SteamVR.
	 */
	public static VivecraftAPI getAPI()
	{
		return INSTANCE;
	}

	private static VivecraftAPI INSTANCE = new VivecraftAPI();

	private static class DummyHaptics
			implements IHaptics
	{
		static final DummyHaptics INSTANCE = new DummyHaptics();

		@Override
		public void triggerHapticPulse(EnumHapticsController hand, float durationSeconds, float frequency, float amplitude)
		{
		}

		@Override
		public void queueHapticPulse(EnumHapticsController hand, float durationSeconds, float frequency, float amplitude, float delaySeconds)
		{
		}
	}

	private static class DummyVRPlayer
			implements IVRPlayer
	{
		static final DummyVRPlayer INSTANCE = new DummyVRPlayer();

		@Override
		public boolean isActive()
		{
			return false;
		}

		@Override
		public IVRDeviceInfo getDeviceInfo(VRDataStage stage, VRDevice device)
		{
			return IVRDeviceInfo.DUMMY;
		}
	}
}