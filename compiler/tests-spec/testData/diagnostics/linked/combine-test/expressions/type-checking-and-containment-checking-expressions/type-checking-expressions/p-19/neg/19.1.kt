// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: bare type is-check with star-projection input causes inference failure — smart-casted type is star-projected and cannot be passed as concrete type argument
 */

// TESTCASE NUMBER: 1
interface Foo<A>
class Fee<T>(val t: T) : Foo<T>

fun expectFeeString(fee: Fee<String>) {}

fun case_1(foo: Foo<*>) {
    if (foo is Fee) {
        expectFeeString(<!TYPE_MISMATCH!>foo<!>)
    }
}
