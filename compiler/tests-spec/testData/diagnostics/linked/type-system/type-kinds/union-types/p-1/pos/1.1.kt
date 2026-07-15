// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, union-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Union types decay to least upper bounds in conditional expressions
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val x = if (true) 1 else 2L
    checkSubtype<Long>(x)
}


// TESTCASE NUMBER: 2
fun case_2() {
    val x = if (true) 1 else 2.0
    checkSubtype<Number>(x)
}


// TESTCASE NUMBER: 3
fun case_3() {
    val x: Any = if (true) "a" else 1
    checkSubtype<Any>(x)
}
