package org.zeith.viveapi;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zeith.viveapi.api.VivecraftAPI;
import org.zeith.viveapi.client.impl.VivecraftAPIImpl;
import org.zeith.viveapi.util.Reflectors;
import org.zeith.viveapi.util.VivecraftClasses;

import java.lang.reflect.Field;
import java.util.Optional;

@Mod(modid = "viveapi", name = "Vivecraft API", version = "@VERSION@", certificateFingerprint = "9f5e2a811a8332a842b34f6967b7db0ac4f24856", updateJSON = "http://dccg.herokuapp.com/api/fmluc/485932")
public class ViveAPIMod
{
	public static final Side LAUNCH_SIDE = FMLCommonHandler.instance().getSide();
	public static final Logger LOG = LogManager.getLogger("VivecraftAPI");

	public ViveAPIMod()
	{
		if(LAUNCH_SIDE.isClient())
		{
			if(VivecraftClasses.MCOpenVR.isPresent())
			{
				LOG.info("Detected MCOpenVR class, ViveCraft is probably present!");
				try
				{
					Field f = Reflectors.getDeclaredField(Optional.of(VivecraftAPI.class), "INSTANCE").orElse(null);
					f.set(null, new VivecraftAPIImpl());
					LOG.info("Installed VivecraftAPI implementation.");
				} catch(ReflectiveOperationException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	@Mod.EventHandler
	public void construction(FMLConstructionEvent e)
	{
	}
}