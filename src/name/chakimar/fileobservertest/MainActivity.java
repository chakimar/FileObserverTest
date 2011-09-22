package name.chakimar.fileobservertest;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileObserver;
import android.util.Log;

public class MainActivity extends Activity {
	private static final String TYPE = "application/vnd.ms-excel";
	private static final String TAG = "MainActivity";
	private static final FileObserver observer = new FileObserver("/data/data/name.chakimar.fileobservertest/files/") {
		
		@Override
		public void onEvent(int event, String path) {
			if (event == FileObserver.OPEN) {
				Log.d(TAG,"OPEN");
			} else if (event == FileObserver.CLOSE_NOWRITE) {
				Log.d(TAG,"CLOSE");
				Util.delete();
			} else if (event == FileObserver.CREATE) {
				Log.d(TAG,"CREATE");
			} else if (event == FileObserver.DELETE) {
				Log.d(TAG, "DELETE");
			}
			
		}
	};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		observer.startWatching();
        try {
			Util.populateAssetFile(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.parse("content://" + FileProvider.AUTHORITY + "/Book1.xls"), TYPE);
		intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		
		startActivity(intent);
    }
    
    @Override
	protected void onDestroy() {
		super.onDestroy();
		observer.stopWatching();
	}
}