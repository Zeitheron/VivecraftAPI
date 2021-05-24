package org.zeith.viveapi.mixins;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.vivecraft.gameplay.OpenVRPlayer;
import org.zeith.viveapi.api.IVRPlayer;
import org.zeith.viveapi.api.VivecraftAPI;
import org.zeith.viveapi.api.event.VRPollEvent;

@SideOnly(Side.CLIENT)
@Mixin(value = OpenVRPlayer.class, remap = false)
public class MixinOpenVRPlayer
{
	@Inject(
			method = "postPoll",
			at = @At("TAIL")
	)
	public void postPollEvent(CallbackInfo ci)
	{
		VivecraftAPI.VR_BUS.post(new VRPollEvent(IVRPlayer.VRDataStage.ROOM_PRE));
	}
}