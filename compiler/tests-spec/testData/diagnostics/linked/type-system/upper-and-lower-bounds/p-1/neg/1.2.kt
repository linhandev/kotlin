// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, upper-and-lower-bounds -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Values below lower bound cannot substitute for bounded type parameter
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val i: Int = 1
    val s: String = <!TYPE_MISMATCH!>i<!>
}

// TESTCASE NUMBER: 2
open class Base2
class Derived2 : Base2()

fun case_2(b: Base2): Derived2 = <!TYPE_MISMATCH!>b<!>

// TESTCASE NUMBER: 3
fun case_3() {
    val a: Any = true
    val i: Int = <!TYPE_MISMATCH!>a<!>
}

// TESTCASE NUMBER: 4
fun case_4() {
    val n: Number = 1.0
    val i: Int = <!TYPE_MISMATCH!>n<!>
}

// TESTCASE NUMBER: 5
interface IA
interface IB
fun case_5(x: IA): IB = <!TYPE_MISMATCH!>x<!>
