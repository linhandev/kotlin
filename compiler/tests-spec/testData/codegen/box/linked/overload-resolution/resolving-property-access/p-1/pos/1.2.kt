/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, resolving-property-access -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: property assignment a.x = y resolves to mutable property setter
 */

class Holder1145 {
    var score1145: Int = 0
}

// TESTCASE NUMBER: 1
fun box(): String {
    val h = Holder1145()
    h.score1145 = 42
    return if (h.score1145 == 42) "OK" else "NOK"
}
