// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 80 -> sentence 80
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 80 -> sentence 80
 *                declarations, property-declaration -> paragraph 80 -> sentence 80
 * NUMBER: 1
 * DESCRIPTION: init runs after primary constructor properties; y equals x+1
 */

// TESTCASE NUMBER: 1
class Demo(val x: Int) {
    val y: Int
    init { y = x + 1 }
}

fun test(): Int = Demo(1).y


fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
