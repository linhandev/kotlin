// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, subtyping, subtyping-rules -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Invariant type parameters reject unsafe parameterized subtyping
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val m: MutableList<Int> = mutableListOf(1)
    val n: MutableList<Number> = <!TYPE_MISMATCH!>m<!>
}

// TESTCASE NUMBER: 2
fun case_2() {
    val m: MutableList<Number> = mutableListOf(1)
    val n: MutableList<Int> = <!TYPE_MISMATCH!>m<!>
}

// TESTCASE NUMBER: 3
open class Base3
class Derived3 : Base3()

fun case_3(b: Base3): Derived3 = <!TYPE_MISMATCH!>b<!>

// TESTCASE NUMBER: 4
fun case_4() {
    val a: Array<Int> = arrayOf(1)
    val b: Array<Number> = <!TYPE_MISMATCH!>a<!>
}

// TESTCASE NUMBER: 5
interface Inv5<T>
class IntBox5 : Inv5<Int>

fun case_5(x: IntBox5): Inv5<Number> = <!TYPE_MISMATCH!>x<!>
