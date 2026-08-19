// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 14 -> sentence 14
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 14 -> sentence 14
 *                declarations, declarations-with-type-parameters -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: generic sealed hierarchy when is exhaustive and preserves type arguments
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed class Result<out T>
data class Ok<T>(val value: T) : Result<T>()
data class Err(val message: String) : Result<Nothing>()

fun case_1(r: Result<Int>) {
    checkSubtype<Int>(when (r) {
        is Ok -> r.value
        is Err -> r.message.length
    })
}
