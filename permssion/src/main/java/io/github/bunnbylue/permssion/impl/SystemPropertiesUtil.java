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

package io.github.bunnbylue.permssion.impl;

/**
 * Created by BunnyBlue on 1/18/16.
 */

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Process;

public class SystemPropertiesUtil {

	public static int getUid(Context context) {
		return Process.myUid();
	}

	public static String getPackageLabel(Context context) {
		try {
			return (String) context.getApplicationInfo().loadLabel(context.getPackageManager());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String get(String str) {
		try {
			Class cls = Class.forName("android.os.SystemProperties");
			Object invoke = cls.getMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{str});
			if (invoke == null || !(invoke instanceof String)) {
				return null;
			}
			return (String) invoke;
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean get(Context context, String pkg) {
		PackageManager packageManager = context.getPackageManager();
		if (packageManager == null) {
			return false;
		}
		PackageInfo packageInfo = null;
		try {
			packageInfo = packageManager.getPackageInfo(pkg, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (packageInfo != null) {
			return true;
		}
		return false;
	}


}