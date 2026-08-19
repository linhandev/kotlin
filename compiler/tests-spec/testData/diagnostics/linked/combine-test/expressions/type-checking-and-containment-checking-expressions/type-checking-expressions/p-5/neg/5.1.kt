// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: bare type is-check with star-projection input results in star-projected members that cannot be used as concrete types
 */

// TESTCASE NUMBER: 1
interface Foo<A>
class Fee<T>(val t: T) : Foo<T>

fun case_1(foo: Foo<*>) {
    if (foo is Fee) {
        val x: Int = <!TYPE_MISMATCH!>foo.t<!>
    }
}
