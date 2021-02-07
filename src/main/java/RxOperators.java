import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class RxOperators {
    public static void operatorsFilter() {
        Observable.just(1,2,3,4,5,6,7).filter(it -> it > 4).subscribe(System.out::println);
    }

    public static void operatorsMap() {
        Observable.just(1,2,3,4,5,6,7).map(it -> it*it).subscribe(System.out::println);
    }

    public static void operatorsFlatMap() {
    }

    public static void operatorsConcatMap() {
    }

    public static void operatorsSwitchMap() {
    }

    public static void operatorsConcat() {
        Observable<Integer> observable_1 = Observable.just(1,2,3,4);
        Observable<Integer> observable_2 = Observable.just(5,6,7);
        Observable.concat(observable_1, observable_2).subscribe(System.out::println);
    }

    public static void operatorsMerge() {
        Observable<Integer> observable_1 = Observable.just(1,2,3,4);
        Observable<Integer> observable_2 = Observable.just(5,6,7);
        Observable.merge(observable_1, observable_2).subscribe(System.out::println);
    }

    public static void operatorsDebounce() {
        Observable.just(1,2,3,4,5,6,7).debounce(1, TimeUnit.SECONDS).subscribe(System.out::println);
    }
}
