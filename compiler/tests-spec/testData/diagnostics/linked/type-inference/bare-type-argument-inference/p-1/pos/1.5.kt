// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT
// WITH_STDLIB

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, bare-type-argument-inference -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: bare type argument inference — checked cast as? infers type arguments from subject type
 * HELPERS: checkType
 */

interface Foo144<A>
class Fee144<T>(val value: T) : Foo144<T>

// TESTCASE NUMBER: 1
fun case_1(foo: Foo144<String>): String? {
    val fee = foo as? Fee144
    checkSubtype<Fee144<String>?>(fee)
    return fee?.value
}
