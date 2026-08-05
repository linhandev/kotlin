// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 27 -> sentence 27
 *                scopes-and-identifiers, linked-scopes -> paragraph 27 -> sentence 27
 *                declarations, function-declaration -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: same-file top-level declaration takes priority over an identically named imported function type inference
 * HELPERS: checkType
 */
import kotlin.math.sqrt

fun sqrt(x: Int): Int = x

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Int>(sqrt(4))
}
