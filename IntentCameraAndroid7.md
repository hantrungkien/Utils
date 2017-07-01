folder: res/xml - filename: provider_paths.xml
````
<?xml version="1.0" encoding="utf-8"?>
<paths xmlns:android="http://schemas.android.com/apk/res/android">
    <external-path name="external_files" path="."/>
    <external-path name="external_sd" path="."/>
    <external-files-path name="external_app" path="."/>
    <files-path name="files" path="."/>
    <cache-path name="cache" path="."/>
</paths>

````

AndroidManifest.xml
````
<application>
<provider
            android:name="android.support.v4.content.FileProvider"
            android:authorities="${applicationId}.provider"
            android:grantUriPermissions="true"
            android:exported="false">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/provider_paths" />
        </provider>
</application> 
````

Intent Camera
````

Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePicture.resolveActivity(mMainActivity.getPackageManager()) != null) {
                    String photoFileName = String.valueOf(System.currentTimeMillis()).concat("_event_photo");
                    File photoFile = null;
                    try {
                        photoFile = CameraUtils.createImageFile(photoFileName);
                        //imgPathFromCamera = "file:".concat(photoFile.getAbsolutePath());
                        imgPath = photoFile.getAbsolutePath();
                    } catch (IOException ex) {
                        Log.e(TAG, "Error occurred while creating the Image File");
                        return;
                    }
                    Uri photoURI = null;
                    if (Build.VERSION.SDK_INT >= 24) {
                        photoURI = FileProvider.getUriForFile(mMainActivity,
                                BuildConfig.APPLICATION_ID + ".provider",
                                photoFile);
                    } else {
                        photoURI = Uri.fromFile(photoFile);
                    }
                    if (photoURI == null) {
                        return;
                    }
                    takePicture.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePicture, IMAGE_FROM_CAMERA);

                }
````
