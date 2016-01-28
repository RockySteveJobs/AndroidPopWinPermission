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
import android.text.TextUtils;

import io.github.bunnbylue.permssion.PermissionState;
import io.github.bunnbylue.permssion.Rom;

/**
 * Created by BunnyBlue on 1/18/16.
 */
public class HTCRom extends Rom {
	private float version = -1.0f;

	public HTCRom(Context context) {
		super(context);
	}

	public boolean isRuning() {
		String mVer = SystemPropertiesUtil.get("ro.build.sense.version");
		if (!TextUtils.isEmpty(mVer)) {
			try {
				this.version = Float.parseFloat(mVer);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (this.version != -1.0f) {
			return true;
		}
		return false;
	}

	public void initPermissionPolicy() {
	}

	public boolean openSystemSettings(int permissionType) {
		return false;
	}

	public boolean modifyPermissionDirect(int permissionType, PermissionState permissionState) {
		return false;
	}

	public String getRomName() {
		return "HTC Sense v" + this.version;
	}

	public int getRomSdkVersion() {
		return 1;
	}
}