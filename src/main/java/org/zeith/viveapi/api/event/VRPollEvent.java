package org.zeith.viveapi.api.event;

import net.minecraftforge.fml.common.eventhandler.Event;
import org.zeith.viveapi.api.IVRPlayer;

/**
 * This event is a hook for getting VR data.
 * Called when the data was just polled, please pay attention to the {@link #stage} when subscribing to the event.
 * <p>
 * Fired on {@link org.zeith.viveapi.api.VivecraftAPI#VR_BUS}
 */
public class VRPollEvent
		extends Event
{
	public final IVRPlayer.VRDataStage stage;

	public VRPollEvent(IVRPlayer.VRDataStage stage)
	{
		this.stage = stage;
	}

	@Override
	public String toString()
	{
		return "VRPollEvent{" +
				"stage=" + stage +
				'}';
	}
}