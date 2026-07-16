// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT
// WITH_STDLIB

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, builder-style-type-inference -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: builder-style type inference — explicit type arguments satisfy standard inference without postponed variables
 * HELPERS: checkType
 */

fun <K, V> buildMap145(action: MutableMap<K, V>.() -> Unit): Map<K, V> {
    val map = mutableMapOf<K, V>()
    map.action()
    return map
}

// TESTCASE NUMBER: 1
fun case_1(): Map<String, Int> {
    val map = buildMap145<String, Int> {
        put("key", 1)
    }
    checkSubtype<Map<String, Int>>(map)
    return map
}
