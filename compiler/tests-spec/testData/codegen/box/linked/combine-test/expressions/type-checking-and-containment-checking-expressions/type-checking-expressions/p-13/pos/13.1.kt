// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 13 -> sentence 13
 *                expressions, function-literals, lambda-literals -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: bare type is-check with smart cast passes correctly typed argument to lambda at runtime
 */

// TESTCASE NUMBER: 1
interface Foo<A>
class Fee<T>(val t: T) : Foo<T>

class OtherFoo<T> : Foo<T>

fun test(foo: Foo<String>, block: (Fee<String>) -> String): String {
    if (foo is Fee) {
        return block(foo)
    }
    return "not Fee"
}

fun box(): String {
    val fee: Foo<String> = Fee("lambda")
    if (test(fee) { it.t } != "lambda") return "NOK: Fee lambda"
    val other: Foo<String> = OtherFoo()
    if (test(other) { "unused" } != "not Fee") return "NOK: other not Fee"
    return "OK"
}
