package com.market.view.activity.base;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.market.R;
import com.market.bean.LoginResp;
import com.market.bean.RegisterResp;
import com.market.http.RetrofitHelper;
import com.market.utils.RotateAnimationUtil;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class LoginActivity extends BaseCompatActivity {
    private FrameLayout container;

    private LinearLayout container1;

    private LinearLayout container2;

    private RotateAnimationUtil rotateAnimationUtil;

    private ImageView bt_towhile;
    private ImageView bt_toblack;
    private SharedPreferences sp;
    private EditText et_text;
    private EditText et_password;
    private String number;
    private String password;
    /**
     * 保存的文件名字
     */
    public static String TEXT = "userinfo";
    public static String REGISTER = "register";
    /**
     * 保存的UserId
     *
     * @param savedInstanceState
     */
    public static String USERId_LOGIN = "useridLogin";
    public static String USERId_REGISTER = "useridRegister";
    private EditText et_register_text;
    private EditText et_register_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("250");
        if (stringExtra != null) {
            SharedPreferences sp = getSharedPreferences(TEXT, Context.MODE_PRIVATE);
            String string = sp.getString("name", "");
            Log.i("test", string);
            et_text.setText(string);
        }

        bt_towhile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                rotateAnimationUtil.applyRotateAnimation(1, 0, 90);
            }
        });
        bt_toblack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                rotateAnimationUtil.applyRotateAnimation(-1, 0, -90);
            }
        });

        // 设置当前View控件的缓存
        container
                .setPersistentDrawingCache(ViewGroup.PERSISTENT_ANIMATION_CACHE);
        container2.setVisibility(View.GONE);
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected int getLayoutId() {

        return R.layout.activity_login;
    }

    /**
     * 初始化数据
     * @param savedInstanceState
     */
    @Override
    protected void initView(Bundle savedInstanceState) {
        sp = this.getSharedPreferences(TEXT, Context.MODE_PRIVATE);
        et_text = ((EditText) findViewById(R.id.editText));
        et_password = ((EditText) findViewById(R.id.et_password));
        et_register_text = ((EditText) findViewById(R.id.register_editText));
        et_register_password = ((EditText) findViewById(R.id.et_register_password));
        container = (FrameLayout) findViewById(R.id.container);
        bt_toblack = (ImageView) findViewById(R.id.bt_toblack);
        bt_towhile = (ImageView) findViewById(R.id.bt_towhile);

        container1 = (LinearLayout) findViewById(R.id.container1);
        container2 = (LinearLayout) findViewById(R.id.container2);

        rotateAnimationUtil = new RotateAnimationUtil(container, container1,
                container2);
    }
    /**
     * 点击注册界面的立即注册
     */

    public void click_register_register(View view) {
        String registerText = et_register_text.getText().toString().trim();
        String registerPassword = et_register_password.getText().toString().trim();

        RetrofitHelper.getApiService()
                .postRegisterApi(registerText, registerPassword)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RegisterResp>() {
                    @Override
                    public void call(RegisterResp registerResp) {
                        String response = registerResp.getResponse();
                        String userid = registerResp.getUserInfo().getUserid();
                        if (response.equals("register")) {
                            SharedPreferences sp = LoginActivity.this.getSharedPreferences(REGISTER, Context.MODE_PRIVATE);
                            SharedPreferences.Editor edit = sp.edit();
                            edit.putString(USERId_REGISTER, userid);
                            edit.commit();
                            rotateAnimationUtil.applyRotateAnimation(-1, 0, -90);
                            Toast.makeText(LoginActivity.this, "注册成功,请登录哦,亲", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     *  点击登录界面的立即登录
     */
    public void click_login(View view) {
        number = et_text.getText().toString().trim();
        password = et_password.getText().toString().trim();
        RetrofitHelper.getApiService()
                .getLoginApi(number, password)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<LoginResp>() {
                    @Override
                    public void call(LoginResp loginResp) {
                        String response = loginResp.getResponse();
                        String userid = loginResp.getUserInfo().getUserid();
                        if (response.equals("login")) {
                            SharedPreferences.Editor edit = sp.edit();
                            edit.putString(USERId_LOGIN, userid);
                            edit.putString("name",number);
                            edit.commit();
                            Toast.makeText(LoginActivity.this, "登陆成功哦,亲", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "您输入的有误", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 点击返回
     * @param view
     */
    public void login_goBack(View view) {
        finish();
    }
}
