// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 39 -> sentence 39
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 39 -> sentence 39
 *                type-inference, introduction-1 -> paragraph 39 -> sentence 39
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 39 -> sentence 39
 * NUMBER: 1
 * DESCRIPTION: generic constructor call infers type arguments from value arguments
 */

// TESTCASE NUMBER: 1
fun box(): String {
    if (Pair(1, "a") != Pair<Int, String>(1, "a")) return "NOK"
    if (Pair("x", 2) != Pair<String, Int>("x", 2)) return "NOK"
    return "OK"
}
