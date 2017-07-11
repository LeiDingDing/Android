package com.dinglei.shudu;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

public class AboutUs extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		getActionBar().setTitle("¹ØÓÚ");
		getActionBar().setDisplayHomeAsUpEnabled(true); 
		getActionBar().setDisplayShowHomeEnabled(false);
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
