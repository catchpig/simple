package simple.zhuazhu.com.simple;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * 创建时间:2017/5/27 18:42<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/5/27 18:42<br/>
 * 描述:
 */

public class MainAdapter extends RecyclerAdapter<MainAdapter.ViewHolder,Object> {
    public MainAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder createHolder(ViewGroup parent, int viewType) {
        View v = inflate(R.layout.item_main,parent);
        return new ViewHolder(v);
    }

    @Override
    public void bindViewHolder(ViewHolder holder, Object o, int position) {

    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View v){
            super(v);
        }
    }
}
