package org.zeith.viveapi.api.event;

import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Objects;

/**
 * This event is used to determine the position of the hot-bar.
 * Might be useful if your mod changes positions.
 * <p>
 * Fired on {@link org.zeith.viveapi.api.VivecraftAPI#VR_BUS}
 */
public class VRHotbarPositionEvent
		extends Event
{
	public final boolean reverseHands;
	public final HudLock lock;
	public final PositionType type;

	public float x, y, z;

	public VRHotbarPositionEvent(boolean reverseHands, HudLock lock, PositionType type, float x, float y, float z)
	{
		this.reverseHands = reverseHands;
		this.lock = lock;
		this.type = type;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public String toString()
	{
		return "VRHotbarPositionEvent{" +
				"reverseHands=" + reverseHands +
				", lock=" + lock +
				", type=" + type +
				", x=" + x +
				", y=" + y +
				", z=" + z +
				'}';
	}

	public enum HudLock
	{
		WRIST,
		HAND
	}

	public enum PositionType
	{
		START,
		END
	}
}