// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: expressions, cast-expressions -> paragraph 10 -> sentence 10
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 10 -> sentence 10
 *                expressions, elvis-operator-expressions -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: as? List<*> then unchecked as List<String> feeds Elvis fallback when cast target is absent
 */

// TESTCASE NUMBER: 1
@Suppress("UNCHECKED_CAST")
fun test56210(x: Any): String {
    val xs = x as? List<*> ?: return "none"
    val ys = xs as List<String>
    return ys.firstOrNull() ?: "empty"
}

fun box(): String {
    if (test56210(listOf("a", "b")) != "a") return "NOK"
    if (test56210(emptyList<String>()) != "empty") return "NOK"
    if (test56210(1) != "none") return "NOK"
    return "OK"
}
