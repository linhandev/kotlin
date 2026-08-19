// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 6 -> sentence 6
 *                type-inference, bare-type-argument-inference -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: bare type is-check in when expression with smart cast works correctly at runtime
 */

// TESTCASE NUMBER: 1
interface Foo<A>
class Fee<T>(val t: T) : Foo<T>

fun test(foo: Foo<String>): String = when (foo) {
    is Fee -> foo.t
    else -> ""
}

fun box(): String {
    if (test(Fee("hello")) != "hello") return "NOK: Fee(\"hello\")"
    if (test(object : Foo<String> {}) != "") return "NOK: not Fee"
    return "OK"
}
