// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 2 -> sentence 2
 * NUMBER: 7
 * DESCRIPTION: Box -= 2 uses minus fallback when minusAssign unavailable and yields v == 3
 */

class Box(var v: Int) {
    operator fun minus(other: Int) = Box(v - other)
}

// TESTCASE NUMBER: 1
fun box(): String {
    var b = Box(5)
    b -= 2
    return if (b.v == 3) "OK" else "NOK"
}
