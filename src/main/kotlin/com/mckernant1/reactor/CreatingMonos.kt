package com.mckernant1.reactor

import reactor.core.publisher.Mono


/**
 * Learn how to create Mono instances.
 *
 * @author Sebastien Deleuze
 * @see [Mono Javadoc](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html)
 */
class Part02Mono {
    //========================================================================================
    // TODO Return an empty Mono
    fun emptyMono(): Mono<String>? {
        return Mono.empty()
    }

    //========================================================================================
    // TODO Return a Mono that never emits any signal
    fun monoWithNoSignal(): Mono<String>? {
        return Mono.never()
    }

    //========================================================================================
    // TODO Return a Mono that contains a "foo" value
    fun fooMono(): Mono<String>? {
        return Mono.just("foo")
    }

    //========================================================================================
    // TODO Create a Mono that emits an IllegalStateException
    fun errorMono(): Mono<String>? {
        return Mono.error(IllegalStateException())
    }
}
