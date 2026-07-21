// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: operator-overloading, overview -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: missing operator modifier on set reports OPERATOR_MODIFIER_REQUIRED; invalid compareTo return type reports INAPPLICABLE_OPERATOR_MODIFIER, COMPARE_TO_TYPE_MISMATCH, and TYPE_MISMATCH via checkSubtype<Int>
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class A9021N {
    operator fun get(x: Int): Int = x
    fun set(x: Int, y: Int) {}
}

fun case_1() {
    val a = A9021N()
    <!OPERATOR_MODIFIER_REQUIRED!>a[1]<!> = 2
}


// TESTCASE NUMBER: 2
class A9021NCompare(val n: Int) {
    <!INAPPLICABLE_OPERATOR_MODIFIER!>operator<!> fun compareTo(other: A9021NCompare): Any = this
}

fun case_2() {
    val a = A9021NCompare(-1)
    val b = A9021NCompare(-3)
    val x = a <!COMPARE_TO_TYPE_MISMATCH!>><!> b
    checkSubtype<Int>(<!TYPE_MISMATCH!>a.compareTo(b)<!>)
}
