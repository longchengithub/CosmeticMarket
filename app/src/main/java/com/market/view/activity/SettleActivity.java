package com.market.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.market.R;
import com.market.view.activity.base.BaseCompatActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by GaoLeihua on 2017/1/8.
 */
public class SettleActivity extends BaseCompatActivity{

    @BindView(R.id.bt_tijiaodingdan)
    Button btTiJiaoDingDan;
    @BindView(R.id.back)
    ImageView IvBack;

  /*  @BindView(R.id.youhuiquan1)
    Button bt1;
    @BindView(R.id.youhuiquan2)
    Button bt2;*/

    @Override
    protected void initToolBar() {

    }

    @Override
    protected int getLayoutId() {
      return R.layout.activity_settle_accounts;
      //  return R.layout.activity_cart_wu_youhuiquan;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {


    }

    @OnClick({R.id.bt_tijiaodingdan,R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_tijiaodingdan:
                //提交订单
                TiJiaoDingDan();
                break;
        }
        switch (view.getId()) {
            case R.id.back:
                //标题栏的返回 返回到购物车fragment的界面
                BackToShoppinggFregment();
                break;
        }
    }


    /*@OnClick({R.id.youhuiquan1,R.id.youhuiquan2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.youhuiquan1:
                bt1.setSelected(true);
                bt2.setSelected(false);
                break;
            case R.id.youhuiquan2:
                bt2.setSelected(true);
                bt1.setSelected(false);
                break;
        }
    }*/

    /**
     * 标题栏的返回 返回到购物车fragment的界面
     */

    private void BackToShoppinggFregment() {

        finish();

    /*  Intent intent=new Intent(this,MainActivity.class);
        intent.putExtra("flag","aaa");
        startActivity(intent);*/

    }

    /**
     * 提交订单
     */
    private void TiJiaoDingDan() {
        //如果是确认的话,就是付款成功
        Toast.makeText(this, "亲,购买成功,等着收货吧", Toast.LENGTH_SHORT).show();
        //如果按手机上的返回键或者 支付宝窗体的返回键,跳到  订单详情界面
      //  Toast.makeText(this, "跳到订单详情界面", Toast.LENGTH_SHORT).show();
    }


}
