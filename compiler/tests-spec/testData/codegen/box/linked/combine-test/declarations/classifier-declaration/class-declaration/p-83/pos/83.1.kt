// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 83 -> sentence 83
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 83 -> sentence 83
 *                declarations, property-declaration -> paragraph 83 -> sentence 83
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 83 -> sentence 83
 * NUMBER: 1
 * DESCRIPTION: protected primary constructor property visible inside subclass
 */

// TESTCASE NUMBER: 1
open class Base(protected val token: Int)
class Sub : Base(1) {
    fun get(): Int = token
}

fun test(): Int = Sub().get()


fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
