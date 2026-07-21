// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT
// WITH_STDLIB

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, bare-type-argument-inference -> paragraph 1 -> sentence 1
 * NUMBER: 6
 * DESCRIPTION: bare type argument inference — nested type parameter List<T> inferred from Foo<List<String>> subject
 * HELPERS: checkType
 */

interface Foo144<A>
class Fee144<T> : Foo144<List<T>>

// TESTCASE NUMBER: 1
fun case_1(foo: Foo144<List<String>>) {
    if (foo is Fee144) {
        checkSubtype<Fee144<String>>(<!DEBUG_INFO_SMARTCAST!>foo<!>)
    }
}
