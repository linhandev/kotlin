// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: Pair/Triple destructuring uses the same componentN mechanism
 */

// TESTCASE NUMBER: 1
fun test(): Int {
    val (a, b, c) = Triple(1, 2, 3)
    return a + b + c
}

fun box(): String {
    if (test() != 6) return "NOK"
    return "OK"
}
