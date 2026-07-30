// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 25 -> sentence 25
 *                type-inference, local-type-inference -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: custom get index read infers String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    operator fun get(i: Int): String = "x"
}

fun case1() {
    checkSubtype<String>(Box()[0])
}
