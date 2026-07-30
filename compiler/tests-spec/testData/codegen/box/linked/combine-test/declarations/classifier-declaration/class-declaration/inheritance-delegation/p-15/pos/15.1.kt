/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: empty interfaces multi class delegation
 */

// TESTCASE NUMBER: 1
interface I1
interface I2

class Impl1 : I1
class Impl2 : I2

class Delegate(i1: I1, i2: I2) : I1 by i1, I2 by i2

fun test(): Delegate = Delegate(Impl1(), Impl2())

fun box(): String {
    val d = test()
    if (d !is I1) return "NOK: I1"
    if (d !is I2) return "NOK: I2"
    val asI1: I1 = d
    val asI2: I2 = d
    if (asI1 !is Delegate) return "NOK: asI1"
    if (asI2 !is Delegate) return "NOK: asI2"
    return "OK"
}
