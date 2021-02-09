import io.reactivex.Observable;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

public class RxSubject {
    public static void publishSubject() {
        PublishSubject<Integer> source = PublishSubject.create();

        // Получит 1, 2, 3, 4 и onComplete
        source.subscribe(System.out::println);

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

        // Получит 4 и onComplete для следующего наблюдателя тоже.source.subscribe(getSecondObserver());
        source.subscribe(System.out::println);
        source.onNext(4);
        source.onComplete();
    }

    public static void replaySubject() {
        ReplaySubject<Integer> source = ReplaySubject.create();
        // Он получит 1, 2, 3, 4
        source.subscribe(System.out::println);
        source.onNext(1);
        source.onNext(2);
        source.onNext(3);
        source.onNext(4);
        source.onComplete();
        // Он также получит 1, 2, 3, 4 так как он использует Replay Subject
        source.subscribe(System.out::println);
    }

    public static void behaviorSubject() {
        BehaviorSubject<Integer> source = BehaviorSubject.create();
        // Получит 1, 2, 3, 4 and onComplete
        source.subscribe(System.out::println);
        source.onNext(1);
        source.onNext(2);
        source.onNext(3);
        // Получит 3(последний элемент) и 4(последующие элементы) и onComplete
        source.subscribe(System.out::println);
        source.onNext(4);
        source.onComplete();
    }

    public static void asyncSubject() {
        AsyncSubject<Integer> source = AsyncSubject.create();
        // Получит только 4 и onComplete
        source.subscribe(System.out::println);
        source.onNext(1);
        source.onNext(2);
        source.onNext(3);
        // Тоже получит только 4 и onComplete
        source.subscribe(System.out::println);
        source.onNext(4);
        source.onComplete();
    }
}
