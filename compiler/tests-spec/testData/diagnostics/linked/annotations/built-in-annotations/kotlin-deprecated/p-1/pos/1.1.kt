// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-deprecated -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Deprecated annotation with message compiles on declaration
 */

// TESTCASE NUMBER: 1
@Deprecated("Use newFun instead")
fun oldFun17601() {}

@Deprecated("Use NewClass instead")
class OldClass17601
