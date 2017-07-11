package com.dinglei.shudu;

import java.io.IOException;
import java.security.KeyStore.LoadStoreParameter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Settings extends Activity {
	private CheckBox musicBox;
	private CheckBox mediaCheckBoxBox;
	public  static MediaPlayer mMediaPlayer;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.settings);
	getActionBar().setTitle("设置");
	getActionBar().setDisplayHomeAsUpEnabled(true); 
	getActionBar().setDisplayShowHomeEnabled(false);
    loadPref();
	
	
//	musicBox=(CheckBox) findViewById(R.id.check_1);
//	musicBox.setChecked(mMusic);
//	musicBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//		
//		@Override
//		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//			// TODO Auto-generated method stub
//			if(isChecked){
//				playLocalFile();
//			}else{
//				mMediaPlayer.pause();
//				
//			}
//			
//		}
//	});
//	mediaCheckBoxBox=(CheckBox) findViewById(R.id.check_2);
//	mediaCheckBoxBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//		
//		@Override
//		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//			// TODO Auto-generated method stub
//			if(isChecked){
//				MainActivity.priority=1;
//			}else {
//				MainActivity.priority=0;
//			}
//		}
//	});
   }
	private void playLocalFile() {
		mMediaPlayer = MediaPlayer.create(this, R.raw.yinghua);
		//播放工程res目录下的raw目录中的音乐文件fade

		try {
			mMediaPlayer.prepare();
		} catch (IllegalStateException e) {

		} catch (IOException e) {

		}

		mMediaPlayer.start();
		//headsetplay.setEnabled(false);

		mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
		
			public void onCompletion(MediaPlayer mp) {
				//播完了接着播或者关闭mMediaPlayer
				//mMediaPlayer.start();
			}
			
		});
	}
	protected void loadPref() {
		int mode=Activity.MODE_PRIVATE;
		SharedPreferences mySharedPreferences=getSharedPreferences(MainActivity.MYPREFS, mode);
		boolean mMusic=mySharedPreferences.getBoolean("Music", false);
		boolean mMedia=mySharedPreferences.getBoolean("Media", false);
		final SharedPreferences.Editor editor=mySharedPreferences.edit();
		musicBox=(CheckBox) findViewById(R.id.check_1);
		musicBox.setChecked(mMusic);
		musicBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				
				if(isChecked){
					editor.putBoolean("Music", true);
					editor.commit();
					playLocalFile();
				}else{
					editor.putBoolean("Music", false);
					editor.commit();
					mMediaPlayer.pause();
					
				}
				
			}
		});
		mediaCheckBoxBox=(CheckBox) findViewById(R.id.check_2);
		mediaCheckBoxBox.setChecked(mMedia);
		mediaCheckBoxBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					editor.putBoolean("Media", true);
					editor.commit();
					MainActivity.left=1;
					MainActivity.right=1;
					
				}else {
					editor.putBoolean("Media", false);
					editor.commit();
					MainActivity.left=0;
					MainActivity.right=0;
					
				}
			}
		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK){
			
		}
		return super.onKeyDown(keyCode, event);
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
