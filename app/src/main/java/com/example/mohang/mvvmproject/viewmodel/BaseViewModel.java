package com.example.mohang.mvvmproject.viewmodel;

import android.support.annotation.CallSuper;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public  class BaseViewModel implements LifeCycle.ViewModel {

    public static final String TAG = "BaseViewModel";

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    
    protected LifeCycle.View viewCallback;


    @CallSuper
    @Override
    public void onCreate() {

    }

    @CallSuper
    @Override
    public void onViewResumed() {

    }

    
    @CallSuper
    @Override
    public void onViewAttached(@android.support.annotation.NonNull LifeCycle.View viewCallback) {
        this.viewCallback=viewCallback;
    }

    @CallSuper
    @Override
    public void onViewDetached() {

        viewCallback=null;

    }

    @CallSuper
    @Override
    public void onDestroy() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }


    protected <T>Observable<T> handleObservable(Observable<T> observable,LifeCycle.View viewCallback){
        return observable.compose(this.<T>applyUIDefaults(viewCallback));
    }

    protected <T>Observable<T> handleObservable(Observable<T> observable){
        return observable.compose(this.<T>applyUIDefaults(viewCallback));
    }


    protected   <T> ObservableTransformer<T, T> applyUIDefaults(final LifeCycle.View viewCallback) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return (ObservableSource<T>) upstream
                        .compose(addToCompositeDisposable())
                   //     .compose(applySchedulers())
                        .compose(showLoadingDialog(viewCallback));
            }
        };
    }




    private  final ObservableTransformer schedulersTransformer =
            new ObservableTransformer() {
                @Override
                public ObservableSource apply(Observable observable) {
                    return observable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
                }
            };

    private   <T> ObservableTransformer<T, T> applySchedulers() {
        return (ObservableTransformer<T, T>) schedulersTransformer;
    }

    private    <T> ObservableTransformer<T, T> addToCompositeDisposable() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        compositeDisposable.add(disposable);
                    }
                });
            }
        };
    }

    private  <T> ObservableTransformer<T, T> applyRequestStatus(final LifeCycle.View viewCallback) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        //viewCallback.setRequestInProgress(true);
                    }
                })
                        .doOnTerminate(new Action() {
                            @Override
                            public void run() throws Exception {
                              //  viewCallback.setRequestInProgress(false);
                            }
                        });
            }
        };
    }

    private    <T> ObservableTransformer<T, T> showLoadingDialog(final LifeCycle.View viewCallback) {
        return (ObservableTransformer<T, T>)new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                if(viewCallback!=null) {
                                    Log.d(TAG, "accept: show");
                                    viewCallback.showProgress();
                                }
                            }
                        })
                        .doOnTerminate(new Action() {
                            @Override
                            public void run() throws Exception {
                                if(viewCallback!=null) {
                                    Log.d(TAG, "run: hide");
                                    viewCallback.hideProgress();
                                }
                            }
                        }).doOnError(new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                if (viewCallback != null) {
                                    Log.d(TAG, "accept: error");
                                    viewCallback.showError(throwable);
                                }
                            }
                        });
            }
        };
    }
}
