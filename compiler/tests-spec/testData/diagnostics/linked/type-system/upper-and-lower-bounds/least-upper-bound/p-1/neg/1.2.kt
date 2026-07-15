// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, upper-and-lower-bounds, least-upper-bound -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: LUB result cannot be assigned to unrelated narrower type
 * HELPERS: checkType
 */

open class Base
class Left : Base()
class Right : Base()

// TESTCASE NUMBER: 1
fun case_1(f: Boolean) {
    val x = if (f) Left() else Right()
    val l: Left = <!TYPE_MISMATCH!>x<!>
}

// TESTCASE NUMBER: 2
fun case_2() {
    val x = if (true) 1 else 2L
    val i: Int = <!TYPE_MISMATCH!>x<!>
}

// TESTCASE NUMBER: 3
fun case_3() {
    val x = if (true) 1 else 2.0
    val d: Double = <!TYPE_MISMATCH!>x<!>
}

// TESTCASE NUMBER: 4
fun case_4() {
    val x = if (true) listOf(1) else listOf("a")
    val l: List<Int> = <!TYPE_MISMATCH!>x<!>
}
