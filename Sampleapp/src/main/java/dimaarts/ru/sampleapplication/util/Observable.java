package dimaarts.ru.sampleapplication.util;

import java.util.Vector;

public class Observable<T> {
        private Vector<Observer<T>> obs;
        public Observable() {
            obs = new Vector<>();
        }

        public synchronized void addObserver(Observer<T> o) {
            if (o == null)
                throw new NullPointerException();
            if (!obs.contains(o)) {
                obs.addElement(o);
            }
        }

        public synchronized void deleteObserver(Observer<T> o) {
            obs.removeElement(o);
        }

        public void notifyObservers() {
            notifyObservers(null);
        }

        @SuppressWarnings("unchecked")
        public void notifyObservers(T arg) {
            Object[] arrLocal;

            synchronized (this) {
                arrLocal = obs.toArray();
            }

            for (int i = arrLocal.length-1; i>=0; i--)
                ((Observer<T>)arrLocal[i]).update(this, arg);
        }

        public synchronized void deleteObservers() {
            obs.removeAllElements();
        }

        public synchronized int countObservers() {
            return obs.size();
        }
}
