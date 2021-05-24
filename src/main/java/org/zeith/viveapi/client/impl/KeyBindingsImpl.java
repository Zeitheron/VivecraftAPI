package org.zeith.viveapi.client.impl;

import org.vivecraft.provider.MCOpenVR;
import org.zeith.viveapi.api.IKeyBindings;

public class KeyBindingsImpl
		implements IKeyBindings
{
	@Override
	public boolean isInteractPressedOnHotbar()
	{
		return MCOpenVR.keyVRInteract.isKeyDown();
	}
}