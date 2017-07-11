package com.dinglei.shudu;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class Keydialog extends Dialog {
	private int[]array;
	private MyView myView;
	
	private View[]keys=new View[10];
	public Keydialog(Context context,int []array,MyView myView){
		super(context);
		this.array=array;
		this.myView=myView;
	}
	
	@Override  
    protected void onCreate(Bundle savedInstanceState)  
    {  
        // TODO Auto-generated method stub  
        super.onCreate(savedInstanceState);  
        setTitle("请填入数字");  
        setContentView(R.layout.dialog);  
        keys[0]=findViewById(R.id.keypad_1);
        keys[1]=findViewById(R.id.keypad_2);
        keys[2]=findViewById(R.id.keypad_3);
        keys[3]=findViewById(R.id.keypad_4);
        keys[4]=findViewById(R.id.keypad_5);
        keys[5]=findViewById(R.id.keypad_6);
        keys[6]=findViewById(R.id.keypad_7);
        keys[7]=findViewById(R.id.keypad_8);
        keys[8]=findViewById(R.id.keypad_9);
        keys[9]=findViewById(R.id.keypad_10);
        for(int i=0; i<this.array.length; i++)  
        {  
            if(array[i] != 0)  
            {  
                keys[array[i]-1].setVisibility(View.VISIBLE);
                
            }  
        }  
        keys[9].setVisibility(View.VISIBLE);
        setListener();  
    }  
	//为按钮设置监听器  
    public void setListener()  
    {  
        for(int i = 0; i<keys.length; i++)  
        {  
            final int t=(i<9)?(i+1):0; 
            
            keys[i].setOnClickListener(new View.OnClickListener()  
            {  
                  
                @Override  
                public void onClick(View v)  
                {  
                    // TODO Auto-generated method stub  
                    returnResult(t);  
                }  
            });  
        }  
    }  
      
    //对话框将选择的数据传递给View对象，让其处理业务逻辑  
    public void returnResult(int tile)  
    {  
        myView.setSelectTile(tile);  
        dismiss();  
    }  
}
