package org.zeith.viveapi.api.event;

import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * This event is a hook to 2-axial scrolling in VR.
 * Cancelling this event will prevent Vivecraft scroll code from running
 * <p>
 * Fired on {@link org.zeith.viveapi.api.VivecraftAPI#VR_BUS}
 */
@Cancelable
public class VRScrollEvent
		extends Event
{
	public final ScrollEventType eventType;
	public final float valueX, valueY, deltaX, deltaY;

	public final String name;
	public final String requirement;
	public final String type;

	public VRScrollEvent(ScrollEventType eventType,
						 float valueX, float valueY,
						 float deltaX, float deltaY,
						 String name, String requirement, String type)
	{
		this.eventType = eventType;
		this.valueX = valueX;
		this.valueY = valueY;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
		this.name = name;
		this.requirement = requirement;
		this.type = type;
	}

	public enum ScrollEventType
	{
		SCROLL_AXIS,
		HOTBAR_SCROLL;
	}
}