package ru.webanimal.academy_lessons.data.common.network;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.webanimal.academy_lessons.data.features.digests.network.IDigestsEndPoint;

/**
 * See:
 * http://developer.nytimes.com/top_stories_v2.json#/Documentation/GET/%7Bsection%7D.%7Bformat%7D
 *
 * See also:
 * http://developer.nytimes.com/top_stories_v2.json#/README
 *
 * BASE_URL builder:
 * http://api.nytimes.com/svc/topstories/v2/{section}.{response-format}?api-key={your-api-key}
 */
public class NetworkManager {

    //==============================================================================================
    // Static
    //==============================================================================================

    private static final String PARAM_API_KEY = "api-key";
    private static final String API_KEY = "104dda80956345a3a551415dde722147";
    private static final String BASE_URL = "http://api.nytimes.com/svc/topstories/v2/";
    private static final String[] CATEGORIES = new String[] {
            "home",
            "opinion",
            "world",
            "national",
            "politics",
            "upshot",
            "nyregion",
            "business",
            "technology",
            "science",
            "health",
            "sports",
            "arts",
            "books",
            "movies",
            "theater",
            "sundayreview",
            "fashion",
            "tmagazine",
            "food",
            "travel",
            "magazine",
            "realestate",
            "automobiles",
            "obituaries",
            "insider"
    };

    private static final int TIMEOUT_IN_SECOND_CONNECTION = 2; // In second
    private static final int TIMEOUT_IN_SECOND_READ = 2; // In second
    private static final int TIMEOUT_IN_SECOND_WRITE = 2; // In second

    private static NetworkManager instance;
    public static NetworkManager get() {
        if (instance == null) {
            instance = new NetworkManager();
        }

        return instance;
    }


    //==============================================================================================
    // Fields
    //==============================================================================================

    private IDigestsEndPoint digestsApi;


    //==============================================================================================
    // Public methods
    //==============================================================================================

    // TODO: to use
    public IDigestsEndPoint getDigestsApi() {
        return digestsApi;
    }

    // TODO (Sergio): remove from here this and the "categories" array
    public String getCategory(int position) {
        int length = CATEGORIES.length;
        int pos = position;
        if (pos < 0) {
            pos = 0;
        }
        if (pos >= length) {
            pos = length - 1;
        }

        return CATEGORIES[pos];
    }


    //==============================================================================================
    // Constructor
    //==============================================================================================

    private NetworkManager() {
        final OkHttpClient httpClient = buildHttpClient();
        final Retrofit retrofitClient = buildRetrofitClient(httpClient);

        //init endpoints here. It's can be more then one endpoint
        digestsApi = retrofitClient.create(IDigestsEndPoint.class);
        /*
        // Add a disposable collection
        digestsApi.digests("")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    // handle the response
                }, e -> {
                    Log.e("tag", "digestsApi error");
                    e.printStackTrace();
                    // handle errors
                });
        * */
    }



    //==============================================================================================
    // Private methods
    //==============================================================================================

    @NonNull
    private OkHttpClient buildHttpClient() {
        final HttpLoggingInterceptor networkLoggingInterceptor = new HttpLoggingInterceptor();
        networkLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(ApiKeyInterceptor.create(PARAM_API_KEY, API_KEY))
                .addInterceptor(networkLoggingInterceptor)
                .connectTimeout(TIMEOUT_IN_SECOND_CONNECTION, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_IN_SECOND_READ, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_IN_SECOND_WRITE, TimeUnit.SECONDS)
                .build();
    }

    @NonNull
    private Retrofit buildRetrofitClient(OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
