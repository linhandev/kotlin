// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, upper-and-lower-bounds -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Type arguments must satisfy declared upper bounds
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T : Number> case_1(): T = <!TYPE_MISMATCH!>"x"<!>

// TESTCASE NUMBER: 2
fun <T : Number> case_2(x: T) {
    val y: String = <!TYPE_MISMATCH!>x<!>
}

// TESTCASE NUMBER: 3
class Box3<T : Number>(val value: T)

fun case_3(b: Box3<Int>) {
    val s: String = <!TYPE_MISMATCH!>b.value<!>
}

// TESTCASE NUMBER: 4
fun <T : CharSequence> case_4(): T = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>

// TESTCASE NUMBER: 5
fun case_5() {
    fun <T : Enum<T>> id(x: T): T = x
    val bad = id(<!TYPE_MISMATCH!>"x"<!>)
}
