package org.zeith.viveapi.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.zeith.viveapi.api.IKeyBindings;
import org.zeith.viveapi.api.IVRDeviceInfo;
import org.zeith.viveapi.api.IVRPlayer;
import org.zeith.viveapi.api.VivecraftAPI;

@SideOnly(Side.CLIENT)
//@Mod.EventBusSubscriber(Side.CLIENT)
public class FlamesTest
{
	@SubscribeEvent
	public static void clientTick(TickEvent.ClientTickEvent e)
	{
		if(e.phase == TickEvent.Phase.END)
		{
			WorldClient wc = Minecraft.getMinecraft().world;

			if(wc != null)
			{
				VivecraftAPI api = VivecraftAPI.getAPI();
				IKeyBindings keys = api.getKeyBindings();
				IVRPlayer player = api.getVRPlayer();

				if(keys.isInteractPressedOnHotbar())
				{
					IVRDeviceInfo i = player.getDeviceInfo(IVRPlayer.VRDataStage.WORLD_POST, IVRPlayer.VRDevice.CONTROLLER_RIGHT);
					Vec3d pos = i.getPosition();
					Vec3d dir = i.getDirection().scale(0.01F);
					wc.spawnParticle(EnumParticleTypes.FLAME, pos.x, pos.y, pos.z, dir.x, dir.y, dir.z);
				}
			}
		}
	}
}