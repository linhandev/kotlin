// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 17 -> sentence 17
 *                type-system, introduction-1 -> paragraph 17 -> sentence 17
 *                expressions, when-expressions -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: when expression with nullable sealed class subject and else branch covering null and remaining subclasses
 */

// TESTCASE NUMBER: 1
sealed class Result {
    class Ok(val value: String) : Result()
    object Err : Result()
}

fun test(r: Result?): Int = when (r) {
    is Result.Ok -> r.value.length
    else -> -1
}

fun box(): String {
    if (test(Result.Ok("hello")) != 5) return "NOK"
    if (test(Result.Err) != -1) return "NOK"
    if (test(null) != -1) return "NOK"
    return "OK"
}
