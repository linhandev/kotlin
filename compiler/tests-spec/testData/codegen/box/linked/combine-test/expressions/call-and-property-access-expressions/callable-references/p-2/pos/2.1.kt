// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 2 -> sentence 2
 *                expressions, call-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: unbound member function reference String::isEmpty passed to List.filterNot as higher-order argument, verifying runtime semantics
 */

// TESTCASE NUMBER: 1
fun test(xs: List<String>): List<String> = xs.filterNot(String::isEmpty)

fun box(): String {
    val result = test(listOf("a", "", "ab", "", "abc"))
    if (result != listOf("a", "ab", "abc")) return "NOK"
    if (test(emptyList()) != emptyList<String>()) return "NOK"
    return "OK"
}
