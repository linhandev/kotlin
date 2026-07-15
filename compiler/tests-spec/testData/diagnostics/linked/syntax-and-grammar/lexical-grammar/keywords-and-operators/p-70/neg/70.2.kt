// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 70 -> sentence 70
 * NUMBER: 2
 * DESCRIPTION: Space inside OBJECT token as ob ject breaks object declaration lexeme
 */

// TESTCASE NUMBER: 1
<!SYNTAX!>ob<!> <!SYNTAX!>ject<!> <!SYNTAX!>Broken70<!>

fun case1(): String {
    return "OK"
}
