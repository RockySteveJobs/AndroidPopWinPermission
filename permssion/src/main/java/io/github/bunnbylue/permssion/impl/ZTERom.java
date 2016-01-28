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

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.Locale;

import io.github.bunnbylue.permssion.PermissionPolicy;
import io.github.bunnbylue.permssion.PermissionState;
import io.github.bunnbylue.permssion.Rom;

/**
 * Created by BunnyBlue on 1/18/16.
 */
public class ZTERom extends Rom {

	private PackageManager packageManager = null;

	public ZTERom(Context context) {
		super(context);
		this.packageManager = context.getPackageManager();
	}

	public boolean isRuning() {

		if ((Build.MANUFACTURER.toLowerCase(Locale.US).contains("nubia") || Build.FINGERPRINT.toLowerCase(Locale.US).contains("nubia") || Build.MANUFACTURER.toLowerCase(Locale.US).contains("zte") || Build.FINGERPRINT.toLowerCase(Locale.US).contains("zte")) && SystemPropertiesUtil.get(this.mContext, "com.zte.heartyservice")) {
			return true;
		}
		return false;
	}

	public void initPermissionPolicy() {
		this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_PRIVACY).mState = PermissionState.UNKNOWN;
		this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_PRIVACY).mModifyState = 2;
		Intent intent = new Intent();
		intent.setAction("com.zte.heartyservice.intent.action.startActivity.PERMISSION_SCANNER");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_PRIVACY).mIntent = intent;
		this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_PRIVACY).mTips = SystemPropertiesUtil.getPackageLabel(this.mContext);
	}

	public boolean openSystemSettings(int permissionType) {
		try {
			this.mContext.startActivity(this.mPermissionPolicy.getPermission(permissionType).mIntent);
			return true;
		} catch (Throwable th) {
			return false;
		}
	}

	public boolean modifyPermissionDirect(int permissionType, PermissionState permissionState) {
		return false;
	}

	public String getRomName() {
		return "ZTE Rom";
	}

	public int getRomSdkVersion() {
		return 1;
	}

}
