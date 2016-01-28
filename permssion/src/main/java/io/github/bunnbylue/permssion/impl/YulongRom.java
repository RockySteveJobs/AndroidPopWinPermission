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
import android.os.Build;

import io.github.bunnbylue.permssion.PermissionPolicy;
import io.github.bunnbylue.permssion.PermissionState;
import io.github.bunnbylue.permssion.Rom;

/**
 * Created by BunnyBlue on 1/18/16.
 */
public class YulongRom extends Rom {

	public YulongRom(Context context) {
		super(context);
	}

	public boolean isRuning() {
		return Build.MODEL.toLowerCase().contains("coolpad") || Build.FINGERPRINT.toLowerCase().contains("coolpad");

	}

	public void initPermissionPolicy() {
		if (SystemPropertiesUtil.get(this.mContext, "com.yulong.android.secclearmaster")) {
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_AUTO_START).mState = PermissionState.UNKNOWN;
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_AUTO_START).mModifyState = 2;
			Intent intent = new Intent();
			intent.setClassName("com.yulong.android.secclearmaster", "com.yulong.android.secclearmaster.ui.activity.improve.ForbidAutoRunListActivity");
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_AUTO_START).mIntent = intent;
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_AUTO_START).mTips = "";
		}
		this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_NOTIFICATION).mState = PermissionState.UNKNOWN;
		this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_NOTIFICATION).mModifyState = 2;
		Intent intent = new Intent();
		intent.setClassName("com.yulong.android.seccenter", "com.yulong.android.ntfmanager.AppNtfListActivity");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_NOTIFICATION).mIntent = intent;
		this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_NOTIFICATION).mTips = "";
		this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_PRIVACY).mState = PermissionState.UNKNOWN;
		this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_PRIVACY).mModifyState = 2;
		//Intent intent = new Intent();
		intent.setClassName("com.yulong.android.seccenter", "com.yulong.android.seccenter.dataprotection.ui.AppListActivity");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_PRIVACY).mIntent = intent;
		this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_PRIVACY).mTips = "";
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
		return Build.MODEL;
	}

	public int getRomSdkVersion() {
		return 1;
	}

}
