// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, bare-type-argument-inference -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: bare type argument inference — runtime is check infers swapped type parameters Fee144<Int, String>
 */
// TESTCASE NUMBER: 1

interface Foo144<A, B> {
    fun pair(): Pair<A, B>
}

class Fee144<T, U>(private val t: T, private val u: U) : Foo144<U, T> {
    override fun pair(): Pair<U, T> = u to t
}

fun box(): String {
    val foo: Foo144<String, Int> = Fee144(42, "ok")
    if (foo !is Fee144) return "NOK"
    val pair = foo.pair()
    if (pair.first != "ok") return "NOK"
    if (pair.second != 42) return "NOK"
    return "OK"
}
