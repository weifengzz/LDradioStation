package cn.edu.lyu.ldradiostation.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

public class SDCardHelper {
	private static String TAG = "SDCardHelper";

	/*
	 * 判断sdcard是否挂载
	 */
	public static boolean isSDCardMounted() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

	/*
	 * 获取sdcard绝对物理路径
	 */
	public static String getSDCardPath() {
		if (isSDCardMounted()) {
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		} else {
			return null;
		}
	}

	/*
	 * 获取sdcard的全部的空间大小。返回MB字节
	 */
	public static long getSDCardSize() {
		if (isSDCardMounted()) {
			StatFs fs = new StatFs(getSDCardPath());
			long size = fs.getBlockSize();
			long count = fs.getBlockCount();
			return size * count / 1024 / 1024;
		}
		return 0;
	}

	/*
	 * 获取sdcard的剩余的可用空间的大小。返回MB字节
	 */
	public static long getSDCardFreeSize() {
		if (isSDCardMounted()) {
			StatFs fs = new StatFs(getSDCardPath());
			long size = fs.getBlockSize();
			long count = fs.getAvailableBlocks();
			return size * count / 1024 / 1024;
		}
		return 0;
	}

	public File getAlbumStorageDir(Context context, String albumName) {
		File file = new File(
				context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
				albumName);
		if (!file.mkdirs()) {
			Log.e(TAG, "目录没有创建");
		}
		return file;
	}

	/*
	 * 将文件（byte[]）保存进sdcard指定的路径下
	 */
	public static boolean saveFileToSDCard(byte[] data, String dir,
			String filename) {
		BufferedOutputStream bos = null;
		if (isSDCardMounted()) {
			Log.i(TAG, "==isSDCardMounted==TRUE");
			File file = new File(getSDCardPath() + File.separator + dir);
			Log.i(TAG, "==file:" + file.toString() + file.exists());
			if (!file.exists()) {
				boolean flags = file.mkdirs();
				Log.i(TAG, "==创建文件夹:" + flags);
			}
			try {
				bos = new BufferedOutputStream(new FileOutputStream(new File(
						file, filename)));
				bos.write(data, 0, data.length);
				bos.flush();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/*
	 * 已知文件的路径，从sdcard中获取到该文件，返回byte[]
	 */
	public static byte[] loadFileFromSDCard(String filepath) {
		BufferedInputStream bis = null;
		ByteArrayOutputStream baos = null;
		if (isSDCardMounted()) {
			File file = new File(filepath);
			if (file.exists()) {
				try {
					baos = new ByteArrayOutputStream();
					bis = new BufferedInputStream(new FileInputStream(file));
					byte[] buffer = new byte[1024 * 8];
					int c = 0;
					while ((c = bis.read(buffer)) != -1) {
						baos.write(buffer, 0, c);
						baos.flush();
					}
					return baos.toByteArray();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (bis != null) {
							bis.close();
							baos.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	/**
	 * 从读取取文件数据件
	 * */
	public static byte[] loadFileFromSDCard(File file) {
		// 从内部存储文件中读取数据 data/data/应用程序包名/files
		FileInputStream inputStream = null;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			inputStream = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int temp = 0;
			while ((temp = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, temp);
				outputStream.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e2) {
			}
		}
		return outputStream.toByteArray();
	}
}
