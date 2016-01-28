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

import io.github.bunnbylue.permssion.PermissionState;
import io.github.bunnbylue.permssion.Rom;
import io.github.bunnbylue.permssion.VivoHelper;

/**
 * Created by BunnyBlue on 1/18/16.
 */
public class VivoRom extends Rom {
	public VivoRom(Context context) {
		super(context);
	}

	public boolean isRuning() {
		return VivoHelper.isFunTouch();
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
		return VivoHelper.getRomName();
	}

	public int getRomSdkVersion() {
		return 1;
	}
}
