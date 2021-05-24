package org.zeith.viveapi.util;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.vivecraft.settings.VRSettings;

import java.util.Optional;

public class VivecraftClasses
{
	public static final Optional<Class<?>> MCOpenVR = Reflectors.getClass("org.vivecraft.provider.MCOpenVR");

	@SideOnly(Side.CLIENT)
	private static VRSettings cachedSettings;

	@SideOnly(Side.CLIENT)
	public static VRSettings getVRSettings()
	{
		return getVRSettings(Minecraft.getMinecraft());
	}

	@SideOnly(Side.CLIENT)
	public static VRSettings getVRSettings(Minecraft mc)
	{
		if(cachedSettings != null) return cachedSettings;
		return cachedSettings = Reflectors.<VRSettings> getValue(Reflectors.getField(Optional.ofNullable(mc.getClass()), "vrSettings"), mc).orElse(null);
	}
}