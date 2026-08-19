// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 40 -> sentence 40
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 40 -> sentence 40
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 40 -> sentence 40
 * NUMBER: 1
 * DESCRIPTION: generic constructor can explicitly specify type arguments
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val xs = ArrayList<Int>(listOf(1, 2))
    if (xs.size != 2) return "NOK"
    if (xs[0] != 1) return "NOK"
    if (xs[1] != 2) return "NOK"
    return "OK"
}
