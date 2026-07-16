// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-since-kotlin -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: SinceKotlin-like annotation with version String compiles
 */

// TESTCASE NUMBER: 1
annotation class SinceKotlinLike17671(val version: String)

@SinceKotlinLike17671("1.9")
class VersionedClass17671

@SinceKotlinLike17671(version = "2.0")
fun versionedFun17671() {}
