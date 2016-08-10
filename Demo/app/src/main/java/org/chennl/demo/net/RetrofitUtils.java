package org.chennl.demo.net;

import android.text.TextUtils;

import org.chennl.demo.BuildConfig;
import org.chennl.demo.util.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit封装工具类
 * Created by chennl on 16-8-10.
 */
public class RetrofitUtils {

    private static RetrofitUtils mInstance;

    private Retrofit mRetrofit;

    private OkHttpClient mOkHttpClient;

    private String mBaseUrl;

    public static RetrofitUtils getInstance() {
        synchronized (RetrofitUtils.class) {
            if (mInstance == null) {
                mInstance = new RetrofitUtils();
            }
        }
        return mInstance;
    }

    /**
     * 构造函数
     * 初始化数据
     */
    public RetrofitUtils() {
        mBaseUrl = BuildConfig.API_HOST;
        if (mOkHttpClient == null) {//参数、消息头拦截
            //添加token等参数
            Map<String, String> params = new HashMap<>();
//            params.put("token", "abcdefghijklmnopqrstuvwxyz");
//            params.put("sign", "abcdefghijklmnopqrstuvwxyz");
            OkHttpInterceptor interceptor = new OkHttpInterceptor(params);

            //消息头拦截
            Map<String, String> headers = new HashMap<>();
//            headers.put("Cookie", "uid=1000");
            OkHttpHeaderInterceptor headerInterceptor = new OkHttpHeaderInterceptor(headers);

            mOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(headerInterceptor)
                    .build();

        }
        if (mRetrofit == null) {
            //retrofit 对象是可以单例的，因为 retrofit.create 每次创建的服务都是不一样的，可以单独取消请求
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(mBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(mOkHttpClient)
                    .build();
        }
    }

    /**
     * 创建指定Service
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T createService(Class<T> clazz) {
        T service = mRetrofit.create(clazz);
        return service;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public void setRetrofit(Retrofit mRetrofit) {
        synchronized (this) {
            this.mRetrofit = mRetrofit;
        }
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public void setOkHttpClient(OkHttpClient mOkHttpClient) {
        this.mOkHttpClient = mOkHttpClient;
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }

    public void setBaseUrl(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }


    /**
     * 普通参数拦截器
     * 请求内需添加token，sign等参数时使用
     */
    static class OkHttpInterceptor implements Interceptor {

        private Map<String, String> mParams;

        public OkHttpInterceptor(Map<String, String> params) {
            mParams = params;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            HttpUrl httpUrl = request.url();
            HttpUrl.Builder builder = httpUrl.newBuilder()
                    .scheme(httpUrl.scheme())
                    .host(httpUrl.host());

            //添加附加参数
            for (Map.Entry<String, String> entry : mParams.entrySet()) {
                if (TextUtils.isEmpty(entry.getKey())
                        && TextUtils.isEmpty(entry.getValue())) {
                    builder.addQueryParameter(entry.getKey(), entry.getValue());
                    Logger.e(Logger.TAG, "参数 ===> key: " + entry.getKey()
                            + ", value: " + entry.getValue());
                }
            }

            //构建新请求
            Request newReqeust = request.newBuilder()
                    .method(request.method(), request.body())
                    .url(builder.build())
                    .build();
            return chain.proceed(newReqeust);
        }
    }

    /**
     * 消息头拦截器，用于处理头消息的封装
     * 所有拦截都是在http请求发送前处理
     */
    static class OkHttpHeaderInterceptor implements Interceptor {

        private Map<String, String> mHeaders;

        public OkHttpHeaderInterceptor(Map<String, String> headers) {
            mHeaders = headers;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (request != null) {
                Request.Builder builder = request.newBuilder();
                for (Map.Entry<String, String> entry : mHeaders.entrySet()) {
                    builder.addHeader(entry.getKey(), entry.getValue());
                }
                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
            return chain.proceed(request);
        }

        /**
         * 检测头部是否包含某个key
         *
         * @param key
         * @return
         */
        public boolean containsKey(String key) {
            return mHeaders.containsKey(key);
        }
    }
}
