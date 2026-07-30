// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: object literal must call superclass constructor
 */

// TESTCASE NUMBER: 1
open class Base(val s: String)

fun case_1() = object : <!SUPERTYPE_NOT_INITIALIZED!>Base<!> {
    override fun toString(): String = "x"
}
