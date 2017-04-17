package com.market.view.activity;


import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.market.R;
import com.market.utils.DisplayUtil;
import com.market.view.activity.base.BaseCompatActivity;

public class NewArrivalsActivity extends BaseCompatActivity {


    private RecyclerView rcv_new_arrivals;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_arrivals;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        rcv_new_arrivals = (RecyclerView) findViewById(R.id.rcv_new_arrivals);
        rcv_new_arrivals.setLayoutManager(new GridLayoutManager(NewArrivalsActivity.this,2,GridLayoutManager.VERTICAL,false));
        rcv_new_arrivals.addItemDecoration(new SpaceItemDecoration(5));
        rcv_new_arrivals.setAdapter(newAdapter);
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    int[] imag = {R.drawable.new_arrivals1,R.drawable.new_arrivals2,R.drawable.new_arrivals3,R.drawable.new_arrivals4,R.drawable.new_arrivals1,R.drawable.new_arrivals1,};
    private RecyclerView.Adapter newAdapter = new RecyclerView.Adapter() {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            NewViewHolder newViewHolder = new NewViewHolder(View.inflate(NewArrivalsActivity.this,R.layout.new_arrivals_item,null));

            return newViewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            NewViewHolder newViewHolder = (NewViewHolder) holder;
            newViewHolder.iv_newarrivals.setImageResource(imag[position]);
            newViewHolder.tv_yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }

        @Override
        public int getItemCount() {
            return imag.length;
        }
    };

    private class NewViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv_newarrivals;
        private final TextView tv_yuanjia;

        public NewViewHolder(View itemView) {
            super(itemView);
            iv_newarrivals = (ImageView)itemView.findViewById(R.id.iv_newarrivals);
            tv_yuanjia = (TextView) itemView.findViewById(R.id.tv_yuanjia);
        }
    }

    /**
     * recycler设置间距
     */
    public class SpaceItemDecoration extends RecyclerView.ItemDecoration
    {
        int mSpace;

        /**
         * @param space 传入的值，其单位视为dp
         */
        public SpaceItemDecoration(int space)
        {
            this.mSpace = DisplayUtil.dp2px(NewArrivalsActivity.this, space);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
        {
            int pos = parent.getChildAdapterPosition(view);

            outRect.left = mSpace;
            outRect.top = mSpace;
            outRect.bottom = mSpace;
            outRect.right = mSpace;
        }
    }
}
