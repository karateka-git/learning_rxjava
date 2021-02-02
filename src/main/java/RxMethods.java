import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import static util.Util.sleep;

public class RxMethods {
    public static void methodCreate() {
        // Create the observable
        // Version without lambda
//        Observable<String> testCreate = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                e.onNext("Hello");
//                e.onComplete();
//            }
//        });

        // Version lambda
        Observable<String> testCreate = Observable.create(e -> {
            e.onNext("Hello"); // Обёртка над данными
            e.onComplete(); // Завершение работы потока
        });

        // Create an observer
        // Version with observer implementation
//        testCreate.subscribe(new Observer<>() {
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
        Disposable disposable = testCreate.subscribe(
                n -> {
                    System.out.println("onNext: " + n);
                }
        );

        disposable.dispose(); // Отписка
    }
}
