// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 36 -> sentence 36
 * PRIMARY LINKS: declarations, classifier-declaration, value-class-declaration -> paragraph 36 -> sentence 36
 *                type-system, type-kinds, flexible-types, platform-types -> paragraph 36 -> sentence 36
 *                type-system, type-kinds, nullable-types -> paragraph 36 -> sentence 36
 * NUMBER: 1
 * DESCRIPTION: value class through Any? plus Elvis unpacks correctly type inference
 * HELPERS: checkType
 */

@JvmInline
value class UserId56236(val raw: Int)

// TESTCASE NUMBER: 1
fun case_1(x: Any?) {
    checkSubtype<Int>((x as? UserId56236)?.raw ?: -1)
}
