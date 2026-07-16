// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, bare-type-argument-inference -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: bare type argument inference — nullable U? subject uses non-nullable U for inference
 * HELPERS: checkType
 */

interface Holder144<A>
class Box144<T>(val value: T) : Holder144<T>

// TESTCASE NUMBER: 1
fun case_1(holder: Holder144<String>?) {
    if (holder is Box144) {
        checkSubtype<Box144<String>>(<!DEBUG_INFO_SMARTCAST!>holder<!>)
        <!DEBUG_INFO_SMARTCAST!>holder<!>.value.length
    }
}
