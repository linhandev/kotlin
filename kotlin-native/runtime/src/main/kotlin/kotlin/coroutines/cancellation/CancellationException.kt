/*
 * Copyright 2010-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

package kotlin.coroutines.cancellation

import kotlin.experimental.ExperimentalNativeApi
import kotlin.native.internal.GCUnsafeCall
import kotlin.native.internal.NativePtrArray

@SinceKotlin("1.4")
public actual open class CancellationException : IllegalStateException {
    public actual constructor() : super()
    public actual constructor(message: String?) : super(message)
    public constructor(message: String?, cause: Throwable?) : super(message, cause)
    public constructor(cause: Throwable?) : super(cause)

    override fun getCustomStackTrace(): NativePtrArray {
        return getEmptyStackTrace()
    }
}

@GCUnsafeCall("Kotlin_getEmptyStackTrace", false)
@kotlin.native.internal.escapeAnalysis.Escapes.Nothing
private external fun getEmptyStackTrace(): NativePtrArray

/**
 * Creates an instance of [CancellationException] with the given [message] and [cause].
 */
@SinceKotlin("1.4")
@Deprecated("Provided for expect-actual matching", level = DeprecationLevel.HIDDEN)
@kotlin.internal.InlineOnly
public actual inline fun CancellationException(message: String?, cause: Throwable?): CancellationException =
        CancellationException(message, cause)

/**
 * Creates an instance of [CancellationException] with the given [cause].
 */
@SinceKotlin("1.4")
@Deprecated("Provided for expect-actual matching", level = DeprecationLevel.HIDDEN)
@kotlin.internal.InlineOnly
public actual inline fun CancellationException(cause: Throwable?): CancellationException =
        CancellationException(cause)
