// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 15 -> sentence 15
 *                type-system, introduction-1 -> paragraph 15 -> sentence 15
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: when expression with nullable sealed class subject is exhaustive when null branch is covered type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed class Result {
    class Ok(val value: String) : Result()
    object Err : Result()
}

fun case1() {
    val r: Result? = Result.Ok("hi")
    checkSubtype<Int>(when (r) {
        is Result.Ok -> r.value.length
        Result.Err -> -1
        null -> 0
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val r: Result? = null
    checkSubtype<Int>(when (r) {
        is Result.Ok -> r.value.length
        Result.Err -> -1
        null -> 0
    })
}
