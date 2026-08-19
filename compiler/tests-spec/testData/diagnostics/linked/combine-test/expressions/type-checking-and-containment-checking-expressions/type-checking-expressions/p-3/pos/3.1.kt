// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: precise type inference for is-check with explicit type arguments
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Foo<A>
class Fee<T>(val t: T) : Foo<T>

fun case_1() {
    val foo: Foo<String> = Fee("hello")
    if (foo is Fee<String>) {
        foo checkType { check<Fee<String>>() }
        checkSubtype<Fee<String>>(foo)
        foo.t checkType { check<String>() }
    }
}
