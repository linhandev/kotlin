// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: expressions, cast-expressions -> paragraph 10 -> sentence 10
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 10 -> sentence 10
 *                expressions, elvis-operator-expressions -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: as? List<*> then unchecked as List<String> feeds Elvis fallback when cast target is absent type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun test56210(x: Any): String {
    val xs = x as? List<*> ?: return "none"
    val ys = xs <!UNCHECKED_CAST!>as List<String><!>
    return ys.firstOrNull() ?: "empty"
}

fun case_1(x: Any) {
    checkSubtype<String>(test56210(x))
}
