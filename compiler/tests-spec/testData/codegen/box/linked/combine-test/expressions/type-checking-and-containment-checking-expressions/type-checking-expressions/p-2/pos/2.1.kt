// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 2 -> sentence 2
 *                type-inference, smart-casts -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: bare type is-check enables smart cast and accessed members work correctly at runtime
 */

// TESTCASE NUMBER: 1
interface Foo<A>
class Fee<T>(val t: T) : Foo<T>

fun test(foo: Foo<String>): String {
    if (foo is Fee) {
        return foo.t
    }
    return "not Fee"
}

fun box(): String {
    val fee: Foo<String> = Fee("hello")
    if (test(fee) != "hello") return "NOK: Fee(\"hello\")"
    val fee2: Foo<String> = Fee("world")
    if (test(fee2) != "world") return "NOK: Fee(\"world\")"
    return "OK"
}
