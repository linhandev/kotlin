// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declarations-with-type-parameters -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: companion object, generic Throwable subclass, and call site violating upper bound are rejected
 */

// TESTCASE NUMBER: 1
class Host {
    companion object<!TYPE_PARAMETERS_IN_OBJECT!><T><!>
}

// TESTCASE NUMBER: 2
class TypedFailure<!GENERIC_THROWABLE_SUBCLASS!><T><!>(val payload: T) : Throwable()

// TESTCASE NUMBER: 3
fun <T : Number> box(): T = TODO()

fun violateBound(): Unit {
    box<<!UPPER_BOUND_VIOLATED!>String<!>>()
}
