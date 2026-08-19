// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 20 -> sentence 20
 *                type-inference, smart-casts -> paragraph 20 -> sentence 20
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: smart cast from when branch does not apply outside when expression
 */

// TESTCASE NUMBER: 1
sealed class Result {
    class Ok(val value: String) : Result()
    object Err : Result()
}

fun test(r: Result): Int {
    when (r) {
        is Result.Ok -> {}
        Result.Err -> {}
    }
    return r.<!UNRESOLVED_REFERENCE!>value<!>.<!DEBUG_INFO_MISSING_UNRESOLVED!>length<!>
}
