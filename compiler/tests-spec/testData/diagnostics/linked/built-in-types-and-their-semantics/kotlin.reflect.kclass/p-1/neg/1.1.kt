// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.reflect.kclass -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: call-and-property-access-class-literals -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: non-class-literal values cannot be assigned to kotlin.reflect.KClass
 * HELPERS: checkType
 */

import kotlin.reflect.KClass

// TESTCASE NUMBER: 1
fun case_1() {
    val k: KClass<String> = <!TYPE_MISMATCH!>"not a class"<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val k: KClass<Int> = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val a: KClass<String> = String::class
    val b: KClass<Int> = <!TYPE_MISMATCH!>a<!>
}
