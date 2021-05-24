package org.zeith.viveapi.client.impl;

import net.minecraft.util.math.Vec3d;
import org.vivecraft.api.VRData;
import org.vivecraft.gameplay.OpenVRPlayer;
import org.zeith.viveapi.api.IVRDeviceInfo;
import org.zeith.viveapi.api.IVRPlayer;

import javax.vecmath.Matrix4f;

public class VRPlayerImpl
		implements IVRPlayer
{
	@Override
	public boolean isActive()
	{
		return OpenVRPlayer.get() != null;
	}

	VRData getVRData(OpenVRPlayer vrPlayer, VRDataStage stage)
	{
		switch(stage)
		{
			case ROOM_PRE:
				return vrPlayer.vrdata_room_pre;
			case WORLD_PRE:
				return vrPlayer.vrdata_world_pre;
			case ROOM_POST:
				return vrPlayer.vrdata_room_post;
			case WORLD_POST:
				return vrPlayer.vrdata_world_post;
			case WORLD_RENDER:
				return vrPlayer.vrdata_world_render;
		}
		return null;
	}

	VRData.VRDevicePose getVRPose(VRData data, VRDevice device)
	{
		switch(device)
		{
			case HMD:
				return data.hmd;
			case CONTROLLER_LEFT:
				return data.c1;
			case CONTROLLER_RIGHT:
				return data.c0;
			case EYE_LEFT:
				return data.eye0;
			case EYE_RIGHT:
				return data.eye1;
		}
		return null;
	}

	@Override
	public IVRDeviceInfo getDeviceInfo(VRDataStage stage, VRDevice device)
	{
		final OpenVRPlayer vrPlayer = OpenVRPlayer.get();
		VRData data = getVRData(vrPlayer, stage);
		VRData.VRDevicePose pose = getVRPose(data, device);
		return new VRDeviceInfoImpl(pose);
	}

	public static class VRDeviceInfoImpl
			implements IVRDeviceInfo
	{
		final VRData.VRDevicePose pose;

		public VRDeviceInfoImpl(VRData.VRDevicePose pose)
		{
			this.pose = pose;
		}

		@Override
		public Vec3d getPosition()
		{
			return pose.getPosition();
		}

		@Override
		public Vec3d getDirection()
		{
			return pose.getDirection();
		}

		@Override
		public Vec3d getCustomVector(Vec3d axis)
		{
			return pose.getCustomVector(axis);
		}

		@Override
		public float getYaw()
		{
			return pose.getYaw();
		}

		@Override
		public float getPitch()
		{
			return pose.getPitch();
		}

		@Override
		public float getRoll()
		{
			return pose.getRoll();
		}

		@Override
		public Matrix4f getMatrix()
		{
			float[][] M = pose.getMatrix().M;
			return new Matrix4f(
					M[0][0], M[0][1], M[0][2], M[0][3],
					M[1][0], M[1][1], M[1][2], M[1][3],
					M[2][0], M[2][1], M[2][2], M[2][3],
					M[3][0], M[3][1], M[3][2], M[3][3]
			);
		}
	}
}