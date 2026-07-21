// FIR_IDENTICAL
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: nullable receiver cannot call extension declared on a non-null receiver type
 */

// TESTCASE NUMBER: 1
fun String.lastChar(): Char = this[length - 1]

fun callOnNullableReceiver(value: String?) {
    value<!UNSAFE_CALL!>.<!>lastChar()
}

// TESTCASE NUMBER: 2
fun callOnNullLiteral() {
    null<!UNSAFE_CALL!>.<!>lastChar()
}
