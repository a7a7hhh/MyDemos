package com.example.winkey.mydemos.view.fragment.expression;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.fragment.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.rockerhieu.emojicon.EmojiconTextView;

/**
 * author: Winkey
 * date: 2017/9/18
 * describe: TODO
 */

public class ExpressionFragment extends BaseFragment {

    @BindView(R.id.tv_emoji)
    EmojiconTextView mTvEmoji;
    private Context mContext;

    public static ExpressionFragment newInstance() {
        ExpressionFragment fragment = new ExpressionFragment();
        return fragment;
    }

    @Override
    protected void parsentData() throws Exception {

    }

    @Override
    protected void initViews(View view) throws Exception {
        String str1 = "[/满意]";
        for (int i = 0; i < str1.length(); i++) {
            char ch1 = str1.charAt(i);
            int codePoint = Character.codePointAt(str1, 1);
        }
        mTvEmoji.setText("[/满意]\ue31d333");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expression, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
