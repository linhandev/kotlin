// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: enum State example from specification at runtime
 */

// TESTCASE NUMBER: 1
enum class State { LIQUID, SOLID, GAS }

fun box(): String {
    if (State.SOLID.name != "SOLID") return "NOK"
    if (State.SOLID.ordinal != 1) return "NOK"
    if (!(State.GAS > State.LIQUID)) return "NOK"
    if (State.SOLID.toString() != "SOLID") return "NOK"
    if (State.valueOf("SOLID") != State.SOLID) return "NOK"
    if (State.values().size != 3) return "NOK"
    return "OK"
}
