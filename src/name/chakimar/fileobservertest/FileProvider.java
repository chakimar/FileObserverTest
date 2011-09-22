package name.chakimar.fileobservertest;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;

public class FileProvider extends ContentProvider {
	
	public static final String AUTHORITY = "name.chakimar.fileobservertest";

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		return 0;
	}

	@Override
	public String getType(Uri arg0) {
		return null;
	}

	@Override
	public Uri insert(Uri arg0, ContentValues arg1) {
		return null;
	}

	@Override
	public boolean onCreate() {
		return true;
	}

	@Override
	public Cursor query(Uri arg0, String[] arg1, String arg2, String[] arg3,
			String arg4) {
		return null;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		return 0;
	}

	@Override
	public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
		Log.d("FileProvider", "openFile");
		File f = new File(URI.create("file:///data/data/name.chakimar.fileobservertest/files/Book1.xls"));
		ParcelFileDescriptor parcel = ParcelFileDescriptor.open(f, ParcelFileDescriptor.MODE_READ_ONLY);
		return parcel;
	}

}
