package org.forkearning.sec01;

import org.forkearning.sec01.publisher.PublisherImpl;
import org.forkearning.sec01.publisher.SubscriptionImpl;
import org.forkearning.sec01.subscriber.SubscriberImpl;

import java.time.Duration;

public class Main {
    public static void main(String[] args) throws InterruptedException {
       // demo1();
        // demo2();
       // demo3();
        demo4();

    }

    // 1. Publisher doesn't produce data unless subscriber subscribes it
    public static void demo1() {

        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);

    }

    // 2. Publisher produces only <= subscriber requested items. Can publish 0 items as well
    public static void demo2() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);

        Thread.sleep(Duration.ofSeconds(2));

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
    }

    // 3. subscriber can cancel the subscription, publisher should not publish
    public static void demo3() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));

        subscriber.getSubscription().cancel(); // cancelled, after this no items will be published

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));

    }

    // 4. producer can send ERROR
    private static void demo4() {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(20);

    }

}