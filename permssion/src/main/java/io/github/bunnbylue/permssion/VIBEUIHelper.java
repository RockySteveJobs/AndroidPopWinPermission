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

import android.content.Intent;
import android.os.Build;

import io.github.bunnbylue.permssion.impl.SystemPropertiesUtil;

public class VIBEUIHelper {
	public static boolean isVIBEUI() {
		return Build.FINGERPRINT.contains("VIBEUI_V2") || getRomName().contains("VIBEUI_V2");
	}

	public static String getRomName() {
		try {
			return SystemPropertiesUtil.get("ro.build.version.incremental");
		} catch (Exception e) {
			return "";
		}
	}

	public static Intent getAppNotificationOpenManagerIntent() {
		Intent intent = new Intent();
		intent.setClassName("com.lenovo.systemuiplus", "com.lenovo.systemuiplus.notifymanager.AppNotificationOpenManager");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		return intent;
	}

	public static Intent getAndroidAppNotificationOpenManager() {
		Intent intent = new Intent();
		intent.setClassName("com.android.systemui", "com.android.systemui.lenovo.settings.AppNotificationOpenManager");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		return intent;
	}

	public static Intent getLeSafeMainActivityIntent() {
		Intent intent = new Intent();
		intent.setClassName("com.lenovo.safecenter", "com.lenovo.safecenter.MainTab.LeSafeMainActivity");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		return intent;
	}

	public static Intent getBootSpeedActivityIntent() {
		Intent intent = new Intent();
		intent.setClassName("com.lenovo.safecenter", "com.lenovo.performancecenter.performance.BootSpeedActivity");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		return intent;
	}
}