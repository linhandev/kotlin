// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST -PLATFORM_CLASS_MAPPED_TO_KOTLIN
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: expressions, cast-expressions -> paragraph 14 -> sentence 14
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 14 -> sentence 14
 *                expressions, when-expressions -> paragraph 14 -> sentence 14
 *                type-system, type-kinds, flexible-types, platform-types -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: casting Any to java.util.List<*> then when-branch is List<String> is still illegal under erasure
 */

// TESTCASE NUMBER: 1
fun case_1(x: Any): Int {
    val y = x as java.util.List<*>
    return when (y) {
        is <!CANNOT_CHECK_FOR_ERASED!>List<String><!> -> y.size
        else -> -1
    }
}
