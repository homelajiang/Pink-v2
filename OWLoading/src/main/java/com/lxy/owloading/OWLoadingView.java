package com.lxy.owloading;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
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
    private ValueAnimator animator;
    private int DEFAULT_SIZE = 130;
    private Paint paint;
    private float hexagonRadius;
    private OWPoint center = new OWPoint();
    private float space;
    private OWPoint[] hexagonCenters = new OWPoint[6];
    private Hexagon[] hexagons = new Hexagon[7];
    private boolean showingHexagon = true;
    private final float scaleCritical = 0.7f;
    private boolean hasMeasure;
    private int color = Color.parseColor("#ff9900");//默认橙色

    public OWLoadingView(Context context) {
        this(context, null);
    }

    public OWLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OWLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.OWLoadingView);
        DEFAULT_SIZE = a.getDimensionPixelSize(R.styleable.OWLoadingView_default_size, DEFAULT_SIZE);
        color = a.getColor(R.styleable.OWLoadingView_color, color);
        a.recycle();
    }

    private void init() {
        initPaint();
        initAnimator();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        paint.setPathEffect(new CornerPathEffect(hexagonRadius * 0.1f));
    }

    private void initAnimator() {
        animator = ObjectAnimator.ofInt(0, 10);
        animator.setDuration(200);
        animator.addUpdateListener(animatorUpdateListener);
        animator.setRepeatCount(-1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int measuredWidth = resolveSizeAndState(DEFAULT_SIZE, widthMeasureSpec, 0);
        final int measuredHeight = resolveSizeAndState(DEFAULT_SIZE, heightMeasureSpec, 0);
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh) {
            if (w > 0 && h > 0) {
                int res = Math.min(w, h);
                center.x = w / 2;
                center.y = h / 2;
                float spaceRate = 1 / 100f;
                space = res * spaceRate;
                hexagonRadius = (float) ((res - 2 * space) / (3 * Math.sqrt(3)));
                initHexagonCenters();
                stopAnim();
                animator.start();
            }
        }

    }

    private void initHexagonCenters() {
        float bigR = (float) ((1.5 * hexagonRadius + space) * 2 / Math.sqrt(3));
        hexagonCenters[0] = new OWPoint(center.x - bigR / 2, (float) (center.y - bigR * Math.sqrt(3) / 2));
        hexagonCenters[1] = new OWPoint(center.x + bigR / 2, (float) (center.y - bigR * Math.sqrt(3) / 2));
        hexagonCenters[2] = new OWPoint(center.x + bigR, center.y);
        hexagonCenters[3] = new OWPoint(center.x + bigR / 2, (float) (center.y + bigR * Math.sqrt(3) / 2));
        hexagonCenters[4] = new OWPoint(center.x - bigR / 2, (float) (center.y + bigR * Math.sqrt(3) / 2));
        hexagonCenters[5] = new OWPoint(center.x - bigR, center.y);

        for (int i = 0; i < 6; i++) {
            hexagons[i] = new Hexagon(hexagonCenters[i], hexagonRadius);
        }
        hexagons[6] = new Hexagon(center, hexagonRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < 7; i++) {
            hexagons[i].drawHexagon(canvas, paint);
        }
    }

    /**
     * 设置颜色
     *
     * @param color
     */
    public void setColor(int color) {
        this.color = color;
        paint.setColor(color);
    }

    /**
     * 开始动画
     */
    public void startAnim() {
        initAnimator();
        animator.start();
    }

    /**
     * 中止动画
     */
    public void stopAnim() {
        animator.cancel();
        animator.removeAllListeners();
        animator = null;
        showingHexagon = true;
        resetHexagons();
        invalidate();
    }

    private void resetHexagons() {
        for (int i = 0; i < hexagons.length; i++) {
            hexagons[i].setScale(0);
            hexagons[i].setAlpha(0);
        }
    }

    private ValueAnimator.AnimatorUpdateListener animatorUpdateListener
            = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            if (showingHexagon) {
                hexagons[0].addScale();
                hexagons[0].addAlpha();
                for (int i = 0; i < hexagons.length - 1; i++) {
                    if (hexagons[i].getScale() >= scaleCritical) {
                        hexagons[i + 1].addScale();
                        hexagons[i + 1].addAlpha();
                    }
                }
                if (hexagons[6].getScale() == 1) {
                    showingHexagon = false;
                }
            } else {
                hexagons[0].subScale();
                hexagons[0].subAlpha();
                for (int i = 0; i < hexagons.length - 1; i++) {
                    if (hexagons[i].getScale() < 1-scaleCritical) {
                        hexagons[i + 1].subScale();
                        hexagons[i + 1].subAlpha();

                    }
                }
                if (hexagons[6].getScale() == 0) {
                    showingHexagon = true;
                }
            }
            invalidate();
        }
    };

    public class Hexagon {
        private float sin30 = (float) Math.sin(30f * 2f * Math.PI / 360f);
        private float cos30 = (float) Math.cos(30f * 2f * Math.PI / 360f);
        private float scale = 0;
        private int alpha = 0;
        public OWPoint centerPotint;
        public float radius;
        private OWPoint[] vertexs = new OWPoint[6];
        private float scaleChange = 0.06f;
        private float alphaChange = 15;

        public Hexagon(OWPoint centerPoint, float radius) {
            this.centerPotint = centerPoint;
            this.radius = radius;
            //计算定点
            calculatePointsPosition();
        }

        public void drawHexagon(Canvas canvas, Paint paint) {
            paint.setAlpha(alpha);
            canvas.drawPath(getPath(), paint);
        }

        private int calculatePointsPosition() {
            if (centerPotint == null)
                return -1;


            vertexs[0] = new OWPoint(centerPotint.x, centerPotint.y - radius * scale);
            vertexs[1] = new OWPoint(centerPotint.x + radius * cos30 * scale, centerPotint.y - radius * sin30 * scale);
            vertexs[2] = new OWPoint(centerPotint.x + radius * cos30 * scale, centerPotint.y + radius * sin30 * scale);
            vertexs[3] = new OWPoint(centerPotint.x, centerPotint.y + radius * scale);
            vertexs[4] = new OWPoint(centerPotint.x - radius * cos30 * scale, centerPotint.y + radius * sin30 * scale);
            vertexs[5] = new OWPoint(centerPotint.x - radius * cos30 * scale, centerPotint.y - radius * sin30 * scale);
            return 1;
        }

        public Path getPath() {
            Path path = new Path();
            for (int i = 0; i < vertexs.length; i++) {
                if (i == 0) {
                    path.moveTo(vertexs[i].x, vertexs[i].y);
                } else {
                    path.lineTo(vertexs[i].x, vertexs[i].y);
                }
            }
            path.close();
            return path;
        }

        public float getScale() {
            return scale;
        }

        public void setScale(float scale) {
            this.scale = scale;
            calculatePointsPosition();
        }

        public int getAlpha() {
            return alpha;
        }

        public void setAlpha(int alpha) {
            this.alpha = alpha;
        }

        public void addScale() {
            scale += scaleChange;
            scale = scale > 1 ? 1 : scale;
            calculatePointsPosition();
        }

        public void subScale() {
            scale -= scaleChange;
            scale = scale < 0 ? 0 : scale;
            calculatePointsPosition();
        }

        public void addAlpha() {
            alpha += alphaChange;
            alpha = alpha > 255 ? 255 : alpha;
        }

        public void subAlpha() {
            alpha -= alphaChange;
            alpha = alpha < 0 ? 0 : alpha;
        }
    }

    public class OWPoint {
        public float x;
        public float y;

        public OWPoint() {
        }

        public OWPoint(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
}
