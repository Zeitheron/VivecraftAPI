package org.zeith.viveapi.api;

public interface IHaptics
{
	float DEFAULT_FREQUENCY = 160F;

	/**
	 * Triggers haptics pulse to happen on a given hand.
	 * Use {@link #DEFAULT_FREQUENCY} to give vivecraft-like feel to it.
	 */
	void triggerHapticPulse(EnumHapticsController hand, float durationSeconds, float frequency, float amplitude);

	/**
	 * Queues haptics pulse to happen on a given hand after some time.
	 * Use {@link #DEFAULT_FREQUENCY} to give vivecraft-like feel to it.
	 */
	void queueHapticPulse(EnumHapticsController hand, float durationSeconds, float frequency, float amplitude, float delaySeconds);

	enum EnumHapticsController
	{
		RIGHT,
		LEFT;
	}
}