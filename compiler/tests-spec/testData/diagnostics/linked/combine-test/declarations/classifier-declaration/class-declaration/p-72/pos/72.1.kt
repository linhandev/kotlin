// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 72 -> sentence 72
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 72 -> sentence 72
 *                inheritance, inheriting -> paragraph 72 -> sentence 72
 * NUMBER: 1
 * DESCRIPTION: class without primary uses secondary constructor delegating to superclass
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base(val x: Int)

class Child : Base { constructor() : super(1) }

fun test(): Int = Child().x

fun case1() {
    checkSubtype<Int>(test())
}
