package org.zeith.viveapi.api;

import org.vivecraft.control.ControllerType;

public interface IHapticScheduler
{
	float DEFAULT_FREQUENCY = 160F;

	void triggerHapticPulse(ControllerType controller, float durationSeconds, float frequency, float amplitude);

	void queueHapticPulse(ControllerType controller, float durationSeconds, float frequency, float amplitude, float delaySeconds);
}