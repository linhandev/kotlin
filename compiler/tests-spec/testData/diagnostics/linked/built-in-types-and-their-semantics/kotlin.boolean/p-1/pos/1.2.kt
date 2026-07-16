// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.boolean -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Boolean logical operators &&, || and ! produce kotlin.Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(a: Boolean, b: Boolean) {
    (a && b) checkType { check<Boolean>() }
    (a || b) checkType { check<Boolean>() }
}


// TESTCASE NUMBER: 2
fun case_2(a: Boolean) {
    (!a) checkType { check<Boolean>() }
    checkSubtype<Boolean>(!a)
}


// TESTCASE NUMBER: 3
fun case_3() {
    (true && false) checkType { check<Boolean>() }
    (true || false) checkType { check<Boolean>() }
}


// TESTCASE NUMBER: 4
fun case_4(a: Boolean, b: Boolean, c: Boolean) {
    ((a && b) || c) checkType { check<Boolean>() }
    (!(a || b)) checkType { check<Boolean>() }
}


// TESTCASE NUMBER: 5
fun case_5() {
    val x = true
    val y = !x && (x || false)
    y checkType { check<Boolean>() }
}
