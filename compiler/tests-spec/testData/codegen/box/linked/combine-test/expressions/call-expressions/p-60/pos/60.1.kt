// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 60 -> sentence 60
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 60 -> sentence 60
 *                type-inference, introduction-1 -> paragraph 60 -> sentence 60
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 60 -> sentence 60
 * NUMBER: 1
 * DESCRIPTION: named value argument does not affect type argument inference
 */

// TESTCASE NUMBER: 1
fun <T> id(x: T): T = x

fun box(): String {
    if (id(x = "a") != "a") return "NOK"
    if (id(x = 1) != 1) return "NOK"
    return "OK"
}
