package com.mckernant1.reactor

import reactor.core.publisher.Flux
import java.time.Duration


//generic imports to help with simpler IDEs (ie tech.io)

/**
 * Learn how to create Flux instances.
 *
 * @author Sebastien Deleuze
 * @see [Flux Javadoc](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html)
 */
class Part01Flux {
    //========================================================================================
    // TODO Return an empty Flux
    fun emptyFlux(): Flux<String>? {
        return Flux.empty()
    }

    //========================================================================================
    // TODO Return a Flux that contains 2 values "foo" and "bar" without using an array or a collection
    fun fooBarFluxFromValues(): Flux<String>? {
        return Flux.just("foo", "bar")
    }

    //========================================================================================
    // TODO Create a Flux from a List that contains 2 values "foo" and "bar"
    fun fooBarFluxFromList(): Flux<String>? {
        return Flux.fromArray(arrayOf("foo", "bar"))
    }

    //========================================================================================
    // TODO Create a Flux that emits an IllegalStateException
    fun errorFlux(): Flux<String>? {
        return Flux.error(IllegalStateException())
    }

    //========================================================================================
    // TODO Create a Flux that emits increasing values from 0 to 9 each 100ms
    fun counter(): Flux<Long>? {
        return Flux.interval(Duration.ofMillis(100)).take(10)
    }
}
