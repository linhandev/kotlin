// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 51 -> sentence 51
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 51 -> sentence 51
 *                syntax-and-grammar, syntax-grammar -> paragraph 51 -> sentence 51
 * NUMBER: 1
 * DESCRIPTION: nested generic call type arguments with angle bracket syntax are correctly parsed
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T> box(x: T): List<T> = listOf(x)

fun case_1() {
    val result = box<List<Int>>(listOf(1))
    checkSubtype<List<List<Int>>>(result)
}
