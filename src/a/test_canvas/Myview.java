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
	private float x1=0,x2=0,y1=10,y2=-65;
	public Myview(Context context) {
		super(context);
		paint=new Paint();
		paint.setColor(Color.YELLOW);
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
		canvas.drawCircle(0, 0, 100, paint);
		Paint citePaint =new Paint(paint);
		citePaint.setTextSize(14);
		citePaint.setStrokeWidth(1);
		
		Paint tmpPaint=new Paint(paint);
		tmpPaint.setStrokeWidth(1);
		
		float y=100;
		int count = 60;
		for(int i=0;i<count;i++){
			if(i%5==0){
				canvas.drawLine(0, y, 0, y+12f, paint);
				canvas.drawText(String.valueOf(i/5+1), -4f, y+25f, tmpPaint);
			}else{
				canvas.drawLine(0, y, 0, y+5f, tmpPaint);
			}
			canvas.rotate(360/count, 0, 0);
		}
		tmpPaint.setColor(Color.GRAY);
		tmpPaint.setStrokeWidth(4);
		canvas.drawCircle(0, 0, 7, tmpPaint);
		tmpPaint.setStyle(Style.FILL);
		tmpPaint.setColor(Color.YELLOW);
		canvas.drawCircle(0, 0, 5, paint);
		canvas.drawLine(x1, y1, x2, y2, paint);
		super.onDraw(canvas);
	}
	
}
