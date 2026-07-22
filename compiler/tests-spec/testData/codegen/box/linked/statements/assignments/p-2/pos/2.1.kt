// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: Counter += 2 invokes plusAssign and yields v == 3 at runtime
 */

class Counter(var v: Int) {
    operator fun plusAssign(other: Int) {
        v += other
    }
}

// TESTCASE NUMBER: 1
fun box(): String {
    val c = Counter(1)
    c += 2
    return if (c.v == 3) "OK" else "NOK"
}
