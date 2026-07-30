// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: bare type !is test with automatic type argument inference works correctly at runtime
 */

// TESTCASE NUMBER: 1
interface Foo<A>
class Fee<T>(val t: T) : Foo<T>

class Baz<T>(val t: T) : Foo<T>

fun test(foo: Foo<String>): Boolean = foo !is Fee

fun box(): String {
    val fee: Foo<String> = Fee("hello")
    if (test(fee)) return "NOK: Fee should be Fee"
    val baz: Foo<String> = Baz("world")
    if (!test(baz)) return "NOK: Baz should not be Fee"
    return "OK"
}
