package com.example.viewpager_anim;

import java.util.HashMap;
import java.util.Map;

import android.R.integer;
import android.R.string;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
//自定义ViewPager实现2个View的切换动画
public class ViewPagerWithTransformerAnim extends ViewPager{
	
	private View mleft;
	private View mRight;
	
	private float mTrans;
	private float mScale;
	
	private static final float MIN_SCALE = 0.5f;
	
	private Map<Integer, View> mChildren = new HashMap<Integer , View >();
	
	public void setViewForPosition(View view , int position)
	{
		mChildren.put(position, view);
	}
	
	public void removeViewFromPosition(Integer position)
	{
		mChildren.remove(position);
	}
	public ViewPagerWithTransformerAnim(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		//arg0 是 position , arg1是offset ,arg2是offsetPixels
		Log.i("tag", "postion" + arg0 + " , offset = " + arg1);
		
		mleft = mChildren.get(arg0);
		mRight = mChildren.get(arg0 + 1);
		
		animStack(mleft , mRight , arg1 , arg2);
		super.onPageScrolled(arg0, arg1, arg2);
	}
	
	private void animStack(View left, View right, float offset, int offsetPixels) {
		// TODO Auto-generated method stub
		if (right != null) {
			mScale = ( 1 - MIN_SCALE)* offset + MIN_SCALE;
			
			mTrans = -getWidth() - getPageMargin() + offsetPixels;
			
			ViewHelper.setScaleX(right,mScale);
			ViewHelper.setScaleY(right,mScale);
			
			ViewHelper.setTranslationX(right,mTrans);
		}
		if (left != null) {
			left.bringToFront();
		}
	}

	public ViewPagerWithTransformerAnim(Context context)
	{
		super(context);
	}
	
}
