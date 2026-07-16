// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, builder-style-type-inference -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: builder-style type inference — runtime buildMap145 infers String and Int from lambda body
 */
// TESTCASE NUMBER: 1

fun <K, V> buildMap145(action: MutableMap<K, V>.() -> Unit): Map<K, V> {
    val map = mutableMapOf<K, V>()
    map.action()
    return map
}

fun box(): String {
    val map = buildMap145 {
        put("a", 1)
        put("b", 2)
    }
    return if (map["a"] == 1 && map["b"] == 2) "OK" else "NOK"
}
