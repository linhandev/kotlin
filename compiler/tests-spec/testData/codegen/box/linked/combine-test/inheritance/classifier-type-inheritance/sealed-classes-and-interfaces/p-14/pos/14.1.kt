// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 14 -> sentence 14
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 14 -> sentence 14
 *                declarations, declarations-with-type-parameters -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: generic sealed hierarchy when is exhaustive and preserves type arguments
 */

// TESTCASE NUMBER: 1
sealed class Result<out T>
data class Ok<T>(val value: T) : Result<T>()
data class Err(val message: String) : Result<Nothing>()

fun test(r: Result<Int>): Int = when (r) {
    is Ok -> r.value
    is Err -> r.message.length
}

fun box(): String {
    if (test(Ok(5)) != 5) return "NOK"
    if (test(Err("xy")) != 2) return "NOK"
    return "OK"
}
