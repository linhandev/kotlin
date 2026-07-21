// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -UNUSED_TYPEALIAS_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, type-alias -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: type aliases for concrete, generic, and type-parameterized names are usable in function signatures
 */

// TESTCASE NUMBER: 1
typealias IntList = List<Int>

// TESTCASE NUMBER: 2
typealias IntMap<V> = Map<Int, V>

// TESTCASE NUMBER: 3
typealias Strange<T> = String

fun useAliases(list: IntList, map: IntMap<String>, label: Strange<Int>): String {
    return "${list.size}:${map.size}:$label"
}
