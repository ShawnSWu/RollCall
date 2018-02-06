package com.shawn.newrollcall.login.view;

import android.content.Context;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.shawn.newrollcall.R;

/**
 * Created by Shawn Wu on 2017/11/29
 *
 */

public class LodingFactory{

    public static KProgressHUD getLodingAnimation(int type,Context context) {

        KProgressHUD lodingAnimation = KProgressHUD.create(context);

        switch (type) {

            case R.string.please_wait:
                lodingAnimation.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setDetailsLabel(context.getResources().getString(R.string.please_wait))
                    .setCancellable(true)
                    .setAnimationSpeed(1)
                    .setDimAmount(0.5f);
                break;

        }

        return lodingAnimation;
    }
}
