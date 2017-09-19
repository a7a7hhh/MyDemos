package com.example.winkey.mydemos.view.widget.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.utils.Logger;


/**
 * Created by zhy on 16/6/23.
 */
public class LoadMoreWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int ITEM_TYPE_LOAD_MORE = Integer.MAX_VALUE - 2;
    public static final int ITEM_TYPE_NO_MORE = Integer.MAX_VALUE - 3;
    public static final int ITEM_TYPE_LOAD_FAIL = Integer.MAX_VALUE - 4;

    private RecyclerView.Adapter mInnerAdapter;
    private View mLoadMoreView;
    private View mNoMoreView;
    private int mLoadMoreLayoutId;
    private int mNoMoreLayoutId;

    private Context mContext;
    private boolean NO_MORE;
    private boolean LOAD_FAIL = false;
    private View mLoadFailView;
    private int mLoadFailId;

    public LoadMoreWrapper(RecyclerView.Adapter adapter, Context context) {
        mInnerAdapter = adapter;
        this.mContext = context;

    }

    private boolean hasLoadMore() {
        return mLoadMoreView != null || mLoadMoreLayoutId != 0 || mNoMoreView != null || mNoMoreLayoutId != 0 || mLoadFailView != null;
    }


    private boolean isShowLoadMore(int position) {
        return hasLoadMore() && (position >= mInnerAdapter.getItemCount());
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowLoadMore(position)) {
            if (NO_MORE) {
                if (LOAD_FAIL) {
                    return ITEM_TYPE_LOAD_FAIL;
                }
                return ITEM_TYPE_NO_MORE;
            } else {
                return ITEM_TYPE_LOAD_MORE;
            }
        }
        return mInnerAdapter.getItemViewType(position);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_LOAD_MORE) {
            ViewHolder holder;
            if (mLoadMoreView != null) {
                holder = ViewHolder.createViewHolder(parent.getContext(), mLoadMoreView);
            } else {
                holder = ViewHolder.createViewHolder(parent.getContext(), parent, mLoadMoreLayoutId);
            }
            return holder;
        } else if (viewType == ITEM_TYPE_NO_MORE) {
            ViewHolder holder;
            if (mNoMoreView != null) {
                holder = ViewHolder.createViewHolder(parent.getContext(), mNoMoreView);
            } else {
                holder = ViewHolder.createViewHolder(parent.getContext(), parent, mLoadMoreLayoutId);
            }
            return holder;
        } else if (viewType == ITEM_TYPE_LOAD_FAIL) {
            ViewHolder holder;
            mLoadFailView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_load_fail, parent, false);
            if (mLoadFailView != null) {
                holder = ViewHolder.createViewHolder(parent.getContext(), mLoadFailView);
            } else {
                holder = ViewHolder.createViewHolder(parent.getContext(), parent, mLoadFailId);
            }
            return holder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isShowLoadMore(position)) {
            if (NO_MORE) {
                if (LOAD_FAIL) {
                    mLoadFailView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LOAD_FAIL = false;
                            NO_MORE = false;
                            notifyDataSetChanged();
                        }
                    });
                }
                return;
            } else {
                if (mOnLoadMoreListener != null) {
                    mOnLoadMoreListener.onLoadMoreRequested();
                }
                return;
            }
        }
        Logger.debug("当前position" + position);
        mInnerAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        WrapperUtils.onAttachedToRecyclerView(mInnerAdapter, recyclerView, new WrapperUtils.SpanSizeCallback() {
            @Override
            public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position) {
                if (isShowLoadMore(position)) {
                    return layoutManager.getSpanCount();
                }
                if (oldLookup != null) {
                    return oldLookup.getSpanSize(position);
                }
                return 1;
            }
        });
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mInnerAdapter.onViewAttachedToWindow(holder);

        if (isShowLoadMore(holder.getLayoutPosition())) {
            setFullSpan(holder);
        }
    }

    private void setFullSpan(RecyclerView.ViewHolder holder) {
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;

            p.setFullSpan(true);
        }
    }

    @Override
    public int getItemCount() {
        return mInnerAdapter.getItemCount() + (hasLoadMore() ? 1 : 0);
    }


    public interface OnLoadMoreListener {
        void onLoadMoreRequested();
    }

    private OnLoadMoreListener mOnLoadMoreListener;

    public LoadMoreWrapper setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        if (loadMoreListener != null) {
            mOnLoadMoreListener = loadMoreListener;
        }
        return this;
    }

    public LoadMoreWrapper setLoadMoreView(View loadMoreView) {
        mLoadMoreView = loadMoreView;
        return this;
    }

    public LoadMoreWrapper setLoadMoreView(int layoutId) {
        mLoadMoreLayoutId = layoutId;
        return this;
    }

    public void setLoadFail() {
        NO_MORE = true;
        LOAD_FAIL = true;
//        notifyDataSetChanged();
    }

    public void setLoadMore() {
        NO_MORE = false;
    }

    public void setNoMoreView(View view) {
        NO_MORE = true;
        mNoMoreView = view;
        notifyDataSetChanged();
    }
}
