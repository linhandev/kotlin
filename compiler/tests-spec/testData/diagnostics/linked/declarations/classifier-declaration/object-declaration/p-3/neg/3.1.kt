// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, object-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: object cannot have explicit constructors
 */

// TESTCASE NUMBER: 1
object Config {
    <!CONSTRUCTOR_IN_OBJECT!>constructor()<!> {
        val y = 1
    }
}
