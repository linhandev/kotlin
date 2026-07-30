// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 78 -> sentence 78
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 78 -> sentence 78
 *                declarations, property-declaration -> paragraph 78 -> sentence 78
 *                declarations, classifier-declaration, enum-class-declaration -> paragraph 78 -> sentence 78
 * NUMBER: 1
 * DESCRIPTION: enum class primary constructor parameters become properties
 */

// TESTCASE NUMBER: 1
enum class Color(val rgb: Int) { RED(0xFF0000) }

fun test(): Int = Color.RED.rgb


fun box(): String {
    if (test() != 0xFF0000) return "NOK"
    return "OK"
}
