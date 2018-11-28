package zds.dc.org.zhaodashen;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

/**
 * Created by Administrator on 2017/10/15/015.
 */

public class ImageUriToPath {
    ContentResolver contentResolver;
    public ImageUriToPath(ContentResolver contentResolver)
    {
        this.contentResolver = contentResolver;
    }
    public String handleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        return imagePath;
    }//android4.4以下图片处理方法
    @TargetApi(19)
    public String handleImageOnKitKat(Intent data, Context context){
        String imagePath = null;
        Uri uri = data.getData();
        System.out.println("uri:"+uri);
        if(DocumentsContract.isDocumentUri(context,uri)){
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        }else if ("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri,null);
        }else if ("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = uri.getPath();
        }
        return imagePath;
    }//Android4.4以上图片处理方法
    public String getImagePath(Uri uri, String selection){
        String path = null;
        Cursor cursor = contentResolver.query(uri,null,selection,null,null);
        if (cursor != null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }//从URI中获取路径

}
