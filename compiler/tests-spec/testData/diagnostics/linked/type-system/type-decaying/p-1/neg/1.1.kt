// FIR_IDENTICAL
// DIAGNOSTICS: -IMPLICIT_CAST_TO_ANY -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-decaying -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Decayed union is not preserved as separate branch types
 * HELPERS: checkType
 */

class CA
class CB

// TESTCASE NUMBER: 1
fun case_1(f: Boolean) {
    val x = if (f) CA() else CB()
    val a: CA = <!TYPE_MISMATCH!>x<!>
}

// TESTCASE NUMBER: 2
fun case_2() {
    val x = if (true) 1 else "s"
    val i: Int = <!TYPE_MISMATCH!>x<!>
}

// TESTCASE NUMBER: 3
fun case_3() {
    val x = if (true) 1.toByte() else 2.toShort()
    val b: Byte = <!TYPE_MISMATCH!>x<!>
}
