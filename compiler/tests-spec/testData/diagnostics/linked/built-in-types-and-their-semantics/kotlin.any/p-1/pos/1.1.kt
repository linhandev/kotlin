// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.any -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: kotlin.Any provides equals(other: Any?): Boolean for value equality
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Case1(val x: Int) {
    override fun equals(other: Any?): Boolean = other is Case1 && other.x == x
}

fun case_1(a: Case1, b: Case1) {
    a.equals(b) checkType { check<Boolean>() }
    checkSubtype<Boolean>(a == b)
}


// TESTCASE NUMBER: 2
data class Case2(val name: String)

fun case_2(a: Case2, b: Case2) {
    a.equals(b) checkType { check<Boolean>() }
    checkSubtype<Boolean>(a.equals(null))
}


// TESTCASE NUMBER: 3
open class Case3
class Case3Derived : Case3()

fun case_3(a: Case3, b: Case3Derived) {
    a.equals(b) checkType { check<Boolean>() }
    b.equals(a) checkType { check<Boolean>() }
}


// TESTCASE NUMBER: 4
interface Case4 {
    override fun equals(other: Any?): Boolean
}

class Case4Impl : Case4 {
    override fun equals(other: Any?) = other is Case4Impl
}

fun case_4(x: Case4Impl, y: Any?) {
    x.equals(y) checkType { check<Boolean>() }
}


// TESTCASE NUMBER: 5
enum class Case5 { A, B }

fun case_5(e: Case5, a: Any?) {
    e.equals(a) checkType { check<Boolean>() }
    checkSubtype<Boolean>(Case5.A == Case5.B)
}
