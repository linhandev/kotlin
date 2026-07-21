// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, bare-type-argument-inference -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: bare type argument inference — runtime as? check with inferred type arguments
 */
// TESTCASE NUMBER: 1

interface Foo144<A>
class Fee144<T>(val value: T) : Foo144<T>

fun box(): String {
    val foo: Foo144<String> = Fee144("ok")
    val fee = foo as? Fee144
    return if (fee?.value == "ok") "OK" else "NOK"
}
