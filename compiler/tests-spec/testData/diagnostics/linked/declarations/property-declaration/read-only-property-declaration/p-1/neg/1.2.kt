// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, read-only-property-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: val without initializer cannot be read before assignment
 */

// TESTCASE NUMBER: 1
fun localVal() {
    val x: Int
    println(<!UNINITIALIZED_VARIABLE!>x<!>)
}
