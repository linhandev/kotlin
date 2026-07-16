// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, annotation-declarations -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: repeatable annotation may be applied twice to the same entity
 */

// TESTCASE NUMBER: 1
@Repeatable
annotation class Repeatable17401(val value: Int)

@Repeatable17401(1)
@Repeatable17401(2)
class RepeatedClass17401
