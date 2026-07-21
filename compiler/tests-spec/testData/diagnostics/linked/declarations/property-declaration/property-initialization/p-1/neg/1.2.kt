// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, property-initialization -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: reading uninitialized local variable
 */

// TESTCASE NUMBER: 1
fun readBeforeInit() {
    val message: String
    println(<!UNINITIALIZED_VARIABLE!>message<!>)
}
