package simple.zhuazhu.com.simple;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhuazhu.view.InStyleToast;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间:2017/3/25 10:49
 * 创建人: 李涛
 * 修改人:李涛
 * 修改时间: 2017-5-18 17:24:50
 * 描述:带头部和底部的RecyclerView的适配器
 */

public abstract class RecyclerAdapter<VH extends ViewHolder, M> extends
        RecyclerView.Adapter<ViewHolder> {
    protected List<M> mData;
    protected Context mContext;
    protected LayoutInflater mInflater;
    /**
     * 头部类型
     */
    public static final int TYPE_HEADER = -1;
    /**
     * 底部类型
     */
    public static final int TYPE_FOOTER = -2;
    /**
     * 无数据类型
     */
    public static final int TYPE_EMPTY = -3;
    /**
     * 正常的item
     */
    public static final int TYPE_NORMAL = 0;
    /**
     * 头部
     */
    private View mHeaderView;

    /**
     * 底部
     */
    private View mFooterView;
    /**
     * 是否展示空页面
     */
    private boolean showEmpty;
    /**
     * 空页面layout
     */
    private int emptyLayout = R.layout.include_empty;
    /**
     * 是否是第一次加载数据
     */
    private boolean firstLoad = true;
    private RecyclerAdapter.OnItemClickListener mListener;

    public RecyclerAdapter(Context context) {
        mData = new ArrayList<>();
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * 设置空页面
     * @param emptyLayout
     */
    public void setEmptyLayout(@LayoutRes int emptyLayout) {
        this.emptyLayout = emptyLayout;
    }

    /**
     * 添加头部
     *
     * @param headerView
     */
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }

    /**
     * 获取头部
     *
     * @return
     */
    public View getHeaderView() {
        return mHeaderView;
    }

    /**
     * 添加底部
     *
     * @param footerView
     */
    public void setFooterView(View footerView) {
        mFooterView = footerView;
    }

    /**
     * 获取底部
     *
     * @return
     */
    public View getFooterView() {
        return mFooterView;
    }

    /**
     * 设置每行点击事件的监听
     *
     * @param listener
     */
    public void setOnItemClickListener(RecyclerAdapter.OnItemClickListener
                                               listener) {
        mListener = listener;
    }

    public List<M> getData() {
        return mData;
    }

    public M get(int position){
        return mData.get(position);
    }
    /**
     * 设置list为这个list
     *
     * @param data
     */
    public void set(List<M> data) {
        firstLoad = false;
        if (data != null) {
            mData = data;
        }
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    /**
     * list中添加更多的数据
     *
     * @param data
     */
    public void add(List<M> data) {
        if (mData == null) {
            return;
        }
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0&&showEmpty){
            //当前数据空位,展示空页面
            return TYPE_EMPTY;
        }
        if (position == 0 && mHeaderView != null) {
            //当前view是头部信息
            return TYPE_HEADER;
        }
        if (position == getItemCount() && mFooterView != null) {
            //当前view是底部信息
            return TYPE_FOOTER;
        }

        return getCenterViewType(position);
    }

    /**
     * 标准的item的类型
     *
     * @param position
     * @return
     */
    public int getCenterViewType(int position) {
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        int size = mData == null ? 0 : mData.size();
        if (mHeaderView != null) {
            //有头部,item的个数+1
            size++;
        }
        if (mFooterView != null) {
            //有底部,item的个数+1
            size++;
        }
        if(size==0){
            showEmpty = true;
            size = 1;
        }else{
            showEmpty = false;
        }
        return size;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int
            viewType) {
        if (TYPE_HEADER == viewType) {
            return new HeaderAndFooterViewHolder(mHeaderView);
        }
        if (TYPE_FOOTER == viewType) {
            return new HeaderAndFooterViewHolder(mFooterView);
        }
        if(TYPE_EMPTY == viewType){
            View v = inflate(emptyLayout,parent);

            return new HeaderAndFooterViewHolder(v);
        }
        return createHolder(parent, viewType);
    }

    /**
     * 除头部和底部的ViewHodler的获取
     *
     * @param parent
     * @param viewType holder的类型
     * @return
     */
    public abstract VH createHolder(ViewGroup parent, int viewType);

    /**
     * 获取需要viewholder的view
     *
     * @param layoutId 布局文件
     * @param group
     * @return
     */
    protected View inflate(int layoutId, ViewGroup group) {
        View v = mInflater.inflate(layoutId, group, false);
        return v;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int index = position;
        if (mHeaderView != null) {
            //当前holder是头部就直接返回,不需要去设置viewholder的内容
            if (getItemViewType(position) == TYPE_HEADER) {
                return;
            } else {
                /**
                 * 有头部的情况,需要要减1,否则取item的数据会取到当前数据的下一条,
                 * 取出最后一条数据的时候,会报下标溢出
                 */
                index--;
            }
        }
        if (mFooterView != null) {
            //当前holder是底部就直接返回,不需要去设置viewholder的内容
            if (getItemViewType(position) == TYPE_FOOTER) {
                return;
            }
        }
        //空页面状态,不需要设置holder的内容
        if(getItemViewType(position)==TYPE_EMPTY){
            //第一次加载数据,不展示空页面
            if(firstLoad){
                holder.itemView.setVisibility(View.INVISIBLE);
            }else{
                holder.itemView.setVisibility(View.VISIBLE);
            }
            return;
        }
        final int finalIndex = index;
        //设置item的点击回调事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.itemClick(finalIndex);
                }
            }
        });
        M m = mData.get(index);
        bindViewHolder((VH) holder, m, position);
    }

    /**
     * 绑定viewholder的数据
     *
     * @param holder
     * @param m
     * @param position
     */
    public abstract void bindViewHolder(VH holder, M m, int position);

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager
                    .SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (getItemViewType(position) == TYPE_HEADER ||
                            getItemViewType(position) == TYPE_FOOTER) ?
                            gridManager.getSpanCount() : 1;
                }
            });
        }

    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null && lp instanceof StaggeredGridLayoutManager
                .LayoutParams && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p =
                    (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    /**
     * 头部和底部的ViewHolder
     */
    static class HeaderAndFooterViewHolder extends ViewHolder {

        public HeaderAndFooterViewHolder(View itemView) {
            super(itemView);
        }
    }


    protected void show(String msg) {
        InStyleToast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * item点击事件
     */
    public interface OnItemClickListener {
        void itemClick(int position);
    }

    /**
     * adpter下的item数据是否有变化,减少adapter的未改变的数据刷新
     *
     * @param <M>
     */
    public abstract class DiffCallBack<M> extends DiffUtil.Callback {
        protected List<M> mNewList;
        protected List<M> mOldList;

        public DiffCallBack(List<M> newList, List<M> oldList) {
            mNewList = newList;
            mOldList = oldList;
        }

        @Override
        public int getOldListSize() {
            return mOldList.size();
        }

        @Override
        public int getNewListSize() {
            return mNewList.size();
        }
    }
}
