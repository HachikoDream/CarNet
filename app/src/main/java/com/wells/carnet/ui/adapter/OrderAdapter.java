package com.wells.carnet.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wells.carnet.R;
import com.wells.carnet.data.entity.Order;

import java.util.List;

/**
 * Created by Wells on 2016/4/14.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<Order> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;
    private OnOrderClickListener mListener;

    public OrderAdapter(List<Order> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setListener(OnOrderClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = mInflater.inflate(R.layout.order_item, parent, false);
        return new OrderViewHolder(contentView);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        final Order order = mDatas.get(position);
        holder.placeTv.setText("从 " + order.getSrcPlace() + " 到 " + order.getDestPlace());
        holder.dateTv.setText(order.getBegintime() + "-" + order.getBacktime());
        holder.ordermanTv.setText("申请人:" + order.getOrderman());
        holder.usemanTv.setText("使用人:" + order.getUseman());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onOrderClick(v, order);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addOrder(Order order) {
        mDatas.add(order);
        notifyItemInserted(mDatas.size() - 1);
    }

    public void setOrders(List<Order> mOrders) {
        mDatas = mOrders;
        notifyDataSetChanged();
    }

    public interface OnOrderClickListener {
        void onOrderClick(View v, Order order);
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        private TextView ordermanTv;
        private TextView usemanTv;
        private TextView dateTv;
        private TextView placeTv;

        public OrderViewHolder(View itemView) {
            super(itemView);
            ordermanTv = (TextView) itemView.findViewById(R.id.orderman_tv);
            usemanTv = (TextView) itemView.findViewById(R.id.useman_tv);
            dateTv = (TextView) itemView.findViewById(R.id.dateperiod_tv);
            placeTv = (TextView) itemView.findViewById(R.id.placeperiod_tv);
        }
    }
}
