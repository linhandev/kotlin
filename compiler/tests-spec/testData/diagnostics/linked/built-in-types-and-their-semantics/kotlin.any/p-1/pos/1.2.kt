// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.any -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: kotlin.Any provides hashCode(): Int and toString(): String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Case1(val x: Int) {
    override fun hashCode(): Int = x
}

fun case_1(x: Case1) {
    x.hashCode() checkType { check<Int>() }
    checkSubtype<Int>(x.hashCode())
}


// TESTCASE NUMBER: 2
data class Case2(val name: String)

fun case_2(x: Case2) {
    x.hashCode() checkType { check<Int>() }
    x.toString() checkType { check<String>() }
}


// TESTCASE NUMBER: 3
class Case3 {
    override fun toString(): String = "Case3"
}

fun case_3(x: Case3, a: Any) {
    x.toString() checkType { check<String>() }
    a.toString() checkType { check<String>() }
}


// TESTCASE NUMBER: 4
object Case4 {
    override fun hashCode(): Int = 42
    override fun toString(): String = "Case4"
}

fun case_4() {
    Case4.hashCode() checkType { check<Int>() }
    Case4.toString() checkType { check<String>() }
}


// TESTCASE NUMBER: 5
sealed class Case5 {
    data class Leaf(val v: Int) : Case5()
}

fun case_5(x: Case5.Leaf) {
    x.hashCode() checkType { check<Int>() }
    checkSubtype<String>(x.toString())
}
