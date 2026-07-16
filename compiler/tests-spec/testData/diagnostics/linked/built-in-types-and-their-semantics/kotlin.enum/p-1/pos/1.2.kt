// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.enum -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: enum constants expose name and ordinal properties with kotlin.String and kotlin.Int types
 * HELPERS: checkType
 */
// TESTCASE NUMBER: 1
enum class Direction { NORTH, EAST, SOUTH, WEST }
fun case_1() {
    Direction.NORTH.name checkType { check<String>() }
    Direction.NORTH.ordinal checkType { check<Int>() }
}


// TESTCASE NUMBER: 2
fun case_2(d: Direction) {
    d.name checkType { check<String>() }
    d.ordinal checkType { check<Int>() }
}


// TESTCASE NUMBER: 3
fun case_3() {
    enumValues<Direction>() checkType { check<Array<Direction>>() }
    enumValueOf<Direction>("NORTH") checkType { check<Direction>() }
}
