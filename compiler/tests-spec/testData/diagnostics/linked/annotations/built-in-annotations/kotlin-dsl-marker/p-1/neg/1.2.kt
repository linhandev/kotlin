// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-dsl-marker -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: two implicit receivers of same DSL in same scope produce DSL_SCOPE_VIOLATION
 */

// TESTCASE NUMBER: 1
@DslMarker
annotation class MyDsl17712

@MyDsl17712
class ReceiverA17712 {
    fun action17712() = 1
}

@MyDsl17712
class ReceiverB17712 {
    fun other17712() = 2
}

fun test17712(a: ReceiverA17712, b: ReceiverB17712) {
    with(a) l1@{
        with(b) {
            <!DSL_SCOPE_VIOLATION!>action17712<!>()
            this@l1.action17712()
            other17712()
        }
    }
}
