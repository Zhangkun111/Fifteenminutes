package test.bwie.com.fifteenminutes.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import test.bwie.com.fifteenminutes.R;
import test.bwie.com.fifteenminutes.adpter.ListAdpter;
import test.bwie.com.fifteenminutes.bean.Bean;
import test.bwie.com.fifteenminutes.fragment.FragmentA;
import test.bwie.com.fifteenminutes.fragment.FragmentB;
import test.bwie.com.fifteenminutes.fragment.FragmentC;
import test.bwie.com.fifteenminutes.login.Login3Activity;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout dl;
    private LinearLayout ll;
    private ListView lv;
    private List<Bean> list;
    private SimpleDraweeView sdv;

    private FragmentTabHost mTabHost;
    //定义一个布局
    private LayoutInflater layoutInflater;
    //定义数组来存放Fragment界面
    private Class fragmentArray[] = {FragmentA.class,FragmentB.class,FragmentC.class};
    //定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.tab_shouye,R.drawable.tab_fenlei,
            R.drawable.tab_wode};

    //Tab选项卡的文字
    private String mTextviewArray[] = {"推荐", "段子", "视频"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        sdv= (SimpleDraweeView) findViewById(R.id.sdv);
        Uri uri = Uri.parse("res://test.bwie.com.fifteenminutes/"+R.mipmap.raw_1499936862);
        sdv.setImageURI(uri);
        sdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(MainActivity.this, Login3Activity.class);
                startActivity(it);
            }
        });
        lv= (ListView) findViewById(R.id.lv);
        dl= (DrawerLayout) findViewById(R.id.dl);
        ll= (LinearLayout) findViewById(R.id.ll);
        dl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                ll.setX(slideOffset*drawerView.getWidth());
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        list=new ArrayList<>();
        list.add(new Bean("我的关注",R.mipmap.favorite));
        list.add(new Bean("我的收藏",R.mipmap.iconfontxingxing));
        list.add(new Bean("搜索好友",R.mipmap.wxb));
        list.add(new Bean("消息通知",R.mipmap.zhong));
        ListAdpter la=new ListAdpter(MainActivity.this,list);
        lv.setAdapter(la);

    }
    private void initView() {
        //实例化布局对象
        layoutInflater = LayoutInflater.from(this);

        //实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        //得到fragment的个数
        int count = fragmentArray.length;

        for(int i = 0; i < count; i++){
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec spec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));

            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(spec, fragmentArray[i], null);

            //设置Tab按钮的背景
            //mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
        }

    }
    private View getTabItemView(int index){
        View view = layoutInflater.inflate(R.layout.tabitem, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);
        return view;
    }
}
