package pl.amilosh;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Article article = new Article();

        for (int i = 1; i <= 5; i++) {
            var comment = "comment " + i;
//            executorService.execute(() -> article.addComment(comment));
            executorService.execute(() -> article.addCommentTryLock(comment));
        }

        executorService.shutdown();
    }
}
