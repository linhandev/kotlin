// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: precise type inference for bare type is check with function-typed property
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Foo<A>
class Fee<T>(val fn: () -> T) : Foo<T>

fun case1() {
    val foo: Foo<String> = Fee { "hello" }
    if (foo is Fee) {
        checkSubtype<String>(foo.fn())
        checkSubtype<() -> String>(foo.fn)
    }
}
