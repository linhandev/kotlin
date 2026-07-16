// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT
// WITH_STDLIB

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, builder-style-type-inference -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: builder-style type inference — postponed receiver type variables fixed after lambda body completes
 * HELPERS: checkType
 */

fun <K, V> buildMap145(action: MutableMap<K, V>.() -> Unit): Map<K, V> {
    val map = mutableMapOf<K, V>()
    map.action()
    return map
}

// TESTCASE NUMBER: 1
fun case_1(): Map<String, Int> {
    val map = buildMap145 {
        put("a", 1)
        put("b", 2)
    }
    checkSubtype<Map<String, Int>>(map)
    return map
}
