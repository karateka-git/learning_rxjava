import io.reactivex.Observable;

public class HelloWorld {
    public static void hello() {
        Observable.just("Hello World!").subscribe(System.out::println);
    }
}
