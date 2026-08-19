// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 19 -> sentence 19
 *                type-inference, bare-type-argument-inference -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: bare type is-check with multiple subclass branches in when expression — smart cast works independently per branch at runtime
 */

// TESTCASE NUMBER: 1
interface Foo<A>
class Fee<T>(val t: T) : Foo<T>
class Baz<T>(val t: T) : Foo<T>

fun test(foo: Foo<String>): String = when (foo) {
    is Fee -> foo.t
    is Baz -> foo.t
    else -> ""
}

fun box(): String {
    if (test(Fee("hello")) != "hello") return "NOK: Fee"
    if (test(Baz("world")) != "world") return "NOK: Baz"
    if (test(object : Foo<String> {}) != "") return "NOK: other Foo"
    return "OK"
}
