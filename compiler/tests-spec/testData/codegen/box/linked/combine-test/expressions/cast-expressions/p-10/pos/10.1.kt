// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 10 -> sentence 10
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: supertype as subtype may throw CCE
 */

// TESTCASE NUMBER: 1
open class Base
class Sub : Base()

fun test(b: Base): Sub = b as Sub

fun box(): String {
    val s = Sub()
    if (test(s) !== s) return "NOK"
    try {
        test(Base())
        return "NOK"
    } catch (_: ClassCastException) {
    }
    return "OK"
}
