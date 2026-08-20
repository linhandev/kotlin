// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: private getter visibility must match property visibility
 */

// TESTCASE NUMBER: 1
class Box {
    val x: Int = 42
        <!GETTER_VISIBILITY_DIFFERS_FROM_PROPERTY_VISIBILITY!>private<!> get
}

fun case_1() = Box().x
