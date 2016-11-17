package com.example.reasetextview;

/**
 * Created by lee on 2014/7/29.
 */
public interface RiseNumberBase {
    /**
     * 开始动画
     */
    public void start();

    /**
     * 设置终点值
     * @param number 终点值
     * @return 控件对象
     */
    public RiseNumberTextView withNumber(float number);
    public RiseNumberTextView withNumber(int number);

    public RiseNumberTextView fromNumber(int number);
    public RiseNumberTextView fromNumber(float number);


    public RiseNumberTextView setDuration(long duration);
    public void setOnEnd(RiseNumberTextView.EndListener callback);
}
