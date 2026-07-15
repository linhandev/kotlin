// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.unit -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: kotlin.Unit singleton and explicit Unit expressions
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    Unit checkType { check<Unit>() }
    checkSubtype<Unit>(Unit)
}


// TESTCASE NUMBER: 2
fun case_2(): Unit = Unit

fun case_2_use() {
    case_2() checkType { check<Unit>() }
}


// TESTCASE NUMBER: 3
val case_3: Unit = Unit

fun case_3_use() {
    case_3 checkType { check<Unit>() }
}


// TESTCASE NUMBER: 4
fun case_4() {
    val f: () -> Unit = { }
    f() checkType { check<Unit>() }
}
