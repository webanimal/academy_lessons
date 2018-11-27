package ru.webanimal.academy_lessons.data.common.network;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.webanimal.academy_lessons.data.features.digests.network.IDigestsRestApi;
import ru.webanimal.academy_lessons.data.features.digests.network.IDigestsResults;

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
public class NetworkManager implements IDigestsResults {

    //==============================================================================================
    // Static
    //==============================================================================================

    private static final String PARAM_API_KEY = "api-key";
    private static final String API_KEY = "104dda80956345a3a551415dde722147";
    private static final String BASE_URL = "http://api.nytimes.com/svc/topstories/v2/";

    private static final int TIMEOUT_IN_SECOND_CONNECTION = 2; // In second
    private static final int TIMEOUT_IN_SECOND_READ = 2; // In second
    private static final int TIMEOUT_IN_SECOND_WRITE = 2; // In second

    private static NetworkManager instance;
    public static synchronized NetworkManager get() {
        if (instance == null) {
            instance = new NetworkManager();
        }

        return instance;
    }


    //==============================================================================================
    // Fields
    //==============================================================================================

    private IDigestsRestApi digestsRestApi;


    //==============================================================================================
    // Constructor
    //==============================================================================================

    private NetworkManager() {
        final OkHttpClient httpClient = buildHttpClient();
        final Retrofit retrofit = buildRetrofitClient(httpClient);

        //init endpoints here. It's can be more then one endpoint
        digestsRestApi = retrofit.create(IDigestsRestApi.class);
    }


    //==============================================================================================
    // Rest APIs callbacks
    //==============================================================================================

    /**
     * An instance of the IDigestsRestApi which may produce http GET calls.
     *
     * @return a source which produces Observables with a List<DigestDTO>.
     * See also {@link IDigestsRestApi#call(String)}
     */
    @NonNull
    @Override
    public IDigestsRestApi digestsRestApi() {
        return digestsRestApi;
    }


    //==============================================================================================
    // Private methods
    //==============================================================================================

    @NonNull
    private OkHttpClient buildHttpClient() {
        final HttpLoggingInterceptor networkLoggingInterceptor = new HttpLoggingInterceptor();
//        networkLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        networkLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(ApiKeyInterceptor.create(PARAM_API_KEY, API_KEY))
                .addInterceptor(networkLoggingInterceptor)
                .connectTimeout(TIMEOUT_IN_SECOND_CONNECTION, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_IN_SECOND_WRITE, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_IN_SECOND_READ, TimeUnit.SECONDS)
                .build();
    }

    @NonNull
    private Retrofit buildRetrofitClient(OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}