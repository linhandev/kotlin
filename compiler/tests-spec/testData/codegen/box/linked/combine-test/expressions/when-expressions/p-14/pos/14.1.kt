// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 14 -> sentence 14
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 14 -> sentence 14
 *                type-inference, smart-casts -> paragraph 14 -> sentence 14
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: when expression is branch smart casts sealed class subject for property access
 */

// TESTCASE NUMBER: 1
sealed class Result {
    class Ok(val value: String) : Result()
    class Err(val code: Int) : Result()
}

fun test(r: Result): Int = when (r) {
    is Result.Ok -> r.value.length
    is Result.Err -> r.code
}

fun box(): String {
    if (test(Result.Ok("hello")) != 5) return "NOK"
    if (test(Result.Err(42)) != 42) return "NOK"
    return "OK"
}
