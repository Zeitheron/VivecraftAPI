package org.zeith.viveapi.api.event;

import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Objects;

/**
 * This event is a hook to 2-axial scrolling in VR.
 * Cancelling this event will prevent Vivecraft scroll code from running
 *
 * Fired on {@link org.zeith.viveapi.api.VivecraftAPI#VR_BUS}
 */
@Cancelable
public class VRScrollEvent
		extends Event
{
	public final ScrollEventType type;
	public final float valueX, valueY, deltaX, deltaY;

	public VRScrollEvent(ScrollEventType type, float valueX, float valueY, float deltaX, float deltaY)
	{
		this.type = type;
		this.valueX = valueX;
		this.valueY = valueY;
		this.deltaX = deltaX;
		this.deltaY = deltaY;

		System.out.println(toString());
	}

	@Override
	public String toString()
	{
		return "VRScrollEvent{" +
				"keyBinding=" + type +
				", valueX=" + valueX +
				", valueY=" + valueY +
				", deltaX=" + deltaX +
				", deltaY=" + deltaY +
				'}';
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(type, valueX, valueY, deltaX, deltaY);
	}

	public enum ScrollEventType
	{
		SCROLL_AXIS,
		HOTBAR_SCROLL;
	}
}