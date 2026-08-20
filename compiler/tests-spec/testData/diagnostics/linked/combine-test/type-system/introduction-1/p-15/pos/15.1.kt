// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST -PLATFORM_CLASS_MAPPED_TO_KOTLIN
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 15 -> sentence 15
 *                expressions, when-expressions -> paragraph 15 -> sentence 15
 *                type-inference, introduction-1 -> paragraph 15 -> sentence 15
 *                type-system, type-kinds, flexible-types, platform-types -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: when on a Java List<*> uses is List<*> smart cast and joins with Int under Number type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(x: Any) {
    checkSubtype<Number>(when (x) {
        is java.util.List<*> -> x.size
        is Int -> x
        else -> -1
    })
}
