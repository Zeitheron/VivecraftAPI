package org.zeith.viveapi.client.impl;

import org.vivecraft.control.ControllerType;
import org.vivecraft.provider.MCOpenVR;
import org.zeith.viveapi.api.EnumController;
import org.zeith.viveapi.api.IHaptics;

public class HapticsImpl
		implements IHaptics
{
	private static final ControllerType[] controllerTypes = ControllerType.values();

	public static ControllerType ec2ct(EnumController controller)
	{
		return controllerTypes[controller.ordinal()];
	}

	@Override
	public void triggerHapticPulse(EnumController controller, float durationSeconds, float frequency, float amplitude)
	{
		MCOpenVR.triggerHapticPulse(controllerTypes[controller.ordinal()], durationSeconds, frequency, amplitude);
	}

	@Override
	public void queueHapticPulse(EnumController controller, float durationSeconds, float frequency, float amplitude, float delaySeconds)
	{
		MCOpenVR.triggerHapticPulse(controllerTypes[controller.ordinal()], durationSeconds, frequency, amplitude, delaySeconds);
	}
}