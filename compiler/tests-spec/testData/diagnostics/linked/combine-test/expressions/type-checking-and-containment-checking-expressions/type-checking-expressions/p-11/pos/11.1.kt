// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 11 -> sentence 11
 *                declarations, declarations-with-type-parameters -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: precise type inference for bare type is-check with upper-bounded type parameter
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Foo<A>
class Fee<T : Number>(val t: T) : Foo<T>

fun case_11() {
    val foo: Foo<Int> = Fee(42)
    if (foo is Fee) {
        foo checkType { check<Fee<Int>>() }
        checkSubtype<Fee<Int>>(foo)
        checkSubtype<Int>(foo.t)
    }
}
