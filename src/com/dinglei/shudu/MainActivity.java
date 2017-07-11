package com.dinglei.shudu;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;



public class MainActivity extends Activity implements OnItemClickListener{
    private String mTitle;
	private ImageView menuImageView;
	private Button startButton;
	private Button lawButton;
	private  Class[]clazzs=new Class[]{ListSelect.class,Settings.class,AboutUs.class};
	private ListView listDrawer;
	private String[]listcontent={"关卡选择","设置","关于"};
	private ArrayAdapter<String>mlistAdapter;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
    
	private SoundPool sp;//声明一个SoundPool 
	private int music;//定义一个整型用load（）；来设置suondID   
	public static int left=1;
	public static int right=1;
	
	public static final String MYPREFS="mySharedPreferences";
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        media();
        setContentView(R.layout.copyactivity_main);
        savePref();
        menuImageView=(ImageView) findViewById(R.id.imageview);
        menuImageView.setBackgroundResource(R.drawable.icon);
        mTitle=(String) getTitle();
        startButton=(Button) findViewById(R.id.btn_1);
        lawButton=(Button) findViewById(R.id.btn_2);
        
        	startButton.setOnClickListener(new OnClickListener() {
        		
        		@Override
        		public void onClick(View v) {
        			// TODO Auto-generated method stub
        			sp.play(music, left, right, 0, 0, 1);
        			//media();
        			Intent intent = new Intent(getApplicationContext(),
        					ListSelect.class);
        			startActivity(intent);
        			//setContentView(new MyView(MainActivity.this));
        			
        		}
        	});
        	lawButton.setOnClickListener(new OnClickListener() {
        		
        		@Override
        		public void onClick(View v) {
        			sp.play(music, left, right, 0, 0, 1);
        			//media();
        			// TODO Auto-generated method stub
        			Intent intent = new Intent(getApplicationContext(),
        					RuleInfo.class);
        			startActivity(intent);
        			//setContentView(new MyView(MainActivity.this));
        			
        		}
        	});
        	listDrawer=(ListView) findViewById(R.id.left_drawer);
        	
        	mlistAdapter=new ArrayAdapter<String>(this, R.layout.item, listcontent);
        	
		    listDrawer.setAdapter(mlistAdapter);
		    listDrawer.setOnItemClickListener(this);
		    
		    mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
		    mDrawerToggle=new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer,
		    		R.string.drawer_open, R.string.drawer_close){
		    	@Override
		    	public void onDrawerOpened(View drawerView) {
		    		// TODO Auto-generated method stub
		    		super.onDrawerOpened(drawerView);
		    		getActionBar().setTitle("请选择");
		    		invalidateOptionsMenu();
		    	}
		    	@Override
		    	public void onDrawerClosed(View drawerView) {
		   			// TODO Auto-generated method stub
	 				super.onDrawerClosed(drawerView);
	 				getActionBar().setTitle(mTitle);
		    		invalidateOptionsMenu();
				}
		    };
		    mDrawerLayout.setDrawerListener(mDrawerToggle);
		    getActionBar().setLogo(R.drawable.ic_drawer);
		    getActionBar().setDisplayShowHomeEnabled(true);
		    getActionBar().setHomeButtonEnabled(true);
	} 
	//设置按键音效
	public void media(){
		sp=new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        music=sp.load(this,R.raw.keydown,1);
        //sp.play(music, 1, 1, 0, 0, 1);
	}
	
	//保存checkbox的状态
	protected void savePref() {
		int mode=Activity.MODE_PRIVATE;
		SharedPreferences mySharedPreferences=getSharedPreferences(MYPREFS, mode);
		SharedPreferences.Editor editor=mySharedPreferences.edit();
		boolean mMusic=mySharedPreferences.getBoolean("Music", false);
		boolean mMedia=mySharedPreferences.getBoolean("Media", true);
//		editor.putBoolean("Music", false);
//		editor.putBoolean("Media", true);
//		editor.commit();
		if(mMedia){
			left=1;
			right=1;
			media();
		}else {
			left=0;
			right=0;
			media();
		}
	}
	@Override
   	public boolean onPrepareOptionsMenu(Menu menu) {
			// TODO Auto-generated method stub
			boolean isDrawerOpen=mDrawerLayout.isDrawerOpen(listDrawer);
			menu.findItem(R.id.action_search).setVisible(!isDrawerOpen);
			return super.onPrepareOptionsMenu(menu);
	}
   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	if(mDrawerToggle.onOptionsItemSelected(item)){
    		return true;
    	}
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onPostCreate(savedInstanceState);
    	mDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
    	// TODO Auto-generated method stub
    	super.onConfigurationChanged(newConfig);
    	mDrawerToggle.onConfigurationChanged(newConfig);
    }
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		sp.play(music, left, right, 0, 0, 1);
		//media();
		Intent intent=new Intent(getApplicationContext(),clazzs[position]);
		startActivity(intent);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK ){
			AlertDialog isExit=new AlertDialog.Builder(this).create();
			isExit.setTitle("提示");
			isExit.setMessage("确定要退出吗?");
			isExit.setButton("确定", listener);
			isExit.setButton2("取消", listener);
			isExit.show();
		}
		return false;
	}
	/**监听对话框里面的button点击事件*/  
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()  
    {  
        public void onClick(DialogInterface dialog, int which)  
        {  
            switch (which)  
            {  
            case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序  
            	Settings.mMediaPlayer.pause();
                finish();  
                break;  
            case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框  
                break;  
            default:  
                break;  
            }  
        }  
    };    
 
}

