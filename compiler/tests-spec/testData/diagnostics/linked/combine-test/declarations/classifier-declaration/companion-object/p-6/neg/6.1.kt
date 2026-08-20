// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: private companion object member is invisible outside
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        private val x = 42
    }
}

fun case_1() = Box.<!INVISIBLE_MEMBER!>x<!>
