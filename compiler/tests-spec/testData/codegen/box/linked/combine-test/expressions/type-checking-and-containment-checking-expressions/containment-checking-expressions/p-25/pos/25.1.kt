// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 25 -> sentence 25
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: in operator on Map checks keys not values at runtime
 */

// TESTCASE NUMBER: 1
fun test(m: Map<Any, Int>, x: Int): Boolean = x in m

fun box(): String {
    val m = mapOf<Any, Int>("a" to 1)
    if (test(m, 1)) return "NOK: integer value is not a map key"
    if (!m.containsValue(1)) return "NOK: value 1 should be present in map values"
    if (!("a" in m)) return "NOK: string key should be found by in operator"
    if (1 in m != m.containsKey(1)) return "NOK: in on map must follow containsKey semantics"
    return "OK"
}
