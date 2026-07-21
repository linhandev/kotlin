// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: enum example State with name ordinal compareTo toString valueOf values
 */

// TESTCASE NUMBER: 1
enum class State { LIQUID, SOLID, GAS }

fun case1() {
    val name = State.SOLID.name
    val ord = State.SOLID.ordinal
    val cmp = State.GAS > State.LIQUID
    val str = State.SOLID.toString()
    val v = State.valueOf("SOLID")
    val all = State.values()
}
