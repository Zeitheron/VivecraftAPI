package org.zeith.viveapi.api;

public interface IVRPlayer
{
	boolean isActive();

	IVRDeviceInfo getDeviceInfo(VRDataStage stage, VRDevice device);

	enum VRDevice
	{
		HMD,
		EYE_RIGHT,
		EYE_LEFT,
		CONTROLLER_RIGHT,
		CONTROLLER_LEFT;
	}

	enum VRDataStage
	{
		ROOM_PRE, //just latest polling data, origin = 0,0,0, rotation = 0, scaleXZ = walkMultiplier
		WORLD_PRE, //latest polling data but last tick's origin, rotation, scale
		ROOM_POST, //recalc here in the odd case the walk multiplier changed
		WORLD_POST, //this is used for rendering and the server. _render is interpolated between this and _pre.
		WORLD_RENDER; // using interpolated origin, scale, rotation
	}
}