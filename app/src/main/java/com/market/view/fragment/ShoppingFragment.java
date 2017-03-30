package com.market.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.market.R;
import com.market.bean.GoodsInfo;
import com.market.bean.StoreInfo;
import com.market.view.activity.MainActivity;
import com.market.view.activity.OrderCheckOutActivity;
import com.market.view.adapter.ShopcartAdapter;
import com.market.view.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

import static com.market.R.layout.fragment_shopping;

/**
 * Created by chenlong on 2016/12/20.
 */

public class ShoppingFragment extends BaseFragment implements ShopcartAdapter.CheckInterface, ShopcartAdapter.ModifyCountInterface, ShopcartAdapter.GroupEdtorListener
{

    /**
     * 控件实例化
     */
    private View mFregmentView;

    @BindView(R.id.back)
    ImageView ivBack;
    @BindView(R.id.bt_gotobuy)
    Button btGoToBuy;
    @BindView(R.id.tv_go_to_settle)
    TextView tvGoToSettle;
    @BindView(R.id.exListView)
    ExpandableListView exListView;
    @BindView(R.id.all_chekbox)
    CheckBox allChekbox;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.top_bar)
    LinearLayout topBar;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.ll_shar)
    LinearLayout llShar;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;

    @BindView(R.id.tv_redHod)
    TextView tvRedHod;
    @BindView(R.id.iv_quXiaoReDian)
    ImageView ivQuXiaoReDian;


    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.ll_cart)
    LinearLayout llCart;

    //当购物车中没有商品时,出现的界面
    @BindView(R.id.layout_cart_empty)
    LinearLayout cart_empty;


    /**
     * 全局
     */
    private List<StoreInfo> groups = new ArrayList<StoreInfo>();// 组元素数据列表
    private Map<String, List<GoodsInfo>> children = new HashMap<String, List<GoodsInfo>>();// 子元素数据列表
    private ShopcartAdapter selva;
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    private int flag = 0;


    @Override
    protected void onLazyLoad()
    {
    }

    //初始化布局.shopFragment所有操作的入口
    @Override
    protected void initView(Bundle savedInstanceState)
    {
        //获取shop的view
        mFregmentView = View.inflate(getContext(), fragment_shopping, null);
        //初始化数据
        initDatas();
        //初始化事件
        initEvents();

    }

    private void initEvents()
    {
        selva = new ShopcartAdapter(groups, children, mActivity);
        selva.setCheckInterface(this);// 关键步骤1,设置复选框接口
        selva.setModifyCountInterface(this);// 关键步骤2,设置数量增减接口
        selva.setmListener(this);
        exListView.setAdapter(selva);
        for (int i = 0; i < selva.getGroupCount(); i++)
        {
            exListView.expandGroup(i);// 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
        }
    }

    /**
     * 模拟数据<br>
     * 遵循适配器的数据列表填充原则，组元素被放在一个List中，对应的组元素下辖的子元素被放在Map中，<br>
     * 其键是组元素的Id(通常是一个唯一指定组元素身份的值)
     */
    private void initDatas()
    {
        for (int i = 0; i < 2; i++)
        {
            //   groups.add(new StoreInfo(i + "", "兰芝(LANEIGE)" + (i + 1) + "号店"));
            groups.add(new StoreInfo(i + "", i % 2 == 0 ? "兰芝(LANEIGE)旗舰店" : "高斯(KAOSE)旗舰店"));

            List<GoodsInfo> products = new ArrayList<GoodsInfo>();
            for (int j = 0; j <= i; j++)
            {
                int[] img = {R.drawable.goods1, R.drawable.goods2, R.drawable.goods3, R.drawable.goods4, R.drawable.goods5};
                products.add(new GoodsInfo(j + "", "商品", groups.get(i)
                        .getName() + "的第" + (j + 1) + "个商品", 122.00 + new Random().nextInt(23), new Random().nextInt(5) + 1, "豪华", "1", img[i * j + i], 146.00 + new Random().nextInt(13)));
            }
            children.put(groups.get(i).getId(), products);// 将组元素的一个唯一值，这里取Id，作为子元素List的Key
        }
    }

    /**
     * 设置购物车产品数量
     */
    private void setCartNum()
    {
        int count = 0;
        for (int i = 0; i < groups.size(); i++)
        {
            groups.get(i).setChoosed(allChekbox.isChecked());
            StoreInfo group = groups.get(i);
            List<GoodsInfo> childs = children.get(group.getId());
            for (GoodsInfo goodsInfo : childs)
            {
                count += 1;
            }
        }

        //购物车已清空
        if (count == 0)
        {
            clearCart();
        } else
        {
            //标题栏显示当前有多少件商品
            title.setText("购物车" + "(" + count + ")");
        }
    }

    private void clearCart()
    {
        title.setText("购物车" + "(" + 0 + ")");
        subtitle.setVisibility(View.GONE);
        llCart.setVisibility(View.GONE);
        cart_empty.setVisibility(View.VISIBLE);
        //没有商品时,"返回"控件 可见
        ivBack.setVisibility(View.VISIBLE);
    }


    /**
     * 删除操作<br>
     * 1.不要边遍历边删除，容易出现数组越界的情况<br>
     * 2.现将要删除的对象放进相应的列表容器中，待遍历完后，以removeAll的方式进行删除
     */
    protected void doDelete()
    {
        List<StoreInfo> toBeDeleteGroups = new ArrayList<StoreInfo>();// 待删除的组元素列表
        for (int i = 0; i < groups.size(); i++)
        {
            StoreInfo group = groups.get(i);
            if (group.isChoosed())
            {
                toBeDeleteGroups.add(group);
            }
            List<GoodsInfo> toBeDeleteProducts = new ArrayList<GoodsInfo>();// 待删除的子元素列表
            List<GoodsInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++)
            {
                if (childs.get(j).isChoosed())
                {
                    toBeDeleteProducts.add(childs.get(j));
                }
            }
            childs.removeAll(toBeDeleteProducts);
        }
        groups.removeAll(toBeDeleteGroups);
        //记得重新设置购物车
        setCartNum();
        selva.notifyDataSetChanged();

    }


    private boolean isAllCheck()
    {

        for (StoreInfo group : groups)
        {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }

    /**
     * 全选与反选
     */
    private void doCheckAll()
    {
        for (int i = 0; i < groups.size(); i++)
        {
            groups.get(i).setChoosed(allChekbox.isChecked());
            StoreInfo group = groups.get(i);
            List<GoodsInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++)
            {
                childs.get(j).setChoosed(allChekbox.isChecked());
            }
        }
        selva.notifyDataSetChanged();
        calculate();
    }

    /**
     * 统计操作<br>
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
     * 3.给底部的textView进行数据填充
     */
    private void calculate()
    {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < groups.size(); i++)
        {
            StoreInfo group = groups.get(i);
            List<GoodsInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++)
            {
                GoodsInfo product = childs.get(j);
                if (product.isChoosed())
                {
                    totalCount++;
                    totalPrice += product.getPrice() * product.getCount();
                }
            }
        }

        tvTotalPrice.setText("￥" + totalPrice);
        tvGoToSettle.setText("结算(" + totalCount + ")");
        //计算购物车的金额为0时候清空购物车的视图
        if (totalCount == 0)
        {
            setCartNum();
        } else
        {
            title.setText("购物车" + "(" + totalCount + ")");
        }
    }

    //点击按钮
    @OnClick({R.id.all_chekbox, R.id.tv_delete, R.id.tv_go_to_settle, R.id.subtitle, R.id.tv_save, R.id.tv_share, R.id.bt_gotobuy, R.id.back, R.id.iv_quXiaoReDian, R.id.tv_redHod})
    public void onClick(View view)
    {
        AlertDialog alert;
        switch (view.getId())
        {
            case R.id.all_chekbox:
                //全选与反选
                doCheckAll();
                break;
            case R.id.tv_delete:
                //移除商品
                doDeletegoods();
                break;
            case R.id.tv_go_to_settle:
                //去结算
                goToSettle();
                break;
            case R.id.subtitle:
                //标题栏 完成或编辑
                doEditorOrFinish();
                break;
            case R.id.tv_share:
                //选择分享的商品
                doShare();
                break;
            case R.id.tv_save:
                //选择要保存的商品
                doSave();
                break;
            case R.id.back:
                //返回后跳到home主界面
                goToHomeFragment();
                break;
            case R.id.bt_gotobuy:
                //点击"去逛逛"后 跳到home主界面
                goToHomeFragment();
                break;
            case R.id.iv_quXiaoReDian:
                //点击后,将广告标语 和叉叉 去掉
                QuXiaoReDian();
                break;
            case R.id.tv_redHod:
                //点击"去逛逛"后 跳到home主界面
                goToHomeFragment();
                break;

        }
    }

    /**
     * 将 去热卖店铺 逛逛 的 标语去掉
     */
    private void QuXiaoReDian()
    {
        tvRedHod.setVisibility(View.INVISIBLE);
        ivQuXiaoReDian.setVisibility(View.INVISIBLE);
        Toast.makeText(mActivity, "*^__^* 和色携手传智播客祝您购物愉快", Toast.LENGTH_SHORT).show();

    }

    /**
     * 返回  或 去逛逛 后跳到home主界面
     */
    private void goToHomeFragment()
    {
        ((MainActivity) getActivity()).showFragment(0);
    }

    /**
     * 选择要保存的商品
     */
    private void doSave()
    {
        if (totalCount == 0)
        {
            Toast.makeText(mActivity, "请选择要保存的商品", Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(mActivity, "和色携手传智播客提醒您:\n保存成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 选择要分享的商品
     */
    private void doShare()
    {
        if (totalCount == 0)
        {
            Toast.makeText(mActivity, "请选择要分享的商品", Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(mActivity, "和色携手传智播客提醒您:\n分享成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 标题栏是编辑还是完成
     */
    private void doEditorOrFinish()
    {
        if (flag == 0)
        {
            llInfo.setVisibility(View.GONE);
            tvGoToSettle.setVisibility(View.GONE);
            llShar.setVisibility(View.VISIBLE);
            subtitle.setText("完成");
        } else if (flag == 1)
        {
            llInfo.setVisibility(View.VISIBLE);
            tvGoToSettle.setVisibility(View.VISIBLE);
            llShar.setVisibility(View.GONE);
            subtitle.setText("编辑");
        }
        flag = (flag + 1) % 2;//其余得到循环执行上面2个不同的功能
    }

    /**
     * 去结算
     */
    private void goToSettle()
    {
        AlertDialog alert;
        if (totalCount == 0)
        {
            Toast.makeText(mActivity, "请选择要支付的商品", Toast.LENGTH_LONG).show();
            return;
        }
        alert = new AlertDialog.Builder(mActivity).create();
        alert.setTitle("操作提示");
        alert.setMessage("总计:\n" + totalCount + "种商品\n" + totalPrice + "元");
        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "我再想想",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        return;
                    }
                });
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "我要剁手",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //   Toast.makeText(mActivity, "进入结算界面", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(mActivity, OrderCheckOutActivity.class);

//                        intent.putExtra("shoppingFregment2SettleActivity", totalCount + ":" + totalPrice);

                        startActivity(intent);

                        return;
                    }
                });
        alert.show();
    }

    /**
     * 移除商品
     */
    private void doDeletegoods()
    {
        AlertDialog alert;
        if (totalCount == 0)
        {
            Toast.makeText(mActivity, "请选择要移除的商品", Toast.LENGTH_SHORT).show();
            return;
        }
        alert = new AlertDialog.Builder(mActivity).create();
        alert.setTitle("操作提示");
        alert.setMessage("您确定要将这些商品从购物车中移除吗？");
        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "(*^__^*)我要购买",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        return;
                    }
                });
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "(ㄒoㄒ)忍痛割爱",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        doDelete();
                    }
                });
        alert.show();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        selva = null;
        groups.clear();
        totalPrice = 0;
        totalCount = 0;
        children.clear();
    }


    //点击后,进入fragment_sopping界面
    @Override
    protected int getLayoutId()
    {
        return fragment_shopping;
    }

    public static ShoppingFragment newInstance()
    {
        return new ShoppingFragment();
    }


    @Override
    public void checkGroup(int groupPosition, boolean isChecked)
    {
        StoreInfo group = groups.get(groupPosition);
        List<GoodsInfo> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++)
        {
            childs.get(i).setChoosed(isChecked);
        }
        if (isAllCheck())
            allChekbox.setChecked(true);
        else
            allChekbox.setChecked(false);
        selva.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void checkChild(int groupPosition, int childPosition, boolean isChecked)
    {
        boolean allChildSameState = true;// 判断改组下面的所有子元素是否是同一种状态
        StoreInfo group = groups.get(groupPosition);
        List<GoodsInfo> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++)
        {
            // 不全选中
            if (childs.get(i).isChoosed() != isChecked)
            {
                allChildSameState = false;
                break;
            }
        }
        //获取店铺选中商品的总金额
        if (allChildSameState)
        {
            group.setChoosed(isChecked);// 如果所有子元素状态相同，那么对应的组元素被设为这种统一状态
        } else
        {
            group.setChoosed(false);// 否则，组元素一律设置为未选中状态
        }

        if (isAllCheck())
        {
            allChekbox.setChecked(true);// 全选
        } else
        {
            allChekbox.setChecked(false);// 反选
        }
        selva.notifyDataSetChanged();
        calculate();


    }

    @Override
    public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked)
    {

        GoodsInfo product = (GoodsInfo) selva.getChild(groupPosition,
                childPosition);
        int currentCount = product.getCount();
        currentCount++;
        product.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        selva.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked)
    {
        GoodsInfo product = (GoodsInfo) selva.getChild(groupPosition,
                childPosition);
        int currentCount = product.getCount();
        if (currentCount == 1)
            return;
        currentCount--;
        product.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        selva.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void childDelete(int groupPosition, int childPosition)
    {

        children.get(groups.get(groupPosition).getId()).remove(childPosition);
        StoreInfo group = groups.get(groupPosition);
        List<GoodsInfo> childs = children.get(group.getId());
        if (childs.size() == 0)
        {
            groups.remove(groupPosition);
        }
        selva.notifyDataSetChanged();
        calculate();

    }

    @Override
    public void groupEdit(int groupPosition)
    {
        groups.get(groupPosition).setIsEdtor(true);
        selva.notifyDataSetChanged();
    }
}

