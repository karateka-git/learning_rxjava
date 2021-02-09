import io.reactivex.Observable;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RxOperators {
    public static void operatorsFilter() {
        Observable.just(1,2,3,4,5,6,7).filter(it -> it > 4).subscribe(System.out::println);
    }

    public static void operatorsMap() {
        Observable.just(1,2,3,4,5,6,7).map(it -> it*it).subscribe(System.out::println);
    }

    public static void operatorsFlatMap() {
        // fromIterable - from [1,2,3,4] to Observable(1) .. Observable(4)
        Observable.just(List.of(1,2,3,4)).flatMap(integers -> Observable.fromIterable(integers))
                .subscribe(System.out::println);
    }

    public static void operatorsConcatMap() {
        // It guarantees the order of the observables
        Observable.just(List.of(1,2,3,4)).concatMap(integers -> Observable.fromIterable(integers))
                .subscribe(System.out::println);
    }

    public static void operatorsSwitchMap() {
        // It always returns the latest Observable and emits the items from it
        Observable.just(List.of(1,2,3,4)).switchMap(integers -> Observable.fromIterable(integers))
                .subscribe(System.out::println);
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
