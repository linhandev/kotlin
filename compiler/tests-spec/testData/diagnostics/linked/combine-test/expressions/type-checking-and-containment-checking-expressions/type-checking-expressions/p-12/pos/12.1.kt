// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: precise type inference for nested generic bare type is-check
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Foo<A>
class Fee<T>(val items: List<T>) : Foo<List<T>>

fun case_12() {
    val foo: Foo<List<String>> = Fee(listOf("hello", "world"))
    if (foo is Fee) {
        foo checkType { check<Fee<String>>() }
        checkSubtype<Fee<String>>(foo)
        checkSubtype<List<String>>(foo.items)
    }
}
