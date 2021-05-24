package org.zeith.viveapi.mixins;

import jopenvr.JOpenVRLibrary;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import org.vivecraft.control.HapticScheduler;
import org.vivecraft.control.VRInputAction;
import org.vivecraft.gameplay.screenhandlers.GuiHandler;
import org.vivecraft.provider.MCOpenVR;
import org.vivecraft.utils.Vector2;
import org.vivecraft.utils.Vector3;
import org.zeith.viveapi.api.VivecraftAPI;
import org.zeith.viveapi.api.event.VRHotbarPositionEvent;
import org.zeith.viveapi.api.event.VRScrollEvent;

import static org.zeith.viveapi.util.FloatShenanigans.equal;

@SideOnly(Side.CLIENT)
@Mixin(value = MCOpenVR.class, remap = false)
public abstract class MixinMCOpenVR
{
	@Shadow
	public static VRInputAction getInputAction(KeyBinding keyBinding)
	{
		return null;
	}

	@Accessor
	public static HapticScheduler getHapticScheduler()
	{
		return null;
	}

	@ModifyArgs(
			method = "processHotbar",
			at = @At(
					value = "INVOKE",
					target = "Lorg/vivecraft/utils/Matrix4f;transform(Lorg/vivecraft/utils/Vector3;)Lorg/vivecraft/utils/Vector3;"
			)
	)
	private static void hotbarVectors(Args args)
	{
		Vector3 vec = args.get(0);

		float x = vec.getX();
		float xAbs = Math.abs(x);

		float y = vec.getY();
		float z = vec.getZ();

		boolean reverseHands = false;
		VRHotbarPositionEvent.HudLock lock = null;
		VRHotbarPositionEvent.PositionType type = null;

		if(equal(xAbs, 0.02F) && equal(y, 0.05F))
		{
			lock = VRHotbarPositionEvent.HudLock.WRIST;
			reverseHands = x < 0F;
			if(equal(z, 0.26F)) type = VRHotbarPositionEvent.PositionType.START;
			if(equal(z, 0.01F)) type = VRHotbarPositionEvent.PositionType.END;
		}

		if(equal(xAbs, 0.18F) && equal(y, 0.08F) && equal(z, -0.01F))
		{
			reverseHands = x > 0;
			lock = VRHotbarPositionEvent.HudLock.HAND;
			type = VRHotbarPositionEvent.PositionType.START;
		}

		if(equal(xAbs, 0.19F) && equal(y, 0.04F) && equal(z, -0.08F))
		{
			reverseHands = x < 0;
			lock = VRHotbarPositionEvent.HudLock.HAND;
			type = VRHotbarPositionEvent.PositionType.END;
		}

		if(lock != null && type != null)
		{
			VRHotbarPositionEvent evt = new VRHotbarPositionEvent(reverseHands, lock, type, x, y, z);
			VivecraftAPI.VR_BUS.post(evt);
			args.set(0, new Vector3(x, y, z));
		}
	}

	@Inject(
			method = "processScrollInput",
			at = @At("HEAD"),
			cancellable = true
	)
	private static void processScrollInput(KeyBinding keyBinding, Runnable upCallback, Runnable downCallback, CallbackInfo ci)
	{
		VRInputAction action = getInputAction(keyBinding);
		if(action.isEnabled() && action.getLastOrigin() != JOpenVRLibrary.k_ulInvalidInputValueHandle && action.getAxis2D(true).getY() != 0)
		{
			Vector2 vec = action.getAxis2D(false);
			Vector2 dvec = action.getAxis2D(true);
			if(VivecraftAPI.VR_BUS.post(new VRScrollEvent(keyBinding == GuiHandler.keyScrollAxis ? VRScrollEvent.ScrollEventType.SCROLL_AXIS : VRScrollEvent.ScrollEventType.HOTBAR_SCROLL, vec.getX(), vec.getY(), dvec.getX(), dvec.getY())))
				ci.cancel();
		}
	}
}