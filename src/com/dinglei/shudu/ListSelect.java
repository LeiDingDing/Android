package com.dinglei.shudu;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;


public class ListSelect extends Activity {

	public String[]levels={"第一关","第二关","第三关"};
    public Game game1=new Game();  
	private ListView listView;
	private ArrayAdapter<String> adapter;

	private SoundPool sp;//声明一个SoundPool 
	private int music;//定义一个整型用load（）；来设置suondID 
	
	public static Chronometer chronometer; 
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        getActionBar().setTitle("关卡");
        //getActionBar().setLogo(R.drawable.backdark);
        getActionBar().setDisplayHomeAsUpEnabled(true); 
        getActionBar().setDisplayShowHomeEnabled(false);
        media2();
//        sp=new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
//        music=sp.load(this,R.raw.keydown, MainActivity.priority);
        
        listView=(ListView) findViewById(R.id.list);
        adapter=new ArrayAdapter<String>(getApplicationContext(),
				R.layout.item,levels);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				sp.play(music, MainActivity.left, MainActivity.right, 0, 0, 1);
				//sp.play(music, 1, 1, 0, 0, 1); 
				game1.pos=position;
		
//				switch (position) {
//				case 0:
//					getActionBar().setTitle("第一关");
//					break;
//				case 1:
//					getActionBar().setTitle("第二关");
//					break;
//				case 2:
//					getActionBar().setTitle("第三关");
//					break;
//				default:
//					break;
//				}
					
				
				setContentView(new MyView(ListSelect.this));
				chronometer=(Chronometer) findViewById(R.id.chronmeter);
		        chronometer.start();
			}
			
		});
        
    }
    public void media2(){
    	sp=new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        music=sp.load(this,R.raw.keydown, 1);
        //sp.play(music, MainActivity.left, MainActivity.right, 0, 0, 1);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//        if (id == R.id.action_search) {
//            return true;
//        }
        switch (id) {
		case android.R.id.home:
			finish();
            return true; 
			

		default:
			break;
		}
        return super.onOptionsItemSelected(item);
    }


   

}
