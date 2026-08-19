// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 6 -> sentence 6
 *                expressions, elvis-operator-expressions -> paragraph 6 -> sentence 6
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: as? with Elvis infers String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: Any = 1
    checkSubtype<String>((x as? String) ?: "default")
}
