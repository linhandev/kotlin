// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: type-system, type-kinds, flexible-types, platform-types -> paragraph 19 -> sentence 19
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: safe call on platform type avoids NPE when the value is null type inference
 * HELPERS: checkType
 */

fun need56219(s: String): Int = s.length

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Int>(System.getProperty("no.such.prop.abc.56219")?.let { need56219(it) } ?: 0)
}
