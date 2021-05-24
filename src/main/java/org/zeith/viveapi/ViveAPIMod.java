package org.zeith.viveapi;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zeith.viveapi.util.VivecraftClasses;

@Mod(modid = "viveapi", name = "Vivecraft API", version = "@VERSION@", certificateFingerprint = "@FINGERPRINT@")
public class ViveAPIMod
{
	public static final Side LAUNCH_SIDE = FMLCommonHandler.instance().getSide();
	public static final Logger LOG = LogManager.getLogger("ViveAPI");

	public ViveAPIMod()
	{
		if(LAUNCH_SIDE.isClient())
		{
			if(VivecraftClasses.MCOpenVR.isPresent())
			{
				LOG.info("Detected MCOpenVR class, ViveCraft is probably present!");
			}
		}
	}

	@Mod.EventHandler
	public void construction(FMLConstructionEvent e)
	{
	}
}