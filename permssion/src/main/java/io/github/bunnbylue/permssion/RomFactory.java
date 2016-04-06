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

import io.github.bunnbylue.permssion.impl.EUIRom;
import io.github.bunnbylue.permssion.impl.EmotionUIRom;
import io.github.bunnbylue.permssion.impl.HTCRom;
import io.github.bunnbylue.permssion.impl.MeiZuRom;
import io.github.bunnbylue.permssion.impl.MiuiRom;
import io.github.bunnbylue.permssion.impl.OppoRom;
import io.github.bunnbylue.permssion.impl.SamsungRom;
import io.github.bunnbylue.permssion.impl.VIBEUIRom;
import io.github.bunnbylue.permssion.impl.VivoRom;
import io.github.bunnbylue.permssion.impl.YulongRom;
import io.github.bunnbylue.permssion.impl.ZTERom;

/**
 * Created by BunnyBlue on 1/18/16.
 */
public class RomFactory {

	static Rom mRom = null;

	public static Rom createRom(Context context) {
		return createRom(context, false);
	}


	private static Rom createRom(Context context, boolean forceNew) {
		if (mRom == null || forceNew) {
			mRom = loadRomInternal(context);
		}
		return mRom;
	}


	private static Rom loadRomInternal(Context context) {
		AppOpsManagerHelper.init();
		Rom mRom = new MiuiRom(context);
		if (mRom.isRuning()) {
			return mRom;
		}
		mRom = new EmotionUIRom(context);
		if (mRom.isRuning()) {
			return mRom;
		}
		mRom = new MeiZuRom(context);
		if (mRom.isRuning()) {
			return mRom;
		}
		mRom = new ZTERom(context);
		if (mRom.isRuning()) {
			return mRom;
		}
		mRom = new YulongRom(context);
		if (mRom.isRuning()) {
			return mRom;
		}
		mRom = new VIBEUIRom(context);
		if (mRom.isRuning()) {
			return mRom;
		}
		mRom = new HTCRom(context);
		if (mRom.isRuning()) {
			return mRom;
		}
		mRom = new OppoRom(context);
		if (mRom.isRuning()) {
			return mRom;
		}
		mRom = new VivoRom(context);
		if (mRom.isRuning()) {
			return mRom;
		}
		mRom = new SamsungRom(context);
		if (mRom.isRuning()) {
			return mRom;
		}
		mRom = new EUIRom(context);
		return mRom.isRuning() ? mRom : null;

	}


}