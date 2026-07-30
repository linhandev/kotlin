// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 9 -> sentence 9
 *                type-system, introduction-1 -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: precise type inference for nullable bare type is-check with smart cast
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Foo<A>
class Fee<T>(val t: T) : Foo<T>

fun case_9() {
    val foo: Foo<String>? = Fee("hello")
    if (foo is Fee) {
        foo checkType { check<Fee<String>>() }
        checkSubtype<Fee<String>>(foo)
        checkSubtype<String>(foo.t)
    }
}
