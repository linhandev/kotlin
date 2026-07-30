// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 47 -> sentence 47
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 47 -> sentence 47
 *                type-inference, introduction-1 -> paragraph 47 -> sentence 47
 *                declarations, function-declaration -> paragraph 47 -> sentence 47
 * NUMBER: 1
 * DESCRIPTION: generic extension function infers type argument at call site
 */

// TESTCASE NUMBER: 1
fun <T> T.twice(): Pair<T, T> = this to this

fun box(): String {
    val p1 = 1.twice()
    if (p1 != Pair(1, 1)) return "NOK"
    val p2 = "a".twice()
    if (p2 != Pair("a", "a")) return "NOK"
    val p3 = true.twice()
    if (p3 != Pair(true, true)) return "NOK"
    return "OK"
}
