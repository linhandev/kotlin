// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: enum entry provides name ordinal and compareTo
 */

// TESTCASE NUMBER: 1
enum class State { LIQUID, SOLID, GAS }

fun case1() {
    val s = State.SOLID
    val n: String = s.name
    val o: Int = s.ordinal
    val c: Int = s.compareTo(State.GAS)
}
