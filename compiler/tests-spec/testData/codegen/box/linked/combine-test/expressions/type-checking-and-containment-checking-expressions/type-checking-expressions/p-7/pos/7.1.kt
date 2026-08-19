// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: bare type is-check with multi-type-parameter inference works correctly at runtime
 */

// TESTCASE NUMBER: 1
interface Foo<A, B>
class Fee<T, U>(val first: T, val second: U) : Foo<U, T>

fun test(foo: Foo<String, Int>): String {
    if (foo is Fee) {
        return foo.first.toString() + foo.second
    }
    return ""
}

fun box(): String {
    val f: Foo<String, Int> = Fee(42, "hello")
    if (test(f) != "42hello") return "NOK: Fee(42, \"hello\")"
    val f2: Foo<String, Int> = Fee(100, "world")
    if (test(f2) != "100world") return "NOK: Fee(100, \"world\")"
    return "OK"
}
