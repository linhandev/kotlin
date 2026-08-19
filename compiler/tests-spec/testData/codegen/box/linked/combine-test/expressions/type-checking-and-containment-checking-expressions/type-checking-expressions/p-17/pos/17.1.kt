// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: bare type is check infers function-typed property type parameter and runs correctly at runtime
 */

// TESTCASE NUMBER: 1
interface Foo<A>
class Fee<T>(val fn: () -> T) : Foo<T>

fun test(foo: Foo<String>): String {
    if (foo is Fee) {
        return foo.fn()
    }
    return "fail"
}

fun box(): String {
    val fee: Foo<String> = Fee { "hello" }
    if (test(fee) != "hello") return "NOK"
    class OtherFoo : Foo<String>
    if (test(OtherFoo()) != "fail") return "NOK"
    return "OK"
}
