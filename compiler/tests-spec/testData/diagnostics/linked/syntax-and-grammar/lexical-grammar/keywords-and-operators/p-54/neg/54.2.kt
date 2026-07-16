// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 54 -> sentence 54
 * NUMBER: 2
 * DESCRIPTION: Space in THIS_AT token as this @Outer breaks THIS_AT lexeme
 */

// TESTCASE NUMBER: 1
class Outer {
    val tag = "OK"
    inner class Inner {
        fun read() = this<!SYNTAX!><!> @Outer.tag
    }
}

fun case1(): String {
    return Outer().Inner().read()
}
