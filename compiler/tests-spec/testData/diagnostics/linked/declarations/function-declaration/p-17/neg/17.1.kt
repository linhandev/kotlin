// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: extension on Int, List<Int>, and bounded type parameter is not applicable to incompatible receiver types
 */

// TESTCASE NUMBER: 1
fun Int.mark(): Unit = Unit

fun wrongReceiverType(value: String) {
    value.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>mark<!>()
}

// TESTCASE NUMBER: 2
fun List<Int>.sumItems(): Int = sum()

fun incompatibleReceiver(values: List<String>) {
    values.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>sumItems<!>()
}

// TESTCASE NUMBER: 3
interface A
fun <T : A> T.describe(): String = "A"

fun missingConstraint(receiver: Any) {
    receiver.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>describe<!>()
}
