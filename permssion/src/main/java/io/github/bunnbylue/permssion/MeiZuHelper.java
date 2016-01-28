/*
 *
 *        AndroidPopWinPermission Project
 *
 * Copyright(c) 2016 BunnyBlue <bunnyblueair@gmail.com>
 *
 *  This file is part of AndroidPopWinPermission.
 *
 *  AndroidPopWinPermission is free software: you can redistribute it and/or
 *   modify it under the terms of the GNU Lesser General Public
 *   License as published by the Free Software Foundation, either
 *   version 3 of the License, or (at your option) any later version.
 *
 *   AndroidPopWinPermission is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *   Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public
 *   License along with AndroidPopWinPermission.  If not, see <http://www.gnu.org/licenses/lgpl.txt>
 * *
 *  /
 */

package io.github.bunnbylue.permssion;

/**
 * Created by BunnyBlue on 1/18/16.
 */

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import io.github.bunnbylue.permssion.impl.SystemPropertiesUtil;

public class MeiZuHelper {
	public static int getVersion(Context context) {

		int ver = -1;
		String str = Build.DISPLAY;
		if (TextUtils.isEmpty(str) || !str.toLowerCase().contains("flyme")) {
			return -1;
		}
		try {
			String[] split = str.replaceAll(" ", "").toLowerCase().split("\\.");
			if (split.length < 2) {
				return -1;
			}
			try {
				return (Integer.valueOf(split[0].substring(split[0].length() - 1)).intValue() * 10) + Integer.valueOf(split[1].substring(0, 1)).intValue();
			} catch (Exception e) {

				ver = -1;
				return ver;
			}
		} catch (Exception e2) {
			return ver;
		}
	}

	public static int getPermissionStatus(Context context) {
		if (isMeizuSecurity(context)) {
			return getAppOpsStatus(context, 55);
		}
		return -1;
	}

	public static int getPermissionAbove40(Context context) {
		return getAppOpsStatus(context, 65);
	}

	public static int getPermissionStatusNOTIFICATION(Context context) {
		if (isMeizuSecurity(context)) {
			return getAppOpsStatus(context, 11);
		}
		return -1;
	}

	public static int getPermissionStatusNOTIFICATIONAbove40(Context context) {
		return getAppOpsStatus(context, 11);
	}

	public static boolean updatePERMISSION_AUTO_START(Context context, boolean status) {
		return updateAppOps(context, 65, status);
	}

	public static boolean updatePERMISSION_NOTIFICATION(Context context, boolean status) {
		return updateAppOps(context, 11, status);
	}

	private static int getAppOpsStatus(Context context, int opCode) {
		int intValue;
		try {
			Object systemService = context.getSystemService("appops");
			if (systemService == null) {
				return -1;
			}
			intValue = ((Integer) systemService.getClass().getMethod("checkOp", new Class[]{Integer.TYPE, Integer.TYPE, String.class}).invoke(systemService, new Object[]{Integer.valueOf(opCode), Integer.valueOf(SystemPropertiesUtil.getUid(context)), context.getPackageName()})).intValue();
			return intValue;
		} catch (Throwable th) {
			intValue = -1;
		}
		return intValue;
	}

	private static boolean updateAppOps(Context context, int opCode, boolean status) {

		try {
			Object systemService = context.getSystemService("appops");
			if (systemService == null) {
				return false;
			}
			int mValue;
			Method method = systemService.getClass().getMethod("setMode", new Class[]{Integer.TYPE, Integer.TYPE, String.class, Integer.TYPE});
			if (status) {
				mValue = 0;
			} else {
				mValue = 1;
			}
			method.invoke(systemService, new Object[]{Integer.valueOf(opCode), Integer.valueOf(SystemPropertiesUtil.getUid(context)), context.getPackageName(), Integer.valueOf(mValue)});
			return true;
		} catch (Throwable th) {
			th.printStackTrace();

		}
		return false;
	}

	public static boolean isMeizuSecurity(Context context) {
		if (!isSecVer(context)) {
			return false;
		}
		Boolean valueOf = Boolean.valueOf(false);
		try {
			Boolean bool;
			Class cls = Class.forName("android.os.Build");
			Field field = cls.getField("MeizuSecurity");
			field.setAccessible(true);
			bool = (Boolean) field.get(cls);
			return  bool;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (Error e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return valueOf.booleanValue();
	}

	public static boolean isSecVer(Context context) {
		int version = getVersion(context);
		return version >= 30 && version <= 40;
	}
}