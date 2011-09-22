package name.chakimar.fileobservertest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;

public class Util {
	private static final String TEST_FILE = "Book1.xls";

	public static File getTestFile() {
		return new File("/data/data/name.chakimar.fileobservertest/files/" + TEST_FILE);
	}
	
	public static boolean isTestFileAlreadyExists() {
		return getTestFile().exists();
	}
	
	public static void populateAssetFile(Context context) throws IOException {
		if (isTestFileAlreadyExists()) {
			return;
		}
		
		InputStream is = null;
		FileOutputStream fos = null;
		AssetManager as = context.getResources().getAssets();
		try {
			is = as.open(TEST_FILE);
			fos = context.openFileOutput(TEST_FILE, Context.MODE_WORLD_READABLE);
			
			byte[] buffer = new byte[1024];
			int ch = is.read(buffer);
			while (ch != -1) {
				fos.write(buffer);
				ch = is.read(buffer);
			}
		} finally {
			if (is != null) {
				is.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
	}
	
	public static void delete() {
		getTestFile().delete();
	}
}
