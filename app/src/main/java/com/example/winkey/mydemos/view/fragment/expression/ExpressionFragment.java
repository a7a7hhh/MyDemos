package com.example.winkey.mydemos.view.fragment.expression;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.fragment.base.BaseFragment;
import com.example.winkey.mydemos.view.utils.Logger;

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
        mTvEmoji.setText("[/满意][/吐舌头][/满[/o[/new]]意][/满意][/满意]sfa[/ ？满意]hfh[/喜欢][/喜欢][/喜欢][/喜欢][/喜欢][/喜欢][/喜欢][/喜欢][/喜欢][/喜欢][/喜欢][/喜欢][/喜欢][/喜欢]");
        mTvEmoji.post(new Runnable() {
            @Override
            public void run() {
                Logger.debug("xwb"+mTvEmoji.getLineCount());
            }
        });
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
