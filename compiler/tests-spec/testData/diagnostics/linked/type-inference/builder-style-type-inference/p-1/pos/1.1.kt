// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT
// WITH_STDLIB

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, builder-style-type-inference -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: builder-style type inference — putAll and put in lambda body infer Map<String, Number>
 * HELPERS: checkType
 */

fun <K, V> buildMap145(action: MutableMap<K, V>.() -> Unit): Map<K, V> {
    val map = mutableMapOf<K, V>()
    map.action()
    return map
}

// TESTCASE NUMBER: 1
fun case_1(baseMap: Map<String, Number>, additionalEntry: Pair<String, Int>?) {
    val myMap = buildMap145 {
        putAll(baseMap)
        if (additionalEntry != null) {
            put(<!DEBUG_INFO_SMARTCAST!>additionalEntry<!>.first, <!DEBUG_INFO_SMARTCAST!>additionalEntry<!>.second)
        }
    }
    checkSubtype<Map<String, Number>>(myMap)
}
