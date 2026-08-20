// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 5 -> sentence 5
 *                declarations, function-declaration -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: imported top-level function can be called without an explicit receiver type inference
 * HELPERS: checkType
 */
import kotlin.math.max

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Int>(max(3, 5))
}
