// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: private lazy delegated property is invisible outside
 */

// TESTCASE NUMBER: 1
class Box {
    private val x: Int by lazy { 42 }
}

fun case_1() = Box().<!INVISIBLE_MEMBER!>x<!>
