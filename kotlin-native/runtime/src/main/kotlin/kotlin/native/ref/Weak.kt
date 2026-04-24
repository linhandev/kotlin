/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

package kotlin.native.ref

import kotlin.experimental.ExperimentalNativeApi

/**
 * Class WeakReference encapsulates weak reference to an object, which could be used to either
 * retrieve a strong reference to an object, or return null, if object was already destroyed by
 * the memory manager.
 */
@ExperimentalNativeApi
public class WeakReference<T : Any> {
    /**
     * Creates a weak reference object pointing to an object. Weak reference doesn't prevent
     * removing object, and is nullified once object is collected.
     */
    public constructor(referred: T) {
        initWeakReferenceImpl(this, referred)
    }

    /**
     * Backing store for the object pointer, inaccessible directly.
     * TODO: CRT implementation. make a special type for this. CRTWeakRefImpl
     */
    @PublishedApi
    internal var pointer: Long = 0

    /**
     * Clears reference to an object.
     */
    public fun clear() {
        this.pointer = 0L
    }

    /**
     * Returns either reference to an object or null, if it was collected.
     */
    @Suppress("UNCHECKED_CAST")
    public fun get(): T? = derefWeakReferenceImpl(this) as T?

    /**
     * Returns either reference to an object or null, if it was collected.
     */
    public val value: T?
        get() = this.get()
}
