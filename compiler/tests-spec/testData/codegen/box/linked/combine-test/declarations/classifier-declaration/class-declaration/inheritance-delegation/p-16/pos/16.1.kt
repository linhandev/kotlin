// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: lazy String property delegate
 */

// TESTCASE NUMBER: 1
class Box {
    val x: String by lazy { "lazy" }
}

fun test() = Box().x

fun box(): String {
    if (test() != "lazy") return "NOK"
    return "OK"
}
