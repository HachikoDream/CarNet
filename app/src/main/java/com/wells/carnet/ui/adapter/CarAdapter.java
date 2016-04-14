package com.wells.carnet.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wells.carnet.R;
import com.wells.carnet.data.entity.Car;

import java.util.List;

/**
 * Created by Wells on 2016/4/12.
 */
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {
    private List<Car> mCars;
    private Context mContext;
    private onCarItemClick listener;

    public CarAdapter(List<Car> mCars, Context context) {
        this.mCars = mCars;
        this.mContext = context;
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.car_item, parent, false);
        return new CarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, int position) {
        final Car car = mCars.get(position);
        if (car.getState() == Car.STATE_RUN) {
            holder.mCarIv.setImageResource(R.drawable.caricon);
            holder.mStateTv.setText("运行中，速度:" + car.getSpeed() + "km/h");
        } else {
            holder.mStateTv.setText("停靠中");
            holder.mCarIv.setImageResource(R.drawable.cariconidle);
        }
        holder.mCarNumTv.setText(car.getCarNum());
        holder.mCarDriverTv.setText("司机:" + car.getDriver());
        holder.contentLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v, car);
                }
            }
        });

    }

    public interface onCarItemClick {
        void onClick(View v, Car car);
    }

    public void setmCars(List<Car> mCars) {
        this.mCars = mCars;
        notifyDataSetChanged();
    }

    public void setListener(onCarItemClick listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return mCars.size();
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        private ImageView mCarIv;
        private TextView mCarNumTv;
        private TextView mCarDriverTv;
        private TextView mStateTv;
        private LinearLayout contentLl;

        public CarViewHolder(View itemView) {
            super(itemView);
            mCarIv = (ImageView) itemView.findViewById(R.id.car_icon);
            mCarDriverTv = (TextView) itemView.findViewById(R.id.driver_tv);
            mCarNumTv = (TextView) itemView.findViewById(R.id.carnum_tv);
            mStateTv = (TextView) itemView.findViewById(R.id.state_tv);
            contentLl = (LinearLayout) itemView.findViewById(R.id.content_view);
        }
    }
}
