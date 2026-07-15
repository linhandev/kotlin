// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 55 -> sentence 55
 * NUMBER: 2
 * DESCRIPTION: Space in SUPER_AT token as super @Derived breaks SUPER_AT lexeme
 */

// TESTCASE NUMBER: 1
open class Base {
    open fun value() = 1
}

class Derived : Base() {
    override fun value() = super<!SYNTAX!><!> @Derived.value()
}

fun case1(): String {
    return Derived().value().toString()
}
