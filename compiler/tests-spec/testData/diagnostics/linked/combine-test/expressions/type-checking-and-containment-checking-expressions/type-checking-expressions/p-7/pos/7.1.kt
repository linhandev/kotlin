// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: precise type inference for bare type is-check with multi-type-parameter inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Foo<A, B>
class Fee<T, U>(val first: T, val second: U) : Foo<U, T>

fun case_1() {
    val foo: Foo<String, Int> = Fee(42, "hello")
    if (foo is Fee) {
        foo checkType { check<Fee<Int, String>>() }
        checkSubtype<Fee<Int, String>>(foo)
        foo.first checkType { check<Int>() }
        foo.second checkType { check<String>() }
    }
}
