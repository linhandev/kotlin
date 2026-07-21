// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: primary constructor parameter used as supertype constructor argument
 */

// TESTCASE NUMBER: 1
open class Base(val x: Int)

class Child(x: Int) : Base(x)
