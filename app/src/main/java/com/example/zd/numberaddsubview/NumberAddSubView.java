package com.example.zd.numberaddsubview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class NumberAddSubView extends LinearLayout implements View.OnClickListener{
    private LayoutInflater layoutInflater;
    private Button mAddBtn;
    private Button mSubBtn;
    private TextView mNumTV;

    private int value;
    private int minValue;
    private int maxValue;
    private OnButtonClickListener mOnButtonClickListener;
    public void setmOnButtonClickListener(OnButtonClickListener mOnButtonClickListener) {
        this.mOnButtonClickListener = mOnButtonClickListener;
    }
    public int getValue() {
        String val = mNumTV.getText().toString();
        if(val !=null&&"".equals(val)) {
            this.value = Integer.parseInt(val);
        }
        return value;
    }

    public void setValue(int value) {
        mNumTV.setText(value+"");
        this.value = value;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public NumberAddSubView(Context context) {
        this(context,null);
    }
    public NumberAddSubView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public NumberAddSubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        layoutInflater = LayoutInflater.from(context);
        initview();

        if(attrs !=null) {
            TintTypedArray tta = TintTypedArray.obtainStyledAttributes(
                    context,attrs,R.styleable.NumberAddSubView,defStyleAttr,0
            );
            int val = tta.getInt(R.styleable.NumberAddSubView_value,0);
            setValue(val);
            int minValue = tta.getInt(R.styleable.NumberAddSubView_minValue,0);
            setMinValue(minValue);
            int maxValue = tta.getInt(R.styleable.NumberAddSubView_maxValue,0);
            setMaxValue(maxValue);
            Drawable addBtnDrawable = tta.getDrawable(R.styleable.NumberAddSubView_addBtnBg);
            Drawable subBtnDrawable = tta.getDrawable(R.styleable.NumberAddSubView_subBtnBg);
            setAddBtnBackGround(addBtnDrawable);
            setSubBtnBackGround(subBtnDrawable);
            //安卓自带资源 表示透明   默认是这
            int numTvDrawable = tta.getResourceId(
                    R.styleable.NumberAddSubView_numTvBg,android.R.color.transparent);
            setNumTvBackgroud(numTvDrawable);
            //回收
            tta.recycle();
        }


    }
    private void initview(){
        View view = layoutInflater.inflate(R.layout.wieght_number_add_sub,
                this,true);
        mAddBtn = (Button) view.findViewById(R.id.add_btn);
        mSubBtn = (Button) view.findViewById(R.id.sub_btn);
        mNumTV = (TextView) view.findViewById(R.id.num_tv);

        mAddBtn.setOnClickListener(this);
        mSubBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.add_btn) {
            numAdd();
            if(mOnButtonClickListener != null) {
                mOnButtonClickListener.OnButtonAddClick(v,value);
            }
        }else if(v.getId()==R.id.sub_btn){
            numSub();
            if(mOnButtonClickListener != null) {
                mOnButtonClickListener.OnButtonSubClick(v,value);
            }
        }
    }

    public void setAddBtnBackGround(Drawable addBtnBackGround) {
        this.mAddBtn.setBackground(addBtnBackGround);
    }

    public void setSubBtnBackGround(Drawable subBtnBackGround) {
        this.mSubBtn.setBackground(subBtnBackGround);
    }

    public void setNumTvBackgroud(int numTvBackgroud) {
        this.mNumTV.setBackgroundResource(numTvBackgroud);
    }

    //接口
    public interface OnButtonClickListener{
        void OnButtonAddClick(View view,int value);
        void OnButtonSubClick(View view,int value);
    }
    private void numAdd(){
        if(value<maxValue) {
            value++;
        }
        mNumTV.setText(value+"");
    }
    private void numSub(){
        if(value>minValue) {
            value--;
        }
        mNumTV.setText(value+"");
    }



}
