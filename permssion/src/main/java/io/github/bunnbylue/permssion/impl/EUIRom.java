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

import io.github.bunnbylue.permssion.PermissionPolicy;
import io.github.bunnbylue.permssion.PermissionState;
import io.github.bunnbylue.permssion.Rom;

/**
 * Created by BunnyBlue on 1/18/16.
 */
public class EUIRom extends Rom {
	private int version = -1;
	private String romName = "EUI ";

	public EUIRom(Context context) {
		super(context);
		if ("5.0".equalsIgnoreCase(SystemPropertiesUtil.get("ro.letv.eui"))) {
			this.version = 50;
			this.romName += SystemPropertiesUtil.get("ro.letv.release.version");
		}
	}

	public boolean isRuning() {
		if (this.version == 50) {
			return true;
		}
		return false;
	}

	public void initPermissionPolicy() {
		if (this.version == 50) {
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_AUTO_START).mModifyState = 6;
			Intent intent = new Intent();
			intent.setClassName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity");
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_AUTO_START).mIntent = intent;
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_AUTO_START).mTips = "";
		}
		if (this.version == 50) {
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_FLOAT_VIEW).mModifyState = 6;
			Intent intent = new Intent();
			intent.setClassName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AppActivity");
			this.mPermissionPolicy.getPermission(PermissionPolicy.PERMISSION_FLOAT_VIEW).mIntent = intent;
		}
	}

	public boolean openSystemSettings(int permissionType) {

		this.mContext.startActivity(this.mPermissionPolicy.getPermission(permissionType).mIntent.setFlags(1418002432));
		return true;
	}

	public boolean modifyPermissionDirect(int permissionType, PermissionState permissionState) {
		return false;
	}

	public String getRomName() {
		return this.romName;
	}

	public int getRomSdkVersion() {
		return 1;
	}
}