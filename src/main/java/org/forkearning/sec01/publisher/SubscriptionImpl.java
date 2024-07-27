package org.forkearning.sec01.publisher;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriptionImpl implements Subscription {
    private static final Logger log = LoggerFactory.getLogger(SubscriptionImpl.class);
    private static final int MAX_ITEMS = 10;
    private int count = 0;
    private final Faker faker;
    private final Subscriber<? super String> subscriber;
    private boolean isCancelled;

    public SubscriptionImpl(Subscriber<? super String> subscriber){
        this.faker = Faker.instance();
        this.subscriber = subscriber;
    }
    @Override
    public void request(long requestor) {
        if(isCancelled){
            return;
        }
        log.info("Subscriber has requested: {}", requestor);
        if(requestor > MAX_ITEMS){
            this.subscriber.onError(new RuntimeException("Requested item count is greater!"));
            return;
        }
        for (int i = 0; i < requestor && count< MAX_ITEMS; i++) {
            count++;
            this.subscriber.onNext(this.faker.internet().emailAddress());
        }
        if(count > MAX_ITEMS){
            log.info("No more data to produce");
            this.subscriber.onComplete();
            this.isCancelled = true;
        }

    }

    @Override
    public void cancel() {
        log.info("Subscriber cancelled!");
        isCancelled = true;
    }
}
