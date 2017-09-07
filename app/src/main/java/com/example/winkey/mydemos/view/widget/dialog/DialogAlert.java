package com.example.winkey.mydemos.view.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.winkey.mydemos.R;


/**
 * @author : xupg
 * @date:2017/4/7
 * @description: TODO
 */

public class DialogAlert extends Dialog {

    private Context mContext;
    private String mTitle;
    private String mMessage;
    private String mButtonLeftText;
    private String mButtonRightText;

    private ClickListenerInterface mClickListenerInterface;

    public interface ClickListenerInterface {
        void doLeft();

        void doRight();
    }

    public DialogAlert(Context context, String title, String message, String buttonLeftText, String buttonRightText) {
        super(context, R.style.DialogAlert);
        this.mContext = context;
        this.mTitle = title;
        this.mMessage = message;
        this.mButtonLeftText = buttonLeftText;
        this.mButtonRightText = buttonRightText;
    }

    public DialogAlert(Context context, String title, String message, String buttonText) {
        this(context, title, message, null, buttonText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.dialog_view, null);
        setContentView(view);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvMessage = (TextView) view.findViewById(R.id.tv_message);
        TextView tvLeft = (TextView) view.findViewById(R.id.tv_btn_left);
        TextView tvRight = (TextView) view.findViewById(R.id.tv_btn_right);

        if ("".equals(mTitle)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setText(mTitle);
        }
        tvMessage.setText(mMessage);

        // 如果没有左边的按钮，就隐藏起来
        if (mButtonLeftText == null) {
            tvLeft.setVisibility(View.GONE);
            view.findViewById(R.id.line).setVisibility(View.GONE);
        } else {
            tvLeft.setText(mButtonLeftText);
            tvLeft.setOnClickListener(new ClickListener());
        }
        tvRight.setText(mButtonRightText);

        tvRight.setOnClickListener(new ClickListener());

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics();

        lp.width = (int) (d.widthPixels * 0.8);
        dialogWindow.setAttributes(lp);
    }

    public void setClickListener(ClickListenerInterface clickListenerInterface) {
        this.mClickListenerInterface = clickListenerInterface;
    }

    public class ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.tv_btn_left) {
                mClickListenerInterface.doLeft();
            } else if (id == R.id.tv_btn_right) {
                mClickListenerInterface.doRight();
            }
        }
    }
}
