/**
 * Created by kienht on 4/5/17.
 */

public class NetworkGenerator {

    public static final String URL_SERVER = "";

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    //api interface class
    ArendApi api;

    public ArendApi getApi() {
        return api;
    }

    static NetworkGenerator instance;

    public static NetworkGenerator getInstance() {
        clearInstance();
        if (instance == null) {
            instance = new NetworkGenerator();
        }
        return instance;
    }

    private NetworkGenerator() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient defaultHttpClient =
                new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                        .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Interceptor.Chain chain) throws IOException {
                                String credentials = Credentials.basic(USERNAME, PASSWORD);
                                Request request = chain.request()
                                        .newBuilder()
                                        .addHeader("Content-Type", "application/json")
                                        .addHeader("Authorization", credentials)
                                        .addHeader("X-Token", Constant.TOKEN)
                                        .build();

                                return chain.proceed(request);
                            }
                        })
                        .addInterceptor(logging)
                        .build();

        Retrofit _retrofit = new Retrofit.Builder().baseUrl(URL_SERVER)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(defaultHttpClient)
                .build();

        api = _retrofit.create(ArendApi.class);
    }

    public static void clearInstance() {
        instance = null;
    }

}
