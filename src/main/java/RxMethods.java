import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static util.Util.sleep;

public class RxMethods {
    public static void methodCreate() {
        // Create the observable
        // Version without lambda
//        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                e.onNext("Hello");
//                e.onComplete();
//            }
//        });

        // Version lambda
        Observable<String> observable = Observable.create(e -> {
            e.onNext("create"); // Обёртка над данными
            e.onComplete(); // Завершение работы потока
        });

        // Create an observer
        // Version with observer implementation
//        observable.subscribe(new Observer<>() {
//            @Override
//            public void onSubscribe(@NotNull Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(@NotNull String s) {
//                System.out.println("onNext: " + s);
//            }
//
//            @Override
//            public void onError(@NotNull Throwable e) {
//                System.out.println("onError: " + e);
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

        // Version lambda
        Disposable disposable = observable.subscribe(
                n -> {
                    System.out.println("onNext: " + n);
                }
        );

        disposable.dispose(); // Unsubscribe
    }

    public static void methodJust() {
        Observable<String> observable = Observable.just("1","2","3");

        Disposable disposable = observable.subscribe(
                n -> {
                    System.out.println("onNext " + n);
                }
        );
        disposable.dispose();
    }

    public static void methodDefer() {
        // A disposable container that can hold onto multiple other disposables
        // and offers O(1) add and removal complexity.
        CompositeDisposable disposables = new CompositeDisposable();

        Observable<Long> observable = Observable.defer (
                () -> Observable.just(System.currentTimeMillis())
        );

        disposables.add(observable.subscribe(
                n -> {
                    System.out.println("onNext " + n);
                }
        ));

        sleep(2000);

        disposables.add(observable.subscribe(
                n -> {
                    System.out.println("onNext " + n);
                }
        ));

        // Using clear will clear all, but can accept new disposable
        disposables.clear();
        // Using dispose will clear all and set isDisposed = true, so it will not accept any new disposable
        disposables.dispose();
    }

    public static void methodEmpty() {
        Observable<String> observable = Observable.empty();

        Disposable disposable = observable.subscribe(
                n -> {
                    System.out.println("onNext " + n);
                },
                e -> {
                    System.out.println("onError " + e);
                },
                () -> {
                    System.out.println("onComplete");
                }
        );
        disposable.dispose();
    }

    public static void methodNever() {
        System.out.println("methodNever start");
        Observable<String> observable = Observable.never();

        Disposable disposable = observable.subscribe(
                n -> {
                    System.out.println("onNext " + n);
                },
                e -> {
                    System.out.println("onError " + e);
                },
                () -> {
                    System.out.println("onComplete");
                }
        );
        disposable.dispose();
        System.out.println("methodNever cancel");
    }

    public static void methodError() {
        System.out.println("methodError start");
        Observable<String> observable = Observable.error(
                () -> {
                    if (Math.random() < 0.5) {
                        throw new IOException();
                    }
                    throw new IllegalArgumentException();
                }
        );

        // Error handling
        Observable<String> result = observable.onErrorResumeNext(
                error -> {
                    if (error instanceof IllegalArgumentException) {
                        return Observable.empty();
                    } else {
                        return Observable.error(error);
                    }
                }
        );


        for (int i=0; i<9; i++) {
            Disposable disposable = result.subscribe(
                    n -> {
                        System.out.println("onNext " + n);
                    },
                    e -> {
                        System.out.println("onError " + e.getClass().toString());
                    },
                    () -> {
                        System.out.println("onComplete");
                    }
            );
            disposable.dispose();
        }
        System.out.println("methodError cancel");
    }

    public static void methodRange() {
        System.out.println("methodRange start");
        String str = "methodRange";
        Observable<Integer> observable = Observable.range(0, str.length());
        Disposable disposable = observable.subscribe(
                n -> System.out.println("onNext " + str.charAt(n)),
                e -> System.out.println("onError " + e),
                () -> System.out.println("onComplete")
        );
        disposable.dispose();

        System.out.println("methodRange cancel");
    }

    public static void methodInterval() {
        System.out.println("methodInterval start");
        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS);
        Disposable disposable = observable.subscribe(
                n -> {
                    if (n % 2 == 0L) {
                        System.out.println("Tik ");
                    } else {
                        System.out.println("Tok ");
                    }
                },
                e -> System.out.println("onError " + e),
                () -> System.out.println("onComplete")
        );

        sleep(5000);
        disposable.dispose();
        System.out.println("methodInterval cancel");
    }

    public static void methodTimer() {
        System.out.println("methodTimer start");
        Observable<Long> observable = Observable.timer(5, TimeUnit.SECONDS);
        Disposable disposable = observable.subscribe(
                n -> System.out.println("onNext " + n),
                e -> System.out.println("onError " + e),
                () -> System.out.println("onComplete")
        );

        sleep(8000);
        disposable.dispose();
        System.out.println("methodTimer cancel");
    }

    public static void methodFrom() {
        System.out.println("methodFrom start");
        Callable<String> callable = () -> "Method From";

        Observable<String> observable = Observable.fromCallable(callable);

        Disposable disposable = observable.subscribe(
                n -> System.out.println("onNext " + n),
                e -> System.out.println("onError " + e),
                () -> System.out.println("onComplete")
        );

        disposable.dispose();
        System.out.println("methodFrom cancel");
    }
}
