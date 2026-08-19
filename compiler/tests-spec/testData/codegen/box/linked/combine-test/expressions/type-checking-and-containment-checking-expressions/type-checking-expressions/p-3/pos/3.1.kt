// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: explicit type arguments in is-check produce consistent result with bare type inference at runtime
 */

// TESTCASE NUMBER: 1
interface Foo<A>
class Fee<T>(val t: T) : Foo<T>

fun test(foo: Foo<String>): String {
    if (foo is Fee<String>) {
        return foo.t
    }
    return "not Fee"
}

fun box(): String {
    val fee: Foo<String> = Fee("hello")
    if (test(fee) != "hello") return "NOK: explicit type argument in is-check"
    val fee2: Foo<String> = Fee("world")
    if (test(fee2) != "world") return "NOK: second instance"
    return "OK"
}
