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

import android.content.Context;

/**
 * Created by BunnyBlue on 1/18/16.
 */

public abstract class Rom {
	public static final String ACTION_NOTIFICATION_LISTENER_SETTINGS = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";
	protected Context mContext = null;
	protected PermissionPolicy mPermissionPolicy = null;

	public Rom(Context context) {
		this.mContext = context;
	}

	public abstract String getRomName();

	public abstract int getRomSdkVersion();

	public abstract void initPermissionPolicy();

	public abstract boolean modifyPermissionDirect(int permissionType, PermissionState permissionState);

	public abstract boolean openSystemSettings(int permissionType);

	public abstract boolean isRuning();

	public PermissionPolicy getPermissionPolicy(boolean isNew) {
		if (this.mPermissionPolicy != null && !isNew) {
			return this.mPermissionPolicy;
		}
		this.mPermissionPolicy = new PermissionPolicy(this.mContext);
		initPermissionPolicy();
		modifyPermissionDirect(PermissionPolicy.PERMISSION_FLOAT_VIEW, PermissionState.ALLOWED);
		return this.mPermissionPolicy;
	}


}

