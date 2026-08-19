
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: object literal can inherit class and implement interface
 */

// TESTCASE NUMBER: 1
open class Base
interface Extra { fun extra(): Int }

fun test(): Int = object : Base(), Extra {
    override fun extra(): Int = 7
}.extra()

fun box(): String {
    if (test() != 7) return "NOK"
    return "OK"
}
