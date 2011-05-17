/*
 * This file is part of the Serval Mapping Services app.
 *
 *  Serval Mapping Services app is free software: you can redistribute it 
 *  and/or modify it under the terms of the GNU General Public License 
 *  as published by the Free Software Foundation, either version 3 of 
 *  the License, or (at your option) any later version.
 *
 *  Serval Mapping Services app is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Serval Mapping Services app.  
 *  If not, see <http://www.gnu.org/licenses/>.
 */
package org.servalproject.mappingservices.mapsforge;

import org.mapsforge.android.maps.ArrayItemizedOverlay;

import org.servalproject.mappingservices.ViewIncidentActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * Extend the ArrayItemizedOverlay class from the mapsforge package to include
 * functionality required by the app
 * 
 * @author corey.wallis@servalproject.org
 *
 */
public class OverlayList extends ArrayItemizedOverlay {
	
	/*
	 * private class level constants
	 */
	
	private final boolean V_LOG = true;
	private final String TAG = "ServalMaps-OL";
	
	/*
	 * private class level variables
	 */
	private Context parentContext = null;

	public OverlayList(Drawable defaultMarker, Context context) {
		super(defaultMarker, context);
		// TODO Auto-generated constructor stub
		
		parentContext = context;
	}
	
	@Override
	public boolean onTap(int index) {
		
		OverlayItem item = (OverlayItem)this.createItem(index);
		
		//TODO do more than just show the view
		
		Intent intent = new Intent(parentContext, ViewIncidentActivity.class);
		parentContext.startActivity(intent);
		
		if(item != null) {
			//TODO do something more intelligent with the item
			Log.v(TAG, item.getRecordId());
		}
		
		return true;
		
//		synchronized (this.overlayItems) {
//			OverlayItem item = this.overlayItems.get(index);
//			if (item != null) {
//				Builder builder = new AlertDialog.Builder(this.context);
//				builder.setIcon(android.R.drawable.ic_menu_info_details);
//				builder.setTitle(item.getTitle());
//				builder.setMessage(item.getSnippet());
//				builder.setPositiveButton(this.internalMapView.getText(TextField.OKAY), null);
//				builder.show();
//			}
//			return true;
//		}
		
	}
	
	/*
	 * make sure we don't hold onto a reference to the context
	 * (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	
	@Override
	protected void finalize() throws Throwable{
		parentContext = null;
		super.finalize();
	}

}
