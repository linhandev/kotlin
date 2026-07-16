// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: extension is invoked with explicit receiver, implicit receiver in lambda, and labeled this receiver
 */

// TESTCASE NUMBER: 1
fun String.lastChar(): Char = this[length - 1]

fun callWithExplicitReceiver(): Char = "abc".lastChar()

// TESTCASE NUMBER: 2
fun String.withReceiver(block: String.() -> Char): Char = block()

fun callWithImplicitReceiver(): Char = "xyz".withReceiver { lastChar() }

// TESTCASE NUMBER: 3
fun String.labeledThis(): Char = this@labeledThis[0]

fun callWithLabeledThis(): Char = "k".labeledThis()
