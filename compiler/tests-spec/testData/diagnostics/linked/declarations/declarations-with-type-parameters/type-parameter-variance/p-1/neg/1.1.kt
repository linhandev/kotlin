// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declarations-with-type-parameters, type-parameter-variance -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: variance modifiers on functions and conflicting variance usages are forbidden
 */

// TESTCASE NUMBER: 1
fun <<!VARIANCE_ON_TYPE_PARAMETER_NOT_ALLOWED!>out<!> T> illegalFunctionVariance() {}

// TESTCASE NUMBER: 2
class Out<out T> {
    fun consume(value: <!TYPE_VARIANCE_CONFLICT_ERROR!>T<!>) {}
}

// TESTCASE NUMBER: 3
class In<in T> {
    fun produce(): <!TYPE_VARIANCE_CONFLICT_ERROR!>T<!> = TODO()
}
