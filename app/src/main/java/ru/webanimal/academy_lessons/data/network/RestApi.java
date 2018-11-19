package ru.webanimal.academy_lessons.data.network;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
public final class RestApi {

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

    private static RestApi instance;

    public static synchronized RestApi getInstance() {
        if (instance == null) instance = new RestApi();

        return instance;
    }


    //==============================================================================================
    // Fields
    //==============================================================================================

    private IDigestsEndPoint digestsApi;


    //==============================================================================================
    // Constructor
    //==============================================================================================

    private RestApi() {
        final OkHttpClient httpClient = buildHttpClient();
        final Retrofit retrofitClient = buildRetrofitClient(httpClient);

        //init endpoints here. It's can be more then one endpoint
        // FIXME (Sergio): add a "digests" endpoint creation here from retrofit.
        // Where to get a category list?
        digestsApi = retrofitClient.create(IDigestsEndPoint.class);
    }


    //==============================================================================================
    // Getters and Setters
    //==============================================================================================

    public IDigestsEndPoint getDigestsApi() {
        return digestsApi;
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
                .build();
    }
}
