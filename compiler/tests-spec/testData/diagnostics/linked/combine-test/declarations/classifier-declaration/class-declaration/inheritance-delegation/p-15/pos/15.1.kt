// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: empty interfaces multi class delegation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface I1
interface I2

class Impl1 : I1
class Impl2 : I2

class Delegate(i1: I1, i2: I2) : I1 by i1, I2 by i2

fun case_1() {
    val d = Delegate(Impl1(), Impl2())
    checkSubtype<Delegate>(d)
    checkSubtype<I1>(d)
    checkSubtype<I2>(d)
}
