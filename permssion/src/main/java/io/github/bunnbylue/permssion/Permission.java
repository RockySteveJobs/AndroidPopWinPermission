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

import android.content.Intent;

/**
 * Created by BunnyBlue on 1/18/16.
 */
public class Permission {
	public static final int PERMISSION_MODIFY_BY_ACTIVITY = 2;
	public static final int PERMISSION_MODIFY_BY_ACTIVITY_ACC = 4;
	public static final int PERMISSION_MODIFY_BY_DIRECT = 1;
	public static final int PERMISSION_MODIFY_NONE = 0;
	public Intent mIntent;
	public int mModifyState = 0;
	public int mPermissionId = 0;
	public PermissionState mState = PermissionState.UNKNOWN;
	public String mTips;
}