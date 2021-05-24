package org.zeith.viveapi.api;

import net.minecraft.util.math.Vec3d;

import javax.vecmath.Matrix4f;

public interface IVRDeviceInfo
{
	/**
	 * Get the position of this device
	 */
	Vec3d getPosition();

	/**
	 * Get the direction vector of this device
	 */
	Vec3d getDirection();

	/**
	 * Get the custom vector relative to this device
	 */
	Vec3d getCustomVector(Vec3d axis);

	/**
	 * Get yaw of this device
	 */
	float getYaw();

	/**
	 * Get pitch of this device
	 */
	float getPitch();

	/**
	 * Get roll of this device
	 */
	float getRoll();

	/**
	 * Get transformation matrix of this device
	 */
	Matrix4f getMatrix();

	IVRDeviceInfo DUMMY = new IVRDeviceInfo()
	{
		@Override
		public Vec3d getPosition()
		{
			return Vec3d.ZERO;
		}

		@Override
		public Vec3d getDirection()
		{
			return Vec3d.ZERO;
		}

		@Override
		public Vec3d getCustomVector(Vec3d axis)
		{
			return Vec3d.ZERO;
		}

		@Override
		public float getYaw()
		{
			return 0;
		}

		@Override
		public float getPitch()
		{
			return 0;
		}

		@Override
		public float getRoll()
		{
			return 0;
		}

		@Override
		public Matrix4f getMatrix()
		{
			return new Matrix4f();
		}
	};
}