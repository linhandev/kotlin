// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 6 -> sentence 6
 *                type-inference, bare-type-argument-inference -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: precise type inference for bare type is-check in when expression with smart cast
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Foo<A>
class Fee<T>(val t: T) : Foo<T>

fun case_1() {
    val foo: Foo<String> = Fee("hello")
    val result = when (foo) {
        is Fee -> foo.t
        else -> ""
    }
    result checkType { check<String>() }
    checkSubtype<String>(result)
}
