// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 79 -> sentence 79
 * NUMBER: 2
 * DESCRIPTION: Space inside SUPER token as su per breaks super expression lexeme
 */

// TESTCASE NUMBER: 1
open class BaseBroken79 {
    open fun value(): Int = 1
}

class DerivedBroken79 : BaseBroken79() {
    override fun value(): Int = <!UNRESOLVED_REFERENCE!>su<!> <!DEBUG_INFO_MISSING_UNRESOLVED!>per<!><!SYNTAX!>.<!><!DEBUG_INFO_MISSING_UNRESOLVED!>value<!>(<!SYNTAX!><!>)
}

fun case1(): String {
    return "OK"
}
