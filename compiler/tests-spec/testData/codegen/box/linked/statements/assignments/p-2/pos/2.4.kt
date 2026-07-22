// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 2 -> sentence 2
 * NUMBER: 4
 * DESCRIPTION: Counter *= 4 invokes timesAssign and yields v == 12 at runtime
 */

class Counter(var v: Int) {
    operator fun timesAssign(other: Int) {
        v *= other
    }
}

// TESTCASE NUMBER: 1
fun box(): String {
    val c = Counter(3)
    c *= 4
    return if (c.v == 12) "OK" else "NOK"
}
