package hu.ait.android.tictactoe.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import hu.ait.android.tictactoe.MainActivity;

public class TicTacToeView extends View {

    private Paint paintBg;
    private Paint paintLine;

    private List<PointF> circlesList = new ArrayList<>();

    public TicTacToeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paintBg = new Paint();
        paintBg.setColor(Color.BLACK);
        paintBg.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine.setColor(Color.WHITE);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBg);

        drawGameField(canvas);

        drawCircles(canvas);

    }

    private void drawGameField(Canvas canvas) {
        // border
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintLine);
        // two horizontal lines
        canvas.drawLine(0, getHeight() / 3, getWidth(), getHeight() / 3,
                paintLine);
        canvas.drawLine(0, 2 * getHeight() / 3, getWidth(),
                2 * getHeight() / 3, paintLine);

        // two vertical lines
        canvas.drawLine(getWidth() / 3, 0, getWidth() / 3, getHeight(),
                paintLine);
        canvas.drawLine(2 * getWidth() / 3, 0, 2 * getWidth() / 3, getHeight(),
                paintLine);
    }

    private void drawCircles(Canvas canvas) {
        for (PointF circleMid : circlesList) {
            canvas.drawCircle(circleMid.x, circleMid.y,100, paintLine);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d("TAG_DRAW", "x: " + event.getX() + " # y: " + event.getY());
            circlesList.add(new PointF(event.getX(),
                    event.getY()));

            // redraw this view
            invalidate();

            if (circlesList.size() > 5) {
                ((MainActivity)getContext()).showTooManyCirclesMessage();
            }

        }

        return true;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int d = w == 0 ? h : h == 0 ? w : w < h ? w : h;
        setMeasuredDimension(d, d);
    }

    public void restartGame() {
        circlesList.clear();
        invalidate();
    }

}
