// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 61 -> sentence 61
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 61 -> sentence 61
 *                type-inference, introduction-1 -> paragraph 61 -> sentence 61
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 61 -> sentence 61
 * NUMBER: 1
 * DESCRIPTION: explicit type argument and named value argument can coexist in a call
 */

// TESTCASE NUMBER: 1
fun <T> id(x: T): T = x

fun box(): String {
    if (id<Any>(x = 1) != 1) return "NOK"
    if (id<String>(x = "hello") != "hello") return "NOK"
    return "OK"
}
