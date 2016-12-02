package com.lxy.lodingview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by homelajiang on 2016/12/1 0001.
 */

public class OWLoadingView extends View {
    private float sin30 = (float) Math.sin(30f * 2f * Math.PI / 360f);
    private float cos30 = (float) Math.cos(30f * 2f * Math.PI / 360f);
    private ValueAnimator animator;

    private int viewWidth, viewHeight;
    private Point center = new Point();
    private Point[] hexagonCenters = new Point[6];
    private Hexagon[] hexagons = new Hexagon[7];
    private float space;
    private float hexagonRadius;
    private Paint paint;
    private int color = Color.parseColor("#ff9900");//默认橙色
    //进行显示动画和进行隐藏动画的标志常量
    private final int ShowAnimatorFlag = 0x1137, HideAnimatorFlag = 0x1139;
    private int nowAnimatorFlag = ShowAnimatorFlag;
    //触发下一个动画开始的缩放临界点值
    private final float scaleCritical = 0.7f;

    public OWLoadingView(Context context) {
        super(context);
        init();
    }

    public OWLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OWLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initAnimator();
    }

    private void initAnimator() {
        animator = ObjectAnimator.ofInt(0, 10);
        animator.setDuration(200);
        animator.addUpdateListener(animatorUpdateListener);
        animator.setRepeatCount(-1);
    }

    private void resetHexagons() {
        for (int i = 0; i < hexagons.length; i++) {
            hexagons[i].setScale(0);
            hexagons[i].setAlpha(0);
        }
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        CornerPathEffect cornerPathEffect = new CornerPathEffect(hexagonRadius * 0.1f);
        paint.setPathEffect(cornerPathEffect);
    }

    private void setColor(int color) {
        this.color = color;
        paint.setColor(color);
    }

    public void startAnim() {
        initAnimator();
        animator.start();
    }

    public void stopAnim() {
        animator.cancel();
        animator.removeAllListeners();
        animator = null;
        nowAnimatorFlag = ShowAnimatorFlag;
        resetHexagons();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < hexagons.length; i++) {
            hexagons[i].drawHexagon(canvas, paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewHeight = getMeasuredHeight();
        viewWidth = getMeasuredWidth();
        if (viewWidth != 0 && viewHeight != 0) {
            center.x = viewWidth / 2f;
            center.y = viewHeight / 2f;
            float spaceRate = 1 / 100f;
            space = viewWidth <= viewHeight ? viewWidth * spaceRate : viewHeight * spaceRate;
            hexagonRadius = (float) ((viewWidth - 2 * space) / (3 * Math.sqrt(3)));
            initPaint();
            initHexagonCenters();
        }
    }

    private void initHexagonCenters() {
        float bigR = (float) ((1.5 * hexagonRadius + space) / cos30);
        hexagonCenters[0] = new Point(center.x - bigR * sin30, center.y - bigR * cos30);
        hexagonCenters[1] = new Point(center.x + bigR * sin30, center.y - bigR * cos30);
        hexagonCenters[2] = new Point(center.x + bigR, center.y);
        hexagonCenters[3] = new Point(center.x + bigR * sin30, center.y + bigR * cos30);
        hexagonCenters[4] = new Point(center.x - bigR * sin30, center.y + bigR * cos30);
        hexagonCenters[5] = new Point(center.x - bigR, center.y);

        for (int i = 0; i < 6; i++) {
            hexagons[i] = new Hexagon(hexagonCenters[i], hexagonRadius);
        }
        hexagons[6] = new Hexagon(center, hexagonRadius);
    }

    private ValueAnimator.AnimatorUpdateListener animatorUpdateListener =
            new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    if (nowAnimatorFlag == ShowAnimatorFlag) {
                        hexagons[0].addScale();
                        hexagons[0].addAlpha();
                        for (int i = 0; i < hexagons.length - 1; i++) {
                            if (hexagons[i].getScale() > scaleCritical) {
                                hexagons[i + 1].addScale();
                                hexagons[i + 1].addAlpha();
                            }
                        }
                        if (hexagons[6].getScale() == 1) {
                            nowAnimatorFlag = HideAnimatorFlag;
                        }
                    } else {
                        hexagons[0].subScale();
                        hexagons[0].subAlpha();
                        for (int i = 0; i < hexagons.length - 1; i++) {
                            if (hexagons[i].getScale() <= 1 - scaleCritical) {
                                hexagons[i + 1].subScale();
                                hexagons[i + 1].subAlpha();
                            }
                        }
                        if (hexagons[6].getScale() == 0) {
                            nowAnimatorFlag = ShowAnimatorFlag;
                        }
                    }
                    invalidate();
                }
            };

    private class Hexagon {
        private float scale = 0;//缩放
        private int alpha = 0;//透明度
        public Point centerPoint;//中心点
        public float radius;
        private Point[] vertexs = new Point[6];//其余6个定点
        private final float scaleChange = 0.06f;
        private final int alpahChange = 15;

        public Hexagon(Point centerPoint, float radius) {
            this.centerPoint = centerPoint;
            this.radius = radius;
            calculatePointsPosition();
        }

        public void drawHexagon(Canvas canvas, Paint paint) {
            paint.setAlpha(alpha);
            canvas.drawPath(getPath(), paint);
        }

        private int calculatePointsPosition() {
            if (centerPoint == null) {
                return -1;
            }
            //从最上方顺时针数1-6给各顶点标序号 共6个点
            vertexs[0] = new Point(centerPoint.x, centerPoint.y - radius * scale);
            vertexs[1] = new Point(centerPoint.x + radius * cos30 * scale, centerPoint.y - radius * sin30 * scale);
            vertexs[2] = new Point(centerPoint.x + radius * cos30 * scale, centerPoint.y + radius * sin30 * scale);
            vertexs[3] = new Point(centerPoint.x, centerPoint.y + radius * scale);
            vertexs[4] = new Point(centerPoint.x - radius * cos30 * scale, centerPoint.y + radius * sin30 * scale);
            vertexs[5] = new Point(centerPoint.x - radius * cos30 * scale, centerPoint.y - radius * sin30 * scale);
            return 1;
        }

        private Path getPath() {
            Path path = new Path();
            for (int i = 0; i < 6; i++) {
                if (i == 0)
                    path.moveTo(vertexs[i].x, vertexs[i].y);
                else
                    path.lineTo(vertexs[i].x, vertexs[i].y);
            }
            path.close();
            return path;
        }

        private void setAlpha(int alpha) {
            this.alpha = alpha;
        }

        public int getAlpha() {
            return alpha;
        }

        public void setScale(float scale) {
            this.scale = scale;
            calculatePointsPosition();
        }

        public void addScale() {
            if (scale == 1)
                return;
            scale += scaleChange;
            scale = scale > 1 ? 1 : scale;
            calculatePointsPosition();
        }

        public void subScale() {
            if (scale == 0)
                return;
            scale -= scaleChange;
            scale = scale < 0 ? 0 : scale;
            calculatePointsPosition();
        }

        public void addAlpha() {
            if (alpha == 255)
                return;
            alpha += alpahChange;
            alpha = alpha > 255 ? 255 : alpha;
        }

        public void subAlpha() {
            if (alpha == 0)
                return;
            alpha -= alpahChange;
            alpha = alpha < 0 ? 0 : alpha;
        }

        public float getScale() {
            return scale;
        }
    }

    private class Point {
        public float x, y;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public Point() {
        }
    }
}
