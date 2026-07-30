// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 83 -> sentence 83
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 83 -> sentence 83
 *                declarations, property-declaration -> paragraph 83 -> sentence 83
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 83 -> sentence 83
 * NUMBER: 1
 * DESCRIPTION: protected primary constructor property visible in subclass
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base(protected val token: Int)
class Sub : Base(1) {
    fun get(): Int = token
}

fun case1() {
    checkSubtype<Int>(Sub().get())
}

