// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 13 -> sentence 13
 *                expressions, function-literals, lambda-literals -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: precise type inference for bare type is-check smart cast passed to lambda
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Foo<A>
class Fee<T>(val t: T) : Foo<T>

fun case_13() {
    val foo: Foo<String> = Fee("hello")
    val block: (Fee<String>) -> Unit = { fee -> checkSubtype<String>(fee.t) }
    if (foo is Fee) {
        foo checkType { check<Fee<String>>() }
        checkSubtype<Fee<String>>(foo)
        block(foo)
    }
}
