// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-since-kotlin -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: SinceKotlin-like version parameter must be String
 */

// TESTCASE NUMBER: 1
annotation class SinceKotlinLike17672(val version: String)

@SinceKotlinLike17672(version = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>)
class BadVersion17672
