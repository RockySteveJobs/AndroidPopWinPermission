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

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;

import java.lang.reflect.Method;

public class MiuiHelper {
	private static Object object = new Object();

	public static boolean getFLOAT_VIEWStatus(Context context) {
		try {
			int flag;
			ApplicationInfo applicationInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo;
			if (VERSION.SDK_INT < 19) {
				flag = 134217728;
			} else {
				flag = 33554432;
			}
			if ((flag & applicationInfo.flags) != 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}

	public static boolean modifyPermissionAutoStart(Context context, boolean enable) {
		try {
			Class cls = Class.forName("android.miui.AppOpsUtils");
			if (cls != null) {
				Method declaredMethod = cls.getDeclaredMethod("setApplicationAutoStart", new Class[]{Context.class, String.class, Boolean.TYPE});
				if (declaredMethod != null) {
					declaredMethod.invoke(cls, new Object[]{context, context.getPackageName(), Boolean.valueOf(enable)});
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean getPERMISSION_ROOTStatus(Context context) {
		return (getStatus(context) & 512) != 0;
	}

	public static boolean modifyPermissionRoot(Context context, boolean enable) {

		synchronized (MiuiHelper.object) {
			int status = getStatus(context);
			if (enable) {
				status |= 512;
			} else {
				status &= 67108351;
			}
			return modifyPermission(context, status);
		}

	}

	public static boolean modifyPermissionFloatViewDirect(Context context, boolean enable) {

		synchronized (MiuiHelper.object) {
			int status = getStatus(context);
			if (enable) {
				status |= 33554432;
			} else {
				status &= 33554431;
			}
			return modifyPermission(context, status);
		}

	}

	private static boolean modifyPermission(Context context, int flag) {
		String packageName = context.getPackageName();
		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put("userAccept", Integer.valueOf(flag));
			context.getContentResolver().update(Uri.parse("content://com.lbe.security.miui.permmgr/active"), contentValues, "pkgName=?", new String[]{packageName});
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static int getStatus(Context context) {
		Cursor query = null;
		String packageName = context.getPackageName();
		try {
			query = context.getContentResolver().query(Uri.parse("content://com.lbe.security.miui.permmgr/active"), null, "pkgName=?", new String[]{packageName}, null);
			if (query == null) {
				return 0;
			}
			if (query.moveToFirst()) {
				int status = query.getInt(query.getColumnIndex("userAccept"));
				if (query == null) {
					return status;
				}
			}
			query.close();
		} catch (Exception e) {

			return -1;
		}


		return -1;
	}
}