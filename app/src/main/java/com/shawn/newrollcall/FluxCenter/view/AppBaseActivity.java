package com.shawn.newrollcall.FluxCenter.view;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.widget.Toast;


/**
 * Created by Shawn.Wu on 2017/11/12.
 */
//處理View層的code
public abstract class AppBaseActivity extends BaseActivity {

    private ViewDataBinding viewDataBinding;

    protected <T extends ViewDataBinding> T getViewbinding(int layoutId) {
        viewDataBinding = DataBindingUtil.setContentView(this,layoutId);
        return (T)viewDataBinding;
    }
    
    protected void toast(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}
