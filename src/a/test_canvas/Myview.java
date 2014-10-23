package a.test_canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.MotionEvent;
import android.view.View;

public class Myview extends View{
	private Paint paint;
	private float newX,newY;
	private float x1=0,x2=0,y1=10,y2=-135;
	public Myview(Context context) {
		super(context);
		paint=new Paint();
		paint.setColor(Color.RED);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeWidth(3);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		newX=event.getX();
		newY=event.getY();
		setNewLinePoint(newX-this.getWidth()/2, newY-this.getHeight());
		Myview.this.invalidate();
		return super.onTouchEvent(event);
	}
	private void setNewLinePoint(float x,float y){
		float z=(float) Math.sqrt(x*x+y*y);
		x1=-10*x/z;
		y1=-10*x/z;
		x2=65*x/z;
		y2=65*x/z;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.STROKE);
		canvas.translate(canvas.getWidth()/2, canvas.getHeight()/2);
		canvas.drawCircle(0, 0, 150, paint);
		Paint citePaint =new Paint(paint);
		citePaint.setTextSize(14);
		citePaint.setStrokeWidth(1);
		
		Paint numPaint=new Paint(paint);
		numPaint.setStrokeWidth(1);
		numPaint.setTextSize(25);
		
		float y=150;
		int count = 60;
		for(int i=0;i<count;i++){
			if(i%5==0){
				canvas.drawLine(0, y, 0, y+12f, paint);
				canvas.drawText(String.valueOf(i/5+1), -8f, y+35f, numPaint);
			}else{
				canvas.drawLine(0, y, 0, y+5f, numPaint);
			}
			canvas.rotate(360/count, 0, 0);
		}
		numPaint.setColor(Color.GRAY);
		numPaint.setStrokeWidth(4);
		canvas.drawCircle(0, 0, 10, numPaint);
		numPaint.setStyle(Style.FILL);
		numPaint.setColor(Color.GREEN);
		canvas.drawCircle(0, 0, 5, numPaint);
		canvas.drawLine(x1, y1, x2, y2, paint);
		super.onDraw(canvas);
	}
	
}
