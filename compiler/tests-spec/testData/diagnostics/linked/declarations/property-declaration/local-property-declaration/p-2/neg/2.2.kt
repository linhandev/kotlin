// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, local-property-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: local var without initializer cannot be read before assignment
 */

// TESTCASE NUMBER: 1
fun missingInit() {
    var x: Int
    println(<!UNINITIALIZED_VARIABLE!>x<!>)
}
