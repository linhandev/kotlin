// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: bare type is-check automatically infers type arguments and the class works normally at runtime
 */

// TESTCASE NUMBER: 1
interface Foo<A, B>
class Fee<T, U>(val first: T, val second: U) : Foo<U, T>

fun test(foo: Foo<String, Int>): String {
    if (foo is Fee) {
        return foo.second + foo.first.toString()
    }
    return "not Fee"
}

fun box(): String {
    val fee: Foo<String, Int> = Fee(42, "hello")
    if (test(fee) != "hello42") return "NOK: Fee(42, \"hello\")"
    val fee2: Foo<String, Int> = Fee(7, "world")
    if (test(fee2) != "world7") return "NOK: Fee(7, \"world\")"
    return "OK"
}
