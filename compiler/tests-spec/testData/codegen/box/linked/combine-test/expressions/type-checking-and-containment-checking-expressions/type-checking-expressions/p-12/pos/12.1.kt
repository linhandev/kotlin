// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: nested generic bare type is-check with type argument inference works correctly at runtime
 */

// TESTCASE NUMBER: 1
interface Foo<A>
class Fee<T>(val items: List<T>) : Foo<List<T>>

class OtherFoo<T>(val items: List<T>) : Foo<List<T>>

fun test(foo: Foo<List<String>>): String {
    if (foo is Fee) {
        return foo.items[0] + foo.items[1]
    }
    return "not Fee"
}

fun box(): String {
    val fee: Foo<List<String>> = Fee(listOf("hello", "world"))
    if (test(fee) != "helloworld") return "NOK: Fee"
    val other: Foo<List<String>> = OtherFoo(listOf("x", "y"))
    if (test(other) != "not Fee") return "NOK: OtherFoo not Fee"
    return "OK"
}
