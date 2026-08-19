// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: lazy with LazyThreadSafetyMode.SYNCHRONIZED
 */

// TESTCASE NUMBER: 1
class Box {
    val x: Int by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { 42 }
}

fun test() = Box().x

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
