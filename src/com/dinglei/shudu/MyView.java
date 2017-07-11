package com.dinglei.shudu;


import java.util.Calendar;

import android.R.color;
import android.R.integer;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.FontMetrics;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TimePicker;
import android.widget.Toast;

public class MyView extends View{
	Paint darkpaint;
	Paint lightpaint;
	Paint primaryPaint;
	Paint numberPaint;
	 
	  public Game game=new Game();   
	
	  private static  float width;
	  private static  float height;
	  private static  float micwidth;  
	  private static  float micheight;
	  
	  private static int selectX;
	  private static int selectY;
	  
	  Calendar startcalendar;
	  Calendar endcalendar;
	  
	  ListSelect listSelect=new ListSelect();
	  
	  
	public MyView(Context context) {
		super(context);
		
		// TODO Auto-generated constructor stub
		darkpaint = new Paint();  
        darkpaint.setColor(Color.DKGRAY);  
//        darkpaint.setStrokeJoin(Paint.Join.ROUND);      
//        darkpaint.setStrokeCap(Paint.Cap.ROUND);      
        darkpaint.setStrokeWidth(3); 
        
        lightpaint=new Paint();
        lightpaint.setColor(Color.LTGRAY);
        lightpaint.setStrokeWidth(1);
        
        primaryPaint=new Paint();
        primaryPaint.setColor(Color.GREEN);
        primaryPaint.setStrokeWidth(2);
        
        
     
	}
	protected void onDraw(Canvas canvas){  
		  super.onDraw(canvas);
		  
	      canvas.drawColor(Color.GRAY);  
	      //canvas.drawCircle(100, 100, 90, paint);  
	      width = getWidth();  //hdpi 480x800  
	      height = width;   
	      
	      final int space = getWidth()/9;   //长宽间隔  
	      int vertz = 0;  
	      int hortz = 0;
	      
	      
	      for(int i=0;i<10;i++){
	    	  if(i%3==0){
	    		 canvas.drawLine(0,  vertz, width, vertz, darkpaint);  
	 	         canvas.drawLine(hortz, 0, hortz, height, darkpaint);
	 	         
	    	  }
	    	  else{
	    		  canvas.drawLine(0,  vertz, width, vertz, lightpaint);  
	    		  canvas.drawLine(hortz, 0, hortz, height, lightpaint);  
	    		  
	    	  }
	         vertz+=space;  
	         hortz+=space;
	      } 
	      
	      
	      micwidth=width/9;
	      micheight=height/9;  
	      float gap=micwidth; 
	    //绘制数字  
	       numberPaint = new Paint();  
	        //设置文字的一系列属性  
	        numberPaint.setColor(Color.BLACK);  
	        numberPaint.setStyle(Paint.Style.STROKE);  
	        numberPaint.setTextSize(micheight*0.75f);  
	        numberPaint.setTextAlign(Paint.Align.CENTER);  
	        numberPaint.setTypeface(Typeface.SERIF);
	        FontMetrics fontMetrics = numberPaint.getFontMetrics();  
	        float x = micwidth/2;  
	        float y = micheight/2 - (fontMetrics.ascent+fontMetrics.descent)/2;  
        	
	        for(int i=0; i<9; i++)  
	            for(int j=0; j<9; j++)  
	            {  
	            	if(game.getfirstTitle(i, j)!=0){
	            		canvas.drawLine(i*gap, j*gap, i*gap+micwidth, j*gap+micheight, primaryPaint);
	            		canvas.drawLine(i*gap, j*gap+micheight/3, i*gap+2*micwidth/3, j*gap+micheight, primaryPaint);
	            		canvas.drawLine(i*gap, j*gap+2*micheight/3, i*gap+micwidth/3, j*gap+micheight, primaryPaint);
	            		canvas.drawLine(i*gap+micwidth/3, j*gap, i*gap+micwidth, j*gap+2*micheight/3, primaryPaint);
	            		canvas.drawLine(i*gap+2*micwidth/3, j*gap, i*gap+micwidth, j*gap+micheight/3, primaryPaint);
	            	}
	                canvas.drawText(game.getTitleString(i, j), i*micwidth + x, j*micheight+y, numberPaint);  
	            }  
	        Paint imagePaint =new Paint();
	        Bitmap imageBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.arrow254);
	       
	        int rawHeight=imageBitmap.getHeight();
	        int rawWidth=imageBitmap.getWidth();
	        int newHeight = 200; 
	        int newWidth = 200; 
	        // 计算缩放因子 
	        float heightScale = ((float) newHeight) / rawHeight; 
	        float widthScale = ((float) newWidth) / rawWidth; 
	        // 新建立矩阵 
	        //下一关图标
	        if(game.pos<game.sorts.length-1){
	        	Matrix matrix = new Matrix(); 
	        	matrix.postScale(heightScale, widthScale); 
	        	Bitmap newDownBitmap = Bitmap.createBitmap(imageBitmap, 0, 0, rawWidth,rawHeight, matrix, true); 
	        	Rect rect=new Rect(0, 0, newDownBitmap.getWidth(), newDownBitmap.getHeight());
	        	RectF rectf=new RectF(6f*micwidth, 10*micheight, 6f*micwidth+newDownBitmap.getWidth(), 10*micheight+newDownBitmap.getHeight());
	        	canvas.drawBitmap(newDownBitmap,rect,rectf,imagePaint);
	        	
	        }
	        //上一关图标
	        if(game.pos>0){
	        	Matrix matrix1 = new Matrix(); 
	        	matrix1.postScale(heightScale, widthScale); 
	        	matrix1.postRotate(-180); 
	        	Bitmap newUpBitmap = Bitmap.createBitmap(imageBitmap, 0, 0, rawWidth,rawHeight, matrix1, true);
	        	Rect rect2=new Rect(0, 0, newUpBitmap.getWidth(), newUpBitmap.getHeight());
	        	RectF rectf2=new RectF(1.5f*micwidth, 10*micheight, 1.5f*micwidth+newUpBitmap.getWidth(), 10*micheight+newUpBitmap.getHeight());
	        	canvas.drawBitmap(newUpBitmap,rect2,rectf2,imagePaint);
	        	
	        }
	        switch (game.pos) {
			case 0:
				canvas.drawText("第一关", 2.9f*micwidth+newWidth, 11.1f*micheight, numberPaint);
				break;
			case 1:
				canvas.drawText("第二关", 2.9f*micwidth+newWidth, 11.1f*micheight, numberPaint);
				break;
			case 2:
				canvas.drawText("第三关", 2.9f*micwidth+newWidth, 11.1f*micheight, numberPaint);
				break;
			default:
				break;
			}
	        Paint timePaint=new Paint();
	        timePaint.setColor(Color.BLACK);
	        timePaint.setTextSize(50);
	        timePaint.setStrokeWidth(1);
	        startcalendar=Calendar.getInstance();
	        
	        int startmonth=startcalendar.get(Calendar.MONTH);
	        int startday=startcalendar.get(Calendar.DAY_OF_MONTH);
	        int starthour=startcalendar.get(Calendar.HOUR_OF_DAY);
	        int startmin=startcalendar.get(Calendar.MINUTE);
	        int startsec=startcalendar.get(Calendar.SECOND);
	        String timeString="开始时间:"+String.valueOf(startmonth)+"月"+String.valueOf(startday)+"日"
	        			+String.valueOf(starthour)+"时"+String.valueOf(startmin)+"分"+String.valueOf(startsec)+"秒";
	        canvas.drawText(timeString,	1f, 12f*micheight, timePaint);
	  } 
	@Override  
    public boolean onTouchEvent(MotionEvent event)  
    {  
        // TODO Auto-generated method stub  
        if(event.getAction() != MotionEvent.ACTION_DOWN)  
        {  
            return super.onTouchEvent(event);  
        }  
//        width = getWidth();  //hdpi 480x800  
//	    height = width; 
//	    micwidth=width/9;
//	    micheight=height/9;  
	    selectX = (int)(event.getX()/micwidth);  
        selectY = (int)(event.getY()/micheight);  
        if(event.getX()<=width&&event.getY()<=height){
        	int[] used = game.getUsedTile(selectX, selectY);
        	StringBuffer sb = new StringBuffer();  
            for(int i = 0; i<used.length; i++)  
            {  
                sb.append(used[i]);  
            }  
            Log.i("TAG", sb.toString());
            if(game.getfirstTitle(selectX, selectY)==0){
            	Keydialog keyDialog = new Keydialog(this.getContext(), used, this);  
            	keyDialog.show();  
            }
        } else if(Game.pos<game.maxpos&&event.getX()>6f*micwidth&&event.getX()<6f*micwidth+200&&event.getY()>10*micheight&&event.getY()<10*micheight+200){
        	ListSelect.chronometer.setBase(SystemClock.elapsedRealtime());;
       
        	Game.pos=Game.pos+1;
        	
        	game=new Game(Game.pos);
        	invalidate();
        	
        	//Toast.makeText(getContext(),String.valueOf(game.pos),0).show();
        } else if(Game.pos>0&&event.getX()>1.5f*micwidth&&event.getX()<1.5f*micwidth+200&&event.getY()>10*micheight&&event.getY()<10*micheight+200){
        	Game.pos=Game.pos-1;
        	game=new Game(Game.pos);
        	invalidate();
        }
        return true;   	
       
    } 
	 //View类接受KeyDialog传递过来的数据，调用业务逻辑Game类，进行处理  
    public void setSelectTile(int tile)  
    {  
    	for(int i=0;i<9;i++){
        	for(int j=0;j<9;j++){
        		if(game.getTitle(i, j)==0){
        			break;
        		}else if(game.getTitle(i, j)!=0){
        			continue;
        		}
        		Toast.makeText(getContext(), "恭喜过关！", Toast.LENGTH_SHORT).show();
        		listSelect.chronometer.stop();
        		
        	}
        }
        Log.i("TAG", game.setTileIfValid(selectX, selectY, tile)+""+tile);  
        if(game.setTileIfValid(selectX,selectY,tile))  
        { 
            invalidate();//重新绘制View对象  
        }  
        if(!game.setTileIfValid(selectX,selectY,tile))  
        {  
        	Toast.makeText(getContext(), "输入数字有误", 0).show();
        }  
    } 
    
    
}
