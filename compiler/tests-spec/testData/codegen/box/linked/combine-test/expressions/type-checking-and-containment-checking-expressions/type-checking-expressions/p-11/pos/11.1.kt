// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 11 -> sentence 11
 *                declarations, declarations-with-type-parameters -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: bare type is-check with upper-bounded type parameter works correctly at runtime
 */

// TESTCASE NUMBER: 1
interface Foo<A>
class Fee<T : Number>(val t: T) : Foo<T>

class OtherFoo<T : Number>(val t: T) : Foo<T>

fun test(foo: Foo<Int>): Double {
    if (foo is Fee) {
        return foo.t.toDouble()
    }
    return -1.0
}

fun box(): String {
    val fee: Foo<Int> = Fee(42)
    if (test(fee) != 42.0) return "NOK: Fee(42)"
    val other: Foo<Int> = OtherFoo(99)
    if (test(other) != -1.0) return "NOK: OtherFoo not Fee"
    return "OK"
}
