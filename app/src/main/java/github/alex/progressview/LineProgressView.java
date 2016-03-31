package github.alex.progressview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
/*
 * Created by alex on 2016/3/10.
 */
public class LineProgressView extends View {
    private final int maxProgress = 100;
    private float firstProgress;
    private float secondProgress;
    private Paint maxPaint;
    private Paint firstProgressPaint;
    private Paint secondProgressPaint;
    private int maxProgressColor;
    private int firstProgressColor;
    private int secondProgressColor;
    private int width;
    private int height;
    private float rx;
    private float ry;

    public LineProgressView(Context context) {
        super(context);
        initView();
    }
    public LineProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    private void initView() {
        rx = 40;
        ry = 80;
        /*最大值的 进度值*/
        maxPaint = new Paint();
        maxPaint.setAntiAlias(true);
        maxPaint.setStrokeWidth(2F);
        maxPaint.setStyle(Paint.Style.FILL);
        maxPaint.setColor(maxProgressColor);
        /*第一条  进度条*/
        firstProgressPaint = new Paint();
        firstProgressPaint.setAntiAlias(true);
        firstProgressPaint.setStrokeWidth(2F);
        firstProgressPaint.setStyle(Paint.Style.FILL);
        firstProgressPaint.setColor(firstProgressColor);
        /*第二条  进度条*/
        secondProgressPaint = new Paint();
        secondProgressPaint.setAntiAlias(true);
        secondProgressPaint.setStrokeWidth(2F);
        secondProgressPaint.setStyle(Paint.Style.FILL);
        secondProgressPaint.setColor(secondProgressColor);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF maxRectF = new RectF(0, 0, width, height);
        RectF firstRectF = new RectF(0, 0, width * firstProgress / 100, height);
        RectF secondRectF = new RectF(0, 0, width * secondProgress / 100, height);

        /*绘制  maxProgress */
        canvas.drawRoundRect(maxRectF, rx, ry, maxPaint);
        /*绘制  firstProgress */
        canvas.drawRoundRect(firstRectF, rx, ry, firstProgressPaint);
        /*绘制  secondProgress */
        canvas.drawRoundRect(secondRectF, rx, ry, secondProgressPaint);
    }
    /**
     * 给  maxProgress 设置颜色
     */
    public void setMaxProgressColor(int maxProgressColor) {
        this.maxProgressColor = maxProgressColor;
        maxPaint.setColor(maxProgressColor);
    }
    /**
     * 给  firstProgress 设置颜色
     */
    public void setFirstProgressColor(int firstProgressColor) {
        this.firstProgressColor = firstProgressColor;
        firstProgressPaint.setColor(firstProgressColor);
    }
    /**
     * 给  firstProgress 设置颜色
     */
    public void setSecondProgressColor(int secondProgressColor) {
        this.secondProgressColor = secondProgressColor;
        secondProgressPaint.setColor(secondProgressColor);
    }
    /**
     * 给  firstProgress 设置进度， [0,100 ]
     */
    public void setFirstProgress(float firstProgress) {
        if (firstProgress > maxProgress) {
            firstProgress = maxProgress;
        } else if (firstProgress < 0) {
            firstProgress = 0;
        }
        if (secondProgress > firstProgress) {
            secondProgress = firstProgress;
        } else if (secondProgress < 0) {
            secondProgress = 0;
        }
        this.firstProgress = firstProgress;
        invalidate();
    }
    /**
     * 给  secondProgress 设置进度, [0,100 ]
     */
    public void setSecondProgress(float secondProgress) {
        if (secondProgress > maxProgress) {
            secondProgress = maxProgress;
        } else if (secondProgress < 0) {
            secondProgress = 0;
        }
        if (secondProgress > firstProgress) {
            firstProgress = secondProgress;
        }
        this.secondProgress = secondProgress;
        invalidate();
    }
    /**
     * 设置远角化半径 单位dp
     * rx  -  40;   ry - 80
     * */
    public void setRadius(float rx, float ry){
        this.rx = dp2Px(rx);
        this.ry = dp2Px(ry);
        invalidate();
    }
    /**
     * 获取 firstProgress  的进度值
     */
    public float getFirstProgress() {
        return firstProgress;
    }
    /**
     * 获取 secondProgress  的进度值
     */
    public float getSecondProgress() {
        return secondProgress;
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }
    /**数据转换: dp---->px*/
    private float dp2Px(float dp) {
        return dp * getContext().getResources().getDisplayMetrics().density;
    }
}