// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 86 -> sentence 86
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 86 -> sentence 86
 *                expressions, equality-expressions, reference-equality-expressions -> paragraph 86 -> sentence 86
 * NUMBER: 1
 * DESCRIPTION: nullable vars: same instance === true; different instances false
 */

// TESTCASE NUMBER: 1

class Box

fun test(): Boolean {
    val a: Box? = Box()
    val b: Box? = a
    val c: Box? = Box()
    return a === b && !(a === c)
}

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
