package org.zeith.viveapi.api;

import net.minecraft.util.math.Vec3d;

import javax.vecmath.Matrix4f;

public interface IVRDeviceInfo
{
	Vec3d getPosition();

	Vec3d getDirection();

	Vec3d getCustomVector(Vec3d axis);

	float getYaw();

	float getPitch();

	float getRoll();

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