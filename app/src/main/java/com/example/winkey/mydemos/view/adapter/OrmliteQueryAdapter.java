package com.example.winkey.mydemos.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.data.model.vo.UserInfoVO;
import com.example.winkey.mydemos.view.widget.recyclerview.adapter.CommonAdapter;
import com.example.winkey.mydemos.view.widget.recyclerview.adapter.ViewHolder;

import java.util.List;

/**
 * author: Winkey
 * date: 2017/10/10
 * describe: TODO
 */

public class OrmliteQueryAdapter extends CommonAdapter<UserInfoVO> {


    public OrmliteQueryAdapter(Context context,  List<UserInfoVO> datas) {
        super(context, R.layout.item_ormlite_query, datas);
    }

    @Override
    protected void convert(ViewHolder holder, UserInfoVO userInfoVO, int position) {
        TextView tvId = holder.getView(R.id.tv_ormlite_id);
        TextView tvName = holder.getView(R.id.tv_ormlite_name);
        TextView tvEmail = holder.getView(R.id.tv_ormlite_email);
        TextView tvPhone = holder.getView(R.id.tv_ormlite_phone);
        tvId.setText(userInfoVO.id+"");
        tvName.setText(userInfoVO.name);
        tvEmail.setText(userInfoVO.email);
        tvPhone.setText(userInfoVO.phone);
    }

}
