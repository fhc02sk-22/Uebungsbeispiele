package threading;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TextAnalyzer implements Runnable {

    private ArrayList<String> lines;

    public static int countCharacters;
    private static Lock lock = new ReentrantLock();

    public TextAnalyzer(ArrayList<String> lines) {
        this.lines = lines;
    }

    @Override
    public void run() {

        int counter = 0;
        for (String str : lines){
            counter+= str.length();
        }

        lock.lock();
        countCharacters += counter;
        lock.unlock();
    }
}
