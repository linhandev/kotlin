// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 19 -> sentence 19
 *                type-inference, bare-type-argument-inference -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: precise type inference for bare type is-check with multiple subclass branches in when expression — each branch independently infers correct type arguments
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Foo<A>
class Fee<T>(val t: T) : Foo<T>
class Baz<T>(val t: T) : Foo<T>

fun case_1() {
    val foo: Foo<String> = Fee("hello")
    when (foo) {
        is Fee -> checkSubtype<String>(foo.t)
        is Baz -> {}
        else -> {}
    }
    val foo2: Foo<String> = Baz("world")
    when (foo2) {
        is Fee -> {}
        is Baz -> checkSubtype<String>(foo2.t)
        else -> {}
    }
}
