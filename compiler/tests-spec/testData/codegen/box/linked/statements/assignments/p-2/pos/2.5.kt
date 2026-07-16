// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 2 -> sentence 2
 * NUMBER: 5
 * DESCRIPTION: Counter /= 4 invokes divAssign and yields v == 5 at runtime
 */

class Counter(var v: Int) {
    operator fun divAssign(other: Int) {
        v /= other
    }
}

// TESTCASE NUMBER: 1
fun box(): String {
    val c = Counter(20)
    c /= 4
    return if (c.v == 5) "OK" else "NOK"
}
