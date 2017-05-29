package simple.zhuazhu.com.simple;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhuazhu.divider.SpacesItemDecoration;
import com.zhuazhu.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.setDebug(true);
        initView();
    }
    private MainAdapter mAdapter;
    private void initView(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mAdapter = new MainAdapter(this);
        GridLayoutManager manager = new GridLayoutManager(this,3);
        manager.setOrientation(GridLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(manager);
        SpacesItemDecoration decoration = new SpacesItemDecoration(15.1f,10.1f);
//        decoration.setOutermostBorder(false);
        decoration.setColor(Color.RED);

//        DividerItemDecoration decoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
//        decoration.setDrawable(ContextCompat.getDrawable(this,R.drawable.divider1_red));
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setAdapter(mAdapter);
        List<Object> objs = new ArrayList<>();
        for (int i=0;i<29;i++){
            Object obj = new Object();
            objs.add(obj);
        }
        mAdapter.set(objs);
    }
}
