// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, object-literals -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: anonymous object { val n = 42 } without supertypes exposes property n
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val o = object {
        val n = 42
    }
    return if (o.n == 42) "OK" else "NOK"
}
