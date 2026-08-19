// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 15 -> sentence 15
 *                type-system, type-kinds, intersection-types -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: bare type is check with intersection type smart cast via &&
 */

// TESTCASE NUMBER: 1
interface Foo<A>
interface Bar {
    fun bar(): String
}
class Fee<T>(val t: T) : Foo<T>, Bar {
    override fun bar(): String = "bar"
}

fun test(foo: Foo<String>): String {
    if (foo is Fee && foo is Bar) {
        return foo.t + foo.bar()
    }
    return "fail"
}

fun box(): String {
    val fee: Foo<String> = Fee("hello")
    if (test(fee) != "hellobar") return "NOK"
    class OtherFoo(val s: String) : Foo<String>
    if (test(OtherFoo("x")) != "fail") return "NOK"
    return "OK"
}
