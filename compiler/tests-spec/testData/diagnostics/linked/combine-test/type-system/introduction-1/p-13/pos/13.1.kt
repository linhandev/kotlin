// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 13 -> sentence 13
 *                declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: inline reified filterIsInstance uses erased class information to filter type inference
 * HELPERS: checkType
 */

inline fun <reified T> only56213(xs: List<Any>): List<T> = xs.filterIsInstance<T>()

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<List<Int>>(only56213(listOf(1, "a")))
}
