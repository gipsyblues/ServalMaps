/*
 * Copyright (C) 2012 The Serval Project
 *
 * This file is part of the Serval Maps Software
 *
 * Serval Maps Software is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This source code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this source code; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package org.servalproject.maps;

import org.servalproject.maps.provider.MapItemsContract;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * activity to show information about a peer
 */
public class PeerInfoActivity extends Activity implements OnClickListener {
	
	/*
	 * private class level constants
	 */
	private final boolean V_LOG = true;
	private final String  TAG = "PeerInfoActivity";
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.peer_info);
        
        Intent mIntent = getIntent();
        
		// resolve the content uri
		ContentResolver mContentResolver = getApplicationContext().getContentResolver();
		
		Uri mContentUri = Uri.parse(MapItemsContract.Locations.CONTENT_URI.toString() + "/" + mIntent.getIntExtra("recordId", -1));
		
		// get the content
		Cursor mCursor = mContentResolver.query(mContentUri, null, null, null, null);
		
		// populate the activity
		if(mCursor.getCount() == 1) {
			
			mCursor.moveToFirst();
			
			TextView mView = (TextView) findViewById(R.id.peer_info_ui_txt_phone_number);
			mView.setText(mCursor.getString(mCursor.getColumnIndex(MapItemsContract.Locations.Table.PHONE_NUMBER)));
			
			mView = (TextView) findViewById(R.id.peer_info_ui_txt_latitude);
			mView.setText(mCursor.getString(mCursor.getColumnIndex(MapItemsContract.Locations.Table.LATITUDE)));
			
			mView = (TextView) findViewById(R.id.peer_info_ui_txt_longitude);
			mView.setText(mCursor.getString(mCursor.getColumnIndex(MapItemsContract.Locations.Table.LONGITUDE)));
			
			mView = (TextView) findViewById(R.id.peer_info_ui_txt_age);
			mView.setText(mCursor.getString(mCursor.getColumnIndex(MapItemsContract.Locations.Table.TIMESTAMP)));
			
		} else {
			// TODO show error
		}
		
		//debug code
		Log.v(TAG, "Record Count: " + mCursor.getCount());
		mCursor.close();
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}