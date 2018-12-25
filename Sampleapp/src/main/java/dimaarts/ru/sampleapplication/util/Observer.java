package dimaarts.ru.sampleapplication.util;

public interface Observer<T> {
    void update(Observable<T> o, T arg);
}
