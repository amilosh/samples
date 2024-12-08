package pl.amilosh;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Article {
    private final List<String> comments = new ArrayList<>();
    private final Lock lock = new ReentrantLock();

    public void addComment(String comment) {
        lock.lock();
        try {
            var threadName = Thread.currentThread().getName();
            System.out.println("Thread " + threadName + " start adding " + comment);
            Thread.sleep(3_000L);
            comments.add(comment);
            System.out.println("Thread " + threadName + " added " + comment);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void addCommentTryLock(String comment) {
        if (lock.tryLock()) {
            try {
                var threadName = Thread.currentThread().getName();
                System.out.println("Thread " + threadName + " start adding " + comment);
                Thread.sleep(3_000L);
                comments.add(comment);
                System.out.println("Thread " + threadName + " added " + comment);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Resource locked. " + Thread.currentThread().getName() + " will not add comment");
        }
    }
}
