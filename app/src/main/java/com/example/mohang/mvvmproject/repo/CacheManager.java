package com.example.mohang.mvvmproject.repo;


import android.util.LruCache;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class CacheManager {


    // private List<Observable<T>>  cacheObserverList;
    private static LruCache<String, Observable> cacheObserverList = new LruCache<>(10);





    public static <T> Observable<T> chacheObserver(final String key, Observable<T> observable, boolean cache) {

        if(key==null){
            return observable;
        }

        if (!cache) {
            //cacheObserverList.put(key,null);
            return observable;
        }

        Observable<T> observableData = cacheObserverList.get(key);

        if (observableData == null) {
            cacheObserverList.put(key, observable.doOnError(new Consumer<Throwable>() {
                @Override
                public void accept(@NonNull Throwable throwable) throws Exception {
                    cacheObserverList.remove(key);
                }
            }).cache());

            return cacheObserverList.get(key);
        }

        return observableData;
    }

    public static void clearCache(String tag) {
        if (cacheObserverList != null) {
            cacheObserverList.remove(tag);
        }
    }

    public static void clearCache() {
        if (cacheObserverList != null) {
            cacheObserverList.evictAll();
        }
    }


}
