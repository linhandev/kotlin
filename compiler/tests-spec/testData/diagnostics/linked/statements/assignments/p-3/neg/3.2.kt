// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: c?.p = 10 on val property reports VAL_REASSIGNMENT
 */

class Container {
    val p = 0
}

// TESTCASE NUMBER: 1
fun case1() {
    val c: Container? = Container()
    <!VAL_REASSIGNMENT!>c?.p<!> = 10
}
