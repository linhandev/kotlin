// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: type-inference, smart-casts -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: smart cast for bare type is-check does not leak outside the if branch
 */

// TESTCASE NUMBER: 1
interface Foo<A>
class Fee<T>(val t: T) : Foo<T>

fun case_1(foo: Foo<String>): String {
    if (foo is Fee) {
        return foo.t
    }
    return foo.<!UNRESOLVED_REFERENCE!>t<!>
}
