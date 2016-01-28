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

import java.util.List;

import io.github.bunnbylue.permssion.PermissionPolicy;
import io.github.bunnbylue.permssion.PermissionState;
import io.github.bunnbylue.permssion.Rom;
import io.github.bunnbylue.permssion.VIBEUIHelper;

/**
 * Created by BunnyBlue on 1/18/16.
 */
public class VIBEUIRom extends Rom {
	private boolean isVibeUI;

	public VIBEUIRom(Context context) {
		super(context);
		this.isVibeUI = false;
		this.isVibeUI = VIBEUIHelper.isVIBEUI();
	}

	public static boolean existIntent(Context context, Intent intent) {
		List queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536);
		return queryIntentActivities != null && queryIntentActivities.size() > 0;
	}

	public boolean isRuning() {
		return this.isVibeUI;
	}

	public void initPermissionPolicy() {
		if (this.isVibeUI && existIntent(this.mContext, VIBEUIHelper.getLeSafeMainActivityIntent())) {
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_TRUST).mState = PermissionState.UNKNOWN;
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_TRUST).mModifyState = 6;
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_TRUST).mIntent = VIBEUIHelper.getLeSafeMainActivityIntent();
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_TRUST).mTips = "";
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_AUTO_START).mState = PermissionState.UNKNOWN;
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_AUTO_START).mModifyState = 6;
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_AUTO_START).mIntent = VIBEUIHelper.getAndroidAppNotificationOpenManager();
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_AUTO_START).mTips = "";
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_NOTIFICATION).mState = PermissionState.UNKNOWN;
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_NOTIFICATION).mModifyState = 2;
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_NOTIFICATION).mIntent = VIBEUIHelper.getAppNotificationOpenManagerIntent();
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_NOTIFICATION).mTips = "";
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_PRIVACY).mState = PermissionState.UNKNOWN;
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_PRIVACY).mModifyState = 2;
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_PRIVACY).mIntent = VIBEUIHelper.getLeSafeMainActivityIntent();
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_PRIVACY).mTips = "";
		}
	}

	public boolean openSystemSettings(int permissionType) {

		this.mContext.startActivity(this.mPermissionPolicy.getPermission(permissionType).mIntent.setFlags(1418002432).addFlags(32768));
		return true;
		//return false;
	}

	public boolean modifyPermissionDirect(int permissionType, PermissionState permissionState) {
		return false;
	}

	public String getRomName() {
		return VIBEUIHelper.getRomName();
	}

	public int getRomSdkVersion() {
		return 1;
	}
}