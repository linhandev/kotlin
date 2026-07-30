/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: class delegation preserves extension function dispatch
 */

// TESTCASE NUMBER: 1
interface I

class Impl : I

class Delegate(i: I) : I by i

fun I.ext() = "ext"

fun test() = Delegate(Impl()).ext()

fun box(): String {
    if (test() != "ext") return "NOK"
    return "OK"
}
