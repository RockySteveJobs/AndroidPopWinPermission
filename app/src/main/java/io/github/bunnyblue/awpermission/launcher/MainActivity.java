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

package io.github.bunnyblue.awpermission.launcher;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import io.github.bunnbylue.permssion.Permission;
import io.github.bunnbylue.permssion.PermissionPolicy;
import io.github.bunnbylue.permssion.PermissionState;
import io.github.bunnbylue.permssion.Rom;
import io.github.bunnbylue.permssion.RomFactory;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Rom mRom = RomFactory.createRom(this);
		Permission mPermssion = mRom.getPermissionPolicy(false).getPermission(PermissionPolicy.PERMISSION_FLOAT_VIEW);
		if (mPermssion.mState != PermissionState.ALLOWED) {

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("需要悬浮窗权限");
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					mRom.openSystemSettings(PermissionPolicy.PERMISSION_FLOAT_VIEW);
				}
			}).create().show();
		}
	}
}
