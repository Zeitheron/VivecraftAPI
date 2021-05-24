package org.zeith.viveapi.client.impl;

import org.vivecraft.control.ControllerType;
import org.vivecraft.provider.MCOpenVR;
import org.zeith.viveapi.api.IHaptics;

public class HapticsImpl
		implements IHaptics
{
	final ControllerType[] controllerTypes = ControllerType.values();

	@Override
	public void triggerHapticPulse(EnumHapticsController controller, float durationSeconds, float frequency, float amplitude)
	{
		ControllerType type = controllerTypes[controller.ordinal()];
		MCOpenVR.triggerHapticPulse(type, durationSeconds, frequency, amplitude);
	}

	@Override
	public void queueHapticPulse(EnumHapticsController controller, float durationSeconds, float frequency, float amplitude, float delaySeconds)
	{
		ControllerType type = controllerTypes[controller.ordinal()];
		MCOpenVR.triggerHapticPulse(type, durationSeconds, frequency, amplitude, delaySeconds);
	}
}