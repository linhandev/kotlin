// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 2 -> sentence 2
 * NUMBER: 6
 * DESCRIPTION: Counter %= 3 invokes remAssign and yields v == 1 at runtime
 */

class Counter(var v: Int) {
    operator fun remAssign(other: Int) {
        v %= other
    }
}

// TESTCASE NUMBER: 1
fun box(): String {
    val c = Counter(10)
    c %= 3
    return if (c.v == 1) "OK" else "NOK"
}
