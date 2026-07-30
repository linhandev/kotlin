// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 15 -> sentence 15
 *                type-system, introduction-1 -> paragraph 15 -> sentence 15
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: when expression with nullable sealed class subject is exhaustive when null branch is covered
 */

// TESTCASE NUMBER: 1
sealed class Result {
    class Ok(val value: String) : Result()
    object Err : Result()
}

fun test(r: Result?): Int = when (r) {
    is Result.Ok -> r.value.length
    Result.Err -> -1
    null -> 0
}

fun box(): String {
    if (test(Result.Ok("hi")) != 2) return "NOK"
    if (test(Result.Err) != -1) return "NOK"
    if (test(null) != 0) return "NOK"
    return "OK"
}
