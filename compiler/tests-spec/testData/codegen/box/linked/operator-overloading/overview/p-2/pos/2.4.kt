/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: operator-overloading, overview -> paragraph 2 -> sentence 2
 * NUMBER: 4
 * DESCRIPTION: extension operator defined in context type resolves in a + b via implicit receiver
 */

// TESTCASE NUMBER: 1
class A9024(val n: Int)
class B9024(val n: Int)

object Ctx9024 {
    operator fun A9024.plus(b: B9024) = A9024(n + b.n)

    fun add(a: A9024, b: B9024) = a + b
}

fun box(): String {
    val result = Ctx9024.add(A9024(3), B9024(4))
    return if (result.n == 7) "OK" else "NOK: ${result.n}"
}
