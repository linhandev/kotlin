// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 9 -> sentence 9
 *                type-system, introduction-1 -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: nullable bare type is-check with smart cast and member access works correctly at runtime
 */

// TESTCASE NUMBER: 1
interface Foo<A>
class Fee<T>(val t: T) : Foo<T>

fun test(foo: Foo<String>?): String {
    if (foo is Fee) {
        return foo.t
    }
    return "not Fee"
}

fun box(): String {
    val fee: Foo<String>? = Fee("hello")
    if (test(fee) != "hello") return "NOK: Fee(\"hello\")"
    val nil: Foo<String>? = null
    if (test(nil) != "not Fee") return "NOK: null"
    return "OK"
}
