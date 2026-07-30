// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 14 -> sentence 14
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 14 -> sentence 14
 *                type-inference, smart-casts -> paragraph 14 -> sentence 14
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: when expression is branch smart casts sealed class subject for property access type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed class Result {
    class Ok(val value: String) : Result()
    class Err(val code: Int) : Result()
}

fun case1() {
    val r: Result = Result.Ok("hello")
    checkSubtype<Int>(when (r) {
        is Result.Ok -> r.value.length
        is Result.Err -> r.code
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val r: Result = Result.Err(42)
    checkSubtype<Int>(when (r) {
        is Result.Ok -> r.value.length
        is Result.Err -> r.code
    })
}
