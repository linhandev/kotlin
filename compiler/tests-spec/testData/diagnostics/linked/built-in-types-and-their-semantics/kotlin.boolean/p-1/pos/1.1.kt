// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.boolean -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Boolean literals true and false have kotlin.Boolean type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    true checkType { check<Boolean>() }
    false checkType { check<Boolean>() }
}


// TESTCASE NUMBER: 2
fun case_2() {
    checkSubtype<Boolean>(true)
    checkSubtype<Boolean>(false)
}


// TESTCASE NUMBER: 3
val case_3_true: Boolean = true
val case_3_false: Boolean = false

fun case_3() {
    case_3_true checkType { check<Boolean>() }
    case_3_false checkType { check<Boolean>() }
}


// TESTCASE NUMBER: 4
fun case_4(flag: Boolean): Boolean = flag

fun case_4_use() {
    case_4(true) checkType { check<Boolean>() }
    case_4(false) checkType { check<Boolean>() }
}


// TESTCASE NUMBER: 5
fun case_5() {
    val b = if (true) true else false
    b checkType { check<Boolean>() }
}
