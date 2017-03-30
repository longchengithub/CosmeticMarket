package com.market.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.market.R;
import com.market.view.activity.AddressManageActivity;
import com.market.view.activity.OrderMyActivity;
import com.market.view.activity.base.LoginActivity;
import com.market.view.activity.base.PersonalActivity;
import com.market.view.fragment.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by chenlong on 2016/12/20.
 */

public class SettingFragment extends BaseFragment
{

    @BindView(R.id.tv_news)
    TextView tvNews;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.iv_setting_xuhua)
    ImageView ivSettingXuhua;
    @BindView(R.id.tv_up)
    TextView tvUp;
    @BindView(R.id.accountClick)
    ImageView accountClick;
    @BindView(R.id.tv_all_order)
    TextView mOrder;
    @BindView(R.id.iv_head)
    ImageView iv_head;

    @Override
    protected void onLazyLoad()
    {

    }

//    /**
//     * 点击跳转登录界面
//     */
//    private View.OnClickListener LoginListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent(getActivity(),LoginActivity.class);
//            startActivity(intent);
//
//        }
//    };

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        Bitmap image = ((BitmapDrawable)ivSettingXuhua.getDrawable()).getBitmap();
        Drawable drawable = BlurImageview.BlurImages(image, getContext());
        ivSettingXuhua.setImageDrawable(drawable);



        //个人设置中心
        tvUp.setOnClickListener(v -> {
            Intent intent = new Intent(mActivity, PersonalActivity.class);
            startActivity(intent);
        });

        //账户登录中心
        accountClick.setOnClickListener(v -> {
            Intent intent = new Intent(mActivity, LoginActivity.class);
            startActivity(intent);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    iv_head.setVisibility(View.VISIBLE);
                }
            },3000);
        });

        textView4.setOnClickListener(v -> {
            Intent intent = new Intent(mActivity, AddressManageActivity.class);
            startActivity(intent);
        });

        mOrder.setOnClickListener(v -> {
            Intent intent = new Intent(mActivity, OrderMyActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_setting;
    }

    public static SettingFragment newInstance()
    {
        return new SettingFragment();
    }

    /**
     * 图片模糊的类
     */
    public static class BlurImageview {
        /**
         * 水平方向模糊度
         */
        private static float hRadius = 10;
        /**
         * 竖直方向模糊度
         */
        private static float vRadius = 10;
        /**
         * 模糊迭代度
         */
        private static int iterations = 10;

        /**
         * 图片高斯模糊处理 
         */
        public static Drawable BlurImages(Bitmap bmp, Context context) {

            int width = bmp.getWidth();
            int height = bmp.getHeight();
            int[] inPixels = new int[width * height];
            int[] outPixels = new int[width * height];
            Bitmap bitmap = Bitmap.createBitmap(width, height,
                    Bitmap.Config.ARGB_8888);
            bmp.getPixels(inPixels, 0, width, 0, 0, width, height);
            for (int i = 0; i < iterations; i++) {
                blur(inPixels, outPixels, width, height, hRadius);
                blur(outPixels, inPixels, height, width, vRadius);
            }
            blurFractional(inPixels, outPixels, width, height, hRadius);
            blurFractional(outPixels, inPixels, height, width, vRadius);
            bitmap.setPixels(inPixels, 0, width, 0, 0, width, height);
            Drawable drawable = new BitmapDrawable(context.getResources(), bitmap);
            return drawable;
        }

        /**
         * 图片高斯模糊算法
         */
        public static void blur(int[] in, int[] out, int width, int height,
                                float radius) {
            int widthMinus1 = width - 1;
            int r = (int) radius;
            int tableSize = 2 * r + 1;
            int divide[] = new int[256 * tableSize];

            for (int i = 0; i < 256 * tableSize; i++)
                divide[i] = i / tableSize;

            int inIndex = 0;

            for (int y = 0; y < height; y++) {
                int outIndex = y;
                int ta = 0, tr = 0, tg = 0, tb = 0;

                for (int i = -r; i <= r; i++) {
                    int rgb = in[inIndex + clamp(i, 0, width - 1)];
                    ta += (rgb >> 24) & 0xff;
                    tr += (rgb >> 16) & 0xff;
                    tg += (rgb >> 8) & 0xff;
                    tb += rgb & 0xff;
                }

                for (int x = 0; x < width; x++) {
                    out[outIndex] = (divide[ta] << 24) | (divide[tr] << 16)
                            | (divide[tg] << 8) | divide[tb];

                    int i1 = x + r + 1;
                    if (i1 > widthMinus1)
                        i1 = widthMinus1;
                    int i2 = x - r;
                    if (i2 < 0)
                        i2 = 0;
                    int rgb1 = in[inIndex + i1];
                    int rgb2 = in[inIndex + i2];

                    ta += ((rgb1 >> 24) & 0xff) - ((rgb2 >> 24) & 0xff);
                    tr += ((rgb1 & 0xff0000) - (rgb2 & 0xff0000)) >> 16;
                    tg += ((rgb1 & 0xff00) - (rgb2 & 0xff00)) >> 8;
                    tb += (rgb1 & 0xff) - (rgb2 & 0xff);
                    outIndex += height;
                }
                inIndex += width;
            }
        }

        /**
         * 图片高斯模糊算法 
         */
        public static void blurFractional(int[] in, int[] out, int width,
                                          int height, float radius) {
            radius -= (int) radius;
            float f = 1.0f / (1 + 2 * radius);
            int inIndex = 0;

            for (int y = 0; y < height; y++) {
                int outIndex = y;

                out[outIndex] = in[0];
                outIndex += height;
                for (int x = 1; x < width - 1; x++) {
                    int i = inIndex + x;
                    int rgb1 = in[i - 1];
                    int rgb2 = in[i];
                    int rgb3 = in[i + 1];

                    int a1 = (rgb1 >> 24) & 0xff;
                    int r1 = (rgb1 >> 16) & 0xff;
                    int g1 = (rgb1 >> 8) & 0xff;
                    int b1 = rgb1 & 0xff;
                    int a2 = (rgb2 >> 24) & 0xff;
                    int r2 = (rgb2 >> 16) & 0xff;
                    int g2 = (rgb2 >> 8) & 0xff;
                    int b2 = rgb2 & 0xff;
                    int a3 = (rgb3 >> 24) & 0xff;
                    int r3 = (rgb3 >> 16) & 0xff;
                    int g3 = (rgb3 >> 8) & 0xff;
                    int b3 = rgb3 & 0xff;
                    a1 = a2 + (int) ((a1 + a3) * radius);
                    r1 = r2 + (int) ((r1 + r3) * radius);
                    g1 = g2 + (int) ((g1 + g3) * radius);
                    b1 = b2 + (int) ((b1 + b3) * radius);
                    a1 *= f;
                    r1 *= f;
                    g1 *= f;
                    b1 *= f;
                    out[outIndex] = (a1 << 24) | (r1 << 16) | (g1 << 8) | b1;
                    outIndex += height;
                }
                out[outIndex] = in[width - 1];
                inIndex += width;
            }
        }

        public static int clamp(int x, int a, int b) {
            return (x < a) ? a : (x > b) ? b : x;
        }
    }
}
