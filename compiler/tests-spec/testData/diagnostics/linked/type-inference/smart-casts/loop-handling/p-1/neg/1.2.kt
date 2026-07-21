// FIR_IDENTICAL
// LANGUAGE: -ProhibitSimplificationOfNonTrivialConstBooleanExpressions
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, loop-handling -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: only exact while true is handled so while true == true does not smart cast after loop
 */

// TESTCASE NUMBER: 1
fun loopFlag1414(): Boolean = true

fun case_1() {
    var p: String? = "ok"
    while (<!NON_TRIVIAL_BOOLEAN_CONSTANT!>true == true<!>) {
        p!!.length
        if (loopFlag1414()) break
    }
    p<!UNSAFE_CALL!>.<!>length
}
