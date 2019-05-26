package com.zjr.lighttextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * 作者：created by zjr on 2019/5/24 18 42
 * 邮箱：2759442036@qq.com
 */
public class LightTextView extends LinearLayout{

    private TextView tv_left;
    private TextView tv_center;
    private TextView tv_right;
    private ImageView iv_left;
    private ImageView iv_right;

    private LinearLayout container;

    //点击事件监听对象
    private OnLeftTextClickListener monLeftTextClickListener =null;
    private OnCenterTextClickListener monCenterTextClickListener = null;
    private OnRightTextClickListener monRightTextClickListener = null;
    private OnLightTextViewClickListener monLightTextViewClickListener = null;
    private OnLeftImageViewClickListener monLeftImageViewClickListener = null;
    private OnRightImageViewClickListener monRightImageViewClickListener = null;



    public LightTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_lighttextview,this);
        tv_left = findViewById(R.id.tv_left);
        tv_center =findViewById(R.id.tv_center);
        tv_right = findViewById(R.id.tv_right);
        iv_left = findViewById(R.id.iv_left);
        iv_right = findViewById(R.id.iv_right);

        container = findViewById(R.id.container);


        //注册点击事件
        tv_left.setOnClickListener(ClickEvent);
        tv_center.setOnClickListener(ClickEvent);
        tv_right.setOnClickListener(ClickEvent);
        container.setOnClickListener(ClickEvent);
        iv_left.setOnClickListener(ClickEvent);
        iv_right.setOnClickListener(ClickEvent);

        InitAttributeSet(context,attrs);

    }


    //初始化attribute属性
    public void InitAttributeSet(Context context,AttributeSet attrs){

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LightTextView);

        //设置字体内容
        String lefttextstring = array.getString(R.styleable.LightTextView_leftTextString);
        String centertextstring = array.getString(R.styleable.LightTextView_centerTextString);
        String rightcenterstring = array.getString(R.styleable.LightTextView_righTxtString);

        tv_left.setText(lefttextstring);
        tv_center.setText(centertextstring);
        tv_right.setText(rightcenterstring);

        //设置字体大小
        float scale = context.getResources().getDisplayMetrics().density;
        float lefttextsize = array.getDimension(R.styleable.LightTextView_leftTextSize,18);
        float centertextsize = array.getDimension(R.styleable.LightTextView_leftTextSize,18);
        float righttextsize = array.getDimension(R.styleable.LightTextView_leftTextSize,18);

        tv_left.setTextSize(lefttextsize/scale);
        tv_center.setTextSize(centertextsize/scale);
        tv_right.setTextSize(righttextsize/scale);

        //设置字体颜色
        int lefttextColor = array.getColor(R.styleable.LightTextView_leftTextColor, Color.parseColor("#777777"));
        int centertextColor = array.getColor(R.styleable.LightTextView_centerTextColor, Color.parseColor("#777777"));
        int righttextColor = array.getColor(R.styleable.LightTextView_rightTextColor, Color.parseColor("#777777"));

        tv_left.setTextColor(lefttextColor);
        tv_center.setTextColor(centertextColor);
        tv_right.setTextColor(righttextColor);

        //设置LightTextView的背景颜色
        int lightcolor = array.getColor(R.styleable.LightTextView_sLightTextViewBacgroundColor, Color.parseColor("#ffffff"));
        container.setBackgroundColor(lightcolor);

        //设置左边ImageView的图片资源
        Drawable leftres = array.getDrawable(R.styleable.LightTextView_leftImageRes);
        iv_left.setImageDrawable(leftres);

        //设置右边ImageView的图片资源
        Drawable rightres = array.getDrawable(R.styleable.LightTextView_rightImageRes);
        iv_right.setImageDrawable(rightres);

        //设置左边ImageView左边的边距
        float leftmarginleft = array.getDimension(R.styleable.LightTextView_leftImageMarginLeft,0);
        iv_left.setPadding((int)leftmarginleft,0,0,0);

        //设置左边ImageiView右边的边距
        float leftmarginright = array.getDimension(R.styleable.LightTextView_rightImageMaiginRight,0);
        iv_left.setPadding((int)leftmarginleft,0,(int)leftmarginright,0);

        //设置右边ImageView右边的边距
        float rightmarginright = array.getDimension(R.styleable.LightTextView_rightImageMarginright,15);
        iv_right.setPadding(0,0,(int)rightmarginright,0);

        //设置右边ImageView左边的边距
        float rightmarginleft = array.getDimension(R.styleable.LightTextView_rightImageMarginleft,0);
        iv_right.setPadding((int)rightmarginleft,0,(int)rightmarginright,0);


        array.recycle();
    }


    //提供注册系统TextvView点击事件时的OnClickListener对象
    private OnClickListener ClickEvent = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_left:
                    if(monLeftTextClickListener != null){
                        monLeftTextClickListener.OnClick();
                    }
                    break;
                case R.id.tv_center:
                    if (monCenterTextClickListener != null){
                        monCenterTextClickListener.OnClick();
                    }
                    break;
                case R.id.container:
                    if (monLightTextViewClickListener != null){
                        monLightTextViewClickListener.OnClick();
                    }
                    break;
                case R.id.iv_left:
                    if (monLeftImageViewClickListener !=null){
                        monLeftImageViewClickListener.Onclick();
                    }
                    break;
                case R.id.iv_right:
                    if (monRightImageViewClickListener != null){
                        monRightImageViewClickListener.Onclick();
                    }
                    break;
            }
        }
    };


    //设置左边Textview内容
    public LightTextView setLeftstring(String leftstring){
        tv_left.setText(leftstring);
        return this;
    }

    //设置中间边Textview内容
    public LightTextView setCenterString(String centerString){
        tv_center.setText(centerString);
        return this;
    }

    //设置右边Textview内容
    public LightTextView setRightString(String rightString){
        tv_right.setText(rightString);
        return this;
    }

    //返回左边TextView的内容
    public String getLeftString(){
        return tv_left.getText().toString();
    }

    //返回中间TextView的内容
    public String getCenterString(){
        return tv_left.getText().toString();
    }

    //返回右边TextView的内容
    public String getRightString(){
        return tv_left.getText().toString();
    }

    //设置左边字体大小
    public LightTextView setLeftTextSize(float leftTextSize){
        tv_left.setTextSize(leftTextSize);
        return this;
    }

    //设置左边字体大小
    public LightTextView setCenterTextSize(float centerTextSize){
        tv_center.setTextSize(centerTextSize);
        return this;
    }

    //设置左边字体大小
    public LightTextView setRightTextSize(float rightTextSize){
        tv_right.setTextSize(rightTextSize);
        return this;
    }

    //设置左边TextView字体颜色
    public LightTextView setLeftTextColor(int leftColor){
        tv_left.setTextColor(leftColor);
        return this;
    }

    //设置中间TextView字体颜色
    public LightTextView setcenterTextColor(int centerColor){
        tv_center.setTextColor(centerColor);
        return this;
    }

    //设置右边TextView字体颜色
    public LightTextView setRightTextColor(int rightColor){
        tv_right.setTextColor(rightColor);
        return this;
    }

    //设置LightTextView的背景颜色
    public LightTextView setLightTextViewBacgroundColor(int color){
        container.setBackgroundColor(color);
        return this;
    }









    /**
     * 左边TextView点击事件的处理
      **/
    //注册左边Textview点击事件
    public LightTextView setLeftTextOnClickListener(OnLeftTextClickListener onLeftTextClickListener){
        this.monLeftTextClickListener = onLeftTextClickListener;
        return this;
    }

    //提供左边TextView点击事件接口，通过回调机制，实现用户自定义点击事件
    public interface OnLeftTextClickListener{
        void OnClick();
    }



    /**
     * 中间TextView点击事件的处理
      **/
    //注册中间Textview点击事件
    public LightTextView setCenterTextOnClickListener(OnCenterTextClickListener onCenterTextClickListener){
        this.monCenterTextClickListener = onCenterTextClickListener;
        return this;
    }

    //提供中间TextView点击事件接口，通过回调机制，实现用户自定义点击事件
    public interface OnCenterTextClickListener{
        void OnClick();
    }



    /**
     * 右边TextView点击事件的处理
     **/
    //注册右边Textview点击事件
    public LightTextView setRightTextOnClickListener(OnRightTextClickListener onRightTextClickListener){
        this.monRightTextClickListener = onRightTextClickListener;
        return this;
    }

    //提供右边TextView点击事件接口，通过回调机制，实现用户自定义点击事件
    public interface OnRightTextClickListener{
        void OnClick();
    }


    /**
     * 整个LightTextView的点击事件的处理
     */
    public LightTextView setLightTextViewOnClickListener(OnLightTextViewClickListener onLightTextViewClickListener){
        this.monLightTextViewClickListener = onLightTextViewClickListener;
        return this;
    }

    //提供LightTextView点击事件接口，通过回调机制，实现用户自定义点击事件
    public interface OnLightTextViewClickListener{
        void OnClick();
    }


    /**
     * 左边ImageView的点击事件的处理
     */
    public LightTextView setLeftImageViewOnClickListener(OnLeftImageViewClickListener onLeftImageViewClickListener){
        this.monLeftImageViewClickListener = onLeftImageViewClickListener;
        return this;
    }

    public interface OnLeftImageViewClickListener{
        void Onclick();
    }


    /**
     * 右边ImageView的点击事件的处理
     */
    public LightTextView setRightImageViewOnClickListener(OnRightImageViewClickListener onRightImageViewClickListener){
        this.monRightImageViewClickListener = onRightImageViewClickListener;
        return this;
    }

    public interface OnRightImageViewClickListener{
        void Onclick();
    }

}
