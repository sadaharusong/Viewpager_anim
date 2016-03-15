package com.example.viewpager_anim;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;


public class MainActivity extends Activity {
//	private ViewPager mViewPager;
	
	private ViewPagerWithTransformerAnim mViewPager;
	private int[] mImgIds = new int[]{R.drawable.guide_image1,R.drawable.guide_image2,R.drawable.guide_image3};
	private List<ImageView> mImages = new ArrayList<ImageView>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		setContentView(R.layout.activity_main);
		setContentView(R.layout.activity_main2);
//		mViewPager =(ViewPager) findViewById(R.id.id_viewpager);
		mViewPager =(ViewPagerWithTransformerAnim) findViewById(R.id.id_viewpager);
		//为ViewPager添加动画效果 3.0以上有效
//		mViewPager.setPageTransformer(true, new DepthPageTransformer());  //添加切换效果
		mViewPager.setAdapter(new PagerAdapter() {
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// TODO Auto-generated method stub
				
				ImageView imageview = new ImageView(MainActivity.this);
				imageview.setImageResource(mImgIds[position]);
				imageview.setScaleType(ScaleType.CENTER_CROP);
				container.addView(imageview);
				mImages.add(imageview);
				mViewPager.setViewForPosition(imageview, position);
				return imageview;
			}
			
			@Override
			public void destroyItem(ViewGroup container, int position, Object object) {
				container.removeView(mImages.get(position));
				mViewPager.removeViewFromPosition(position);
			}
			
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mImgIds.length;
			}
		});
	}

	
}
