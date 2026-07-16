// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: top-level, member, extension, and generic function declarations compile successfully
 */

// TESTCASE NUMBER: 1
fun topLevel(): Int = 1

// TESTCASE NUMBER: 2
class C {
    fun member(): String = "m"
}

// TESTCASE NUMBER: 3
fun String.extensionFun(): Int = length

// TESTCASE NUMBER: 4
fun <T> genericFun(value: T): T = value
