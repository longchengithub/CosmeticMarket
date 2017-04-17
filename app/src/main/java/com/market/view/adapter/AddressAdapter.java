package com.market.view.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.market.R;
import com.market.bean.AddressBean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/9.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    private final List<AddressBean.AddressListBean> mAddressBean;
    private Context context;
    private TextView delete;
    private TextView edit;
    private View view;
    private TextView tv_checked;
    private LinearLayout ll_checked;
    private int pre;

    public AddressAdapter(Context context, List<AddressBean.AddressListBean> mAddressBean) {
        this.context = context;
        this.mAddressBean = mAddressBean;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.address_list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AddressBean.AddressListBean addressListBean = mAddressBean.get(position);
        String name =addressListBean.getName();
        holder.name.setText(name);
        String phoneNumber = addressListBean.getPhoneNumber();
        holder.number.setText(phoneNumber);
        String address = addressListBean.getProvince()+ addressListBean.getCity()+ addressListBean.getAddressArea()+ addressListBean.getAddressDetail();
        holder.address.setText(address);
        holder.iv_checked.setImageResource(addressListBean.getIsDefault() == 0?R.drawable.neirong_weixuanzhong:R.drawable.neirong_xuanzhong);

       // holder.setIsRecyclable(false);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onclikedListener != null){
                    onclikedListener.detele(mAddressBean.get(position),view);
                }
            }
        });

       edit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (onclikedListener!=null){
                   onclikedListener.edit(mAddressBean.get(position));
               }
           }
       });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onclikedListener != null){
                    onclikedListener.itemData(mAddressBean.get(position));
                }
            }
        });

        ll_checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               pre = position;
                if (onclikedListener != null){
                    onclikedListener.checked(mAddressBean.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAddressBean==null?0:mAddressBean.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView number;
        private TextView address;
        private ImageView iv_checked;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = (TextView) itemView.findViewById(R.id.tv_name);
            number = (TextView) itemView.findViewById(R.id.tv_number);
            address = (TextView) itemView.findViewById(R.id.tv_address);
            delete = (TextView) itemView.findViewById(R.id.tv_delete);
            edit = (TextView) itemView.findViewById(R.id.tv_edit);
            tv_checked = (TextView) itemView.findViewById(R.id.tv_checked);
            ll_checked = (LinearLayout)itemView.findViewById(R.id.ll_checked);
            iv_checked = (ImageView)itemView.findViewById(R.id.iv_checked);
        }
    }

    public interface OnclikedListener{
        public void edit(AddressBean.AddressListBean addressListBean);
        public void detele(AddressBean.AddressListBean addressListBean, View view);
        public void itemData(AddressBean.AddressListBean addressListBean);
        public void checked(AddressBean.AddressListBean addressListBean);
    }

    public OnclikedListener onclikedListener;

    public void setOnclikedListener(OnclikedListener onclikedListener) {
        this.onclikedListener =onclikedListener;

    }
}

