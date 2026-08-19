// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 9 -> sentence 9
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: subtype as supertype
 */

// TESTCASE NUMBER: 1
open class Base
class Sub : Base()

@Suppress("USELESS_CAST")
fun test(s: Sub): Base = s as Base

fun box(): String {
    val s = Sub()
    if (test(s) !== s) return "NOK"
    return "OK"
}
