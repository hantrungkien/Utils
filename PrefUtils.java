/**

 * Created by kienht on 7/16/16.
kienht */
public class PrefUtils {

    public static final String PREF_NAME = "ArendPrefs";

    private static PrefUtils _instance;

    SharedPreferences prefs;

    private PrefUtils(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static PrefUtils getInstance(Context context) {
        if (_instance == null) {
            _instance = new PrefUtils(context);
        }
        return _instance;
    }

    public void set(String key, String value) {
        prefs.edit().putString(key, value).apply();
    }

    public String get(String key, String defValue) {
        return prefs.getString(key, defValue);
    }

    public void set(String key, int value) {
        prefs.edit().putInt(key, value).apply();
    }

    public int get(String key, int defValue) {
        return prefs.getInt(key, defValue);
    }

    public void set(String key, boolean value) {
        prefs.edit().putBoolean(key, value).apply();
    }

    public boolean get(String key, boolean defValue) {
        return prefs.getBoolean(key, defValue);
    }

    public void set(String key, long value) {
        prefs.edit().putLong(key, value).apply();
    }

    public long get(String key, long defValue) {
        return prefs.getLong(key, defValue);
    }
    
    public void clearKey(String key){
        prefs.edit().remove(key).apply();
    }
    
    public void clearAllKey(){
        prefs.edit().clear().apply();
    }
}

