// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.any -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: kotlin.Any inherited members and operator equals override
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Case1

fun case_1(x: Case1, y: Any?) {
    x.equals(y) checkType { check<Boolean>() }
    x.hashCode() checkType { check<Int>() }
    x.toString() checkType { check<String>() }
    checkSubtype<Boolean>(x.equals(x))
}


// TESTCASE NUMBER: 2
class Case2 {
    override operator fun equals(other: Any?): Boolean = this === other
}

fun case_2(a: Case2, b: Case2) {
    a.equals(b) checkType { check<Boolean>() }
    checkSubtype<Boolean>(a == b)
}


// TESTCASE NUMBER: 3
open class Case3Base
class Case3 : Case3Base()

fun case_3(x: Case3, y: Case3Base) {
    x.equals(y) checkType { check<Boolean>() }
    y.equals(x) checkType { check<Boolean>() }
}


// TESTCASE NUMBER: 4
class Case4 {
    override fun equals(other: Any?): Boolean = other != null
}

fun case_4(x: Case4) {
    checkSubtype<Boolean>(x.equals(null))
}


// TESTCASE NUMBER: 5
class Case5

fun case_5(x: Case5, y: Any) {
    checkSubtype<String>(x.toString())
    checkSubtype<Int>(y.hashCode())
}
