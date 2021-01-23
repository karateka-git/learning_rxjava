import io.reactivex.Flowable;
public class HelloWorld {
    public static void hello() {
        Flowable.just("Hello World!").subscribe(System.out::println);
    }
}
