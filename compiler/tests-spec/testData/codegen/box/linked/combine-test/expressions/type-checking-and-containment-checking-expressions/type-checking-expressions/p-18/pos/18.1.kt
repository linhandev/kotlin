// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 18 -> sentence 18
 *                declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: bare type is check inside inline function with reified type parameter
 */

// TESTCASE NUMBER: 1
interface Foo<A>
class Fee<T>(val t: T) : Foo<T>

inline fun <reified T> checkAndGet(foo: Foo<T>): T? {
    if (foo is Fee) {
        return foo.t
    }
    return null
}

fun box(): String {
    val fee: Foo<String> = Fee("hello")
    if (checkAndGet<String>(fee) != "hello") return "NOK"
    class OtherFoo : Foo<String>
    if (checkAndGet<String>(OtherFoo()) != null) return "NOK"
    return "OK"
}
