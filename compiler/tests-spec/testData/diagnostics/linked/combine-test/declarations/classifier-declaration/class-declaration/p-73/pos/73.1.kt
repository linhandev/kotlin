// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 73 -> sentence 73
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 73 -> sentence 73
 *                inheritance, inheriting -> paragraph 73 -> sentence 73
 * NUMBER: 1
 * DESCRIPTION: subclass primary constructor forwards argument to superclass primary
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base(val x: Int)

class Child(x: Int) : Base(x)

fun test(): Int = Child(2).x

fun case1() {
    checkSubtype<Int>(test())
}
