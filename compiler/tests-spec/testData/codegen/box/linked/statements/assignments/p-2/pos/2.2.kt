// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: Box += 2 uses plus fallback when plusAssign unavailable and yields v == 3
 */

class Box(var v: Int) {
    operator fun plus(other: Int) = Box(v + other)
}

// TESTCASE NUMBER: 1
fun box(): String {
    var b = Box(1)
    b += 2
    return if (b.v == 3) "OK" else "NOK"
}
