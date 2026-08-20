// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 34 -> sentence 34
 * PRIMARY LINKS: declarations, classifier-declaration, value-class-declaration -> paragraph 34 -> sentence 34
 *                expressions, cast-expressions -> paragraph 34 -> sentence 34
 *                expressions, when-expressions -> paragraph 34 -> sentence 34
 * NUMBER: 1
 * DESCRIPTION: wrong Any (String) cast to value class is still well-typed under when type inference
 * HELPERS: checkType
 */

@JvmInline
value class UserId56234(val raw: Int)

// TESTCASE NUMBER: 1
fun case_1() {
    val a: Any = "7"
    checkSubtype<Int>(when (a) {
        is String -> (a as UserId56234).raw
        else -> -1
    })
}
