// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 2 -> sentence 2
 * NUMBER: 3
 * DESCRIPTION: Counter -= 2 invokes minusAssign and yields v == 3 at runtime
 */

class Counter(var v: Int) {
    operator fun minusAssign(other: Int) {
        v -= other
    }
}

// TESTCASE NUMBER: 1
fun box(): String {
    val c = Counter(5)
    c -= 2
    return if (c.v == 3) "OK" else "NOK"
}
