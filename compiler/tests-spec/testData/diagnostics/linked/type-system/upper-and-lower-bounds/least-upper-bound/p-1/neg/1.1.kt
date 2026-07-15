// FIR_IDENTICAL
// DIAGNOSTICS: -IMPLICIT_CAST_TO_ANY -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, upper-and-lower-bounds, least-upper-bound -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Incompatible branch types without common supertype fail inference
 * HELPERS: checkType
 */

interface IA
interface IB

// TESTCASE NUMBER: 1
class CA
class CB

fun case_1(f: Boolean) {
    val x = if (f) CA() else CB()
    val y: CA = <!TYPE_MISMATCH!>x<!>
}

// TESTCASE NUMBER: 2
fun case_2(f: Boolean) {
    val x = if (f) CA() else CB()
    val y: CB = <!TYPE_MISMATCH!>x<!>
}

// TESTCASE NUMBER: 3
fun case_3(f: Boolean) {
    val x = if (f) IA::class else IB::class
    val y: IA = <!TYPE_MISMATCH!>x<!>
}

// TESTCASE NUMBER: 4
fun case_4() {
    val x = if (true) 1 else "s"
    val i: Int = <!TYPE_MISMATCH!>x<!>
}
