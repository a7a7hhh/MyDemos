package com.example.winkey.mydemos.presentation.base;

/**
 * @author : xupg
 * @date:2017/3/30
 * @description: TODO
 */

public class BaseSubscriber<T> extends CustomSubscribe<T> {

    private BaseSubscriber(){

    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder<T> {

        private BaseSubscriber<T> subscriber;

        private Builder() {
            subscriber = new BaseSubscriber<T>();
        }

        public BaseSubscriber<T> build() {
            return subscriber;
        }

        public Builder callback(CallBackListener<T> callbackListener) {
            subscriber.setCallBackListener(callbackListener);
            return this;
        }

        public Builder presenter(BasePresenter presenter) {
            subscriber.setPresenter(presenter);
            return this;
        }
    }
}
