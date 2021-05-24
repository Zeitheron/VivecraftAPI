package org.zeith.viveapi.api.event;

import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * This event hooks into the hotbar selection of Vivecraft.
 * Cancelling this event will prevent any Vivecraft slot selection at all.
 * The
 * <p>
 * Fired on {@link org.zeith.viveapi.api.VivecraftAPI#VR_BUS}
 */
@Cancelable
public class VRHotbarHoverEvent
		extends Event
{
	/**
	 * Suggested use range: [?; 1]
	 */
	public final float originalPos;

	/**
	 * Suggested use range: [0; 1]
	 */
	public float newPos;

	public VRHotbarHoverEvent(float pos)
	{
		this.newPos = this.originalPos = pos;
	}

	@Override
	public String toString()
	{
		return "VRHotbarHoverEvent{" +
				"pos=" + originalPos +
				", newPos=" + newPos +
				'}';
	}
}