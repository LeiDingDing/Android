package com.dinglei.shudu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class BaseActivity extends Activity {
	private ImageView menuImageView;
	private Button startButton;
	private Button lawButton;
	private Class[]clazzs=new Class[]{MainActivity.class};
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuImageView=(ImageView) findViewById(R.id.imageview);
        menuImageView.setBackgroundResource(R.drawable.icon);
        startButton=(Button) findViewById(R.id.btn_1);
        
        startButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						clazzs[0]);
				startActivity(intent);
				 
			}
		});
	}   
}
