// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, local-property-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: local property without initializer cannot be used before assignment
 */

// TESTCASE NUMBER: 1
fun f() {
    val local: Int
    println(<!UNINITIALIZED_VARIABLE!>local<!>)
}

// TESTCASE NUMBER: 2
fun g() {
    val local: String
    <!UNINITIALIZED_VARIABLE!>local<!>.length
}
