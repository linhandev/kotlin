// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 44 -> sentence 44
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 44 -> sentence 44
 * NUMBER: 1
 * DESCRIPTION: where combines marker interface and Any upper bounds
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Mark

class Box<T> where T : Mark, T : Any { fun f(t: T): T = t }

class Impl : Mark

fun test(): Impl = Box<Impl>().f(Impl())

fun case1() {
    checkSubtype<Impl>(test())
}
