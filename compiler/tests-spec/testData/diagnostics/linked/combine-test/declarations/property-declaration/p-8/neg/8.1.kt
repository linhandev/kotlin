// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: private property is invisible outside
 */

// TESTCASE NUMBER: 1
class Box {
    private val x: Int = 42
}

fun case_1() = Box().<!INVISIBLE_MEMBER!>x<!>
