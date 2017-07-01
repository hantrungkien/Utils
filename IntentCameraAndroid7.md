````
folder: res/xml - filename: provider_paths.xml
<?xml version="1.0" encoding="utf-8"?>
<paths xmlns:android="http://schemas.android.com/apk/res/android">
    <external-path name="external_files" path="."/>
    <external-path name="external_sd" path="."/>
    <external-files-path name="external_app" path="."/>
    <files-path name="files" path="."/>
    <cache-path name="cache" path="."/>
</paths>

````

````
AndroidManifest.xml
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
