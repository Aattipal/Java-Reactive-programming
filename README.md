**Java Reactive programming:**

**Sec01:**

1. Code is using reactor library
2. Reactive programming uses Observer design pattern
3. **Reactive streams** are a specification: **Reactor** is implementation

# 3 important things:

Publisher
Subscriber 
Subscription

- Publisher has to subscribe using subscription


**Reactor:**

We know that publisher is one of the interfaces in the reactive stream for which the reactor library provides two different implementations.
One is mono, the other one is flux.


**Mono:** Always give 0 or 1 item and call oncomplete/onerror

**Flux:** Always give 0,1,2...N item and call oncomplete/onerror/Cancel

* So why we need Mono when Flux is available ?

Both Mono and flux can handle things in Non-blocking & Async manner.

  Mono     
______________
* No stream                          
* Not need to handle Backpressure    
* Light weight publisher         
* Request/response model          

Flux
______
* stream of Messages
* BackPressure(producer emits too much of data where consumer can't handle)
* Many additional methods specific to handle specific streaming





