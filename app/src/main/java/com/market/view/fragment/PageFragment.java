package com.market.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.market.R;

/**
 * Created by wuwei on 2017/1/8.
 */

public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_page, container, false);
            View view=View.inflate(getContext(),R.layout.fragment_page,null);

        // TextView textView = (TextView) view;
        //textView.setText("Fragment #" + mPage);
        //RecyclerView recyclerView = (RecylcerView) view;
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rcv_single);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MyAdapter mAdapter = new MyAdapter();

        recyclerView.setAdapter(mAdapter);

    }

    int[] imag = new int[]{R.drawable.singleproduct1, R.drawable.singleproduct2, R.drawable.singleproduct3, R.drawable.singleproduct1, R.drawable.singleproduct2, R.drawable.singleproduct3, R.drawable.singleproduct1, R.drawable.singleproduct2, R.drawable.singleproduct3};



//    private RecyclerView.Adapter mAdapter = new RecyclerView.Adapter() {
//        @Override
//        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//            MyViewHolder myViewHolder = new MyViewHolder(View.inflate(getContext(), R.layout.sing_product_item, null));
//            return myViewHolder;
//        }
//
//        @Override
//        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//            MyViewHolder myViewHolder = (MyViewHolder) holder;
//            myViewHolder.iv_sing1.setImageResource(imag[mPage]);
//
//        }
//
//        @Override
//        public int getItemCount() {
//            return imag.length;
//        }
//    };

    class MyAdapter extends RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder myViewHolder = new MyViewHolder(View.inflate(getContext(), R.layout.sing_product_item, null));
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.iv_sing1.setImageResource(imag[mPage]);
        }

        @Override
        public int getItemCount() {
            return imag.length;
        }
    }


    private class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_sing1;
        private final ImageView iv_sing1;
        private final TextView tv_sing2;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_sing1 = (ImageView) itemView.findViewById(R.id.iv_sing1);
            tv_sing1 = (TextView) itemView.findViewById(R.id.tv_sing1);
            tv_sing2 = (TextView) itemView.findViewById(R.id.tv_sing2);
        }
    }

}
