// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: Map property delegation
 */

// TESTCASE NUMBER: 1
class Box(val map: Map<String, Int>) {
    val x: Int by map
}

fun test() = Box(mapOf("x" to 42)).x

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
