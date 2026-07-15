// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 79 -> sentence 79
 * NUMBER: 3
 * DESCRIPTION: Incomplete super expression SUPER dot without selector causes parser error
 */

// TESTCASE NUMBER: 1
open class BaseIncomplete79 {
    open fun value(): Int = 1
}

class DerivedIncomplete79 : BaseIncomplete79() {
    override fun value(): Int = super.<!SYNTAX!><!>
}

fun case1(): String {
    return "OK"
}
