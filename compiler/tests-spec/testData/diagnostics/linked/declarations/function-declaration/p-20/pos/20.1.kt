// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: inline functions may take functional parameters and be called at call-site
 */

// TESTCASE NUMBER: 1
inline fun <T, R> lock(value: T, body: (T) -> R): R = body(value)

fun useLock(): Int = lock(1) { it + 2 }

// TESTCASE NUMBER: 2
inline fun runInline(block: () -> String): String = block()

fun useRunInline(): String = runInline { "ok" }

// TESTCASE NUMBER: 3
inline fun <T> applyInline(value: T, block: T.() -> Unit): T {
    value.block()
    return value
}

fun useApplyInline(): String {
    val builder = StringBuilder()
    applyInline(builder) { append("inline") }
    return builder.toString()
}
