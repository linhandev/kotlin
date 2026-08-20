// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 4 -> sentence 4
 *                scopes-and-identifiers, identifiers-and-paths -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: import alias as resolves naming conflicts for imported top-level declarations type inference
 * HELPERS: checkType
 */
import kotlin.math.PI as MathPI56004

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Double>(MathPI56004)
}
